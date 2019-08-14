package com.gslandtreter.dnstracer.server;

import com.gslandtreter.dnstracer.server.asn.ASNDatabase;
import com.gslandtreter.dnstracer.server.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;
import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    public StatisticsRepository statisticsRepository() {
        return new StatisticsRepository();
    }

    @Value("${as.databaseFile}")
    private String asnDatabaseFile;

    @Value("${as.namesDatabaseFile}")
    private String asNamesDatabaseFile;

    @Value("${threadPool.size}")
    private Integer threadPoolSize;

    @Value("${node.id}")
    private String nodeId;

    @Value("${domainList.file}")
    private String domainFile;

    @Bean
    public ASNDatabase asnDatabase() throws IOException {
        return new ASNDatabase(new File(asnDatabaseFile), new File(asNamesDatabaseFile));
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setThreadNamePrefix("Async-");
        executor.setCorePoolSize(threadPoolSize);
        executor.setMaxPoolSize(threadPoolSize);
        executor.setQueueCapacity(1000000);

        return executor;
    }

}
