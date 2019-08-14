package com.gslandtreter.dnstracer.agent.job;

import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class Dispatcher implements CommandLineRunner {

    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger();

    @Value("${domainList.file}")
    private String domainFile;


    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private VersionInfoHandler versionInfoHandler;

    private List<String> alreadyProcessedDomains;

    private void getAlreadyProcessedDomains() {
        alreadyProcessedDomains = versionInfoHandler.getAlreadyProcessedDomains();
    }

    private void finishExecution() {
        LOGGER.info("Finishing execution! Processed domains: [{}]", totalTasks + alreadyProcessedDomains.size());
        VersionInfoEntity execution = versionInfoHandler.finishExecution();

        long diff = execution.getEndDate().getTime() - execution.getStartDate().getTime();
        Duration timeTaken = Duration.ofMillis(diff);

        String prettyTimeTaken = timeTaken.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
        LOGGER.info("Total Duration: {}", prettyTimeTaken);

        taskExecutor.shutdown();
    }

    private int totalTasks = 0;

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("Obtaining version info. My ID: {}", versionInfoHandler.getVersionInfoEntity().getRegion());

        getAlreadyProcessedDomains();
        LOGGER.info("Version info obtained!");

        if (alreadyProcessedDomains.size() > 0) {
            LOGGER.info("Resuming last execution. Execution ID: {} Skipping already processed domains: [{}]",
                    versionInfoHandler.getVersionInfoEntity().getId(), alreadyProcessedDomains.size());
        } else {
            LOGGER.info("Starting new execution. Execution ID: {}",
                    versionInfoHandler.getVersionInfoEntity().getId());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(domainFile)))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] splitLine = line.split(",");

                String domainName = splitLine[1];
                if (alreadyProcessedDomains.contains(domainName)) {
                    LOGGER.debug("Skipping domain: {}", domainName);
                    continue;
                }

                HopAnalyser job = ctx.getBean(HopAnalyser.class);
                job.setDomainName(domainName);
                job.setPosition(Integer.parseInt(splitLine[0]));
                taskExecutor.execute(job);
                totalTasks++;
            }
        }

        while (true) {
            long tasksExecuted = taskExecutor.getThreadPoolExecutor().getCompletedTaskCount();
            long currentQueueSize = totalTasks - tasksExecuted;

            if (currentQueueSize == 0) {
                break;
            }

            LOGGER.info("Tasks executed: [{}] Tasks pending: [{}]", tasksExecuted, currentQueueSize);
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        }

        finishExecution();
    }
}
