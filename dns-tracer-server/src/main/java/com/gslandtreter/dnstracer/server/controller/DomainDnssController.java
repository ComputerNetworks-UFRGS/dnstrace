package com.gslandtreter.dnstracer.server.controller;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;
import com.gslandtreter.dnstracer.common.entity.DomainDnssPKEntity;
import com.gslandtreter.dnstracer.server.job.ProcessHop;
import com.gslandtreter.dnstracer.server.repository.DomainDnssRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.gslandtreter.dnstracer.server.asn.ASNDatabase.LOGGER;

@RestController
@RequestMapping("/api")
public class DomainDnssController {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    DomainDnssRepository domainDnssRepository;

    @GetMapping("/dnsTraceEntry")
    public DomainDnssEntity getDnssEntityByVersion(@RequestParam(value = "domain") String domain,
                                                   @RequestParam(value = "ns_name") String nsName,
                                                   @RequestParam(value = "hop_position") int hopPosition,
                                                   @RequestParam(value = "trace_version") int traceVersion) {

        DomainDnssPKEntity privateKey = new DomainDnssPKEntity();
        privateKey.setDomain(domain);
        privateKey.setNsName(nsName);
        privateKey.setHopPosition(hopPosition);
        privateKey.setTraceVersion(traceVersion);

        return domainDnssRepository.findById(privateKey).orElse(null);
    }

    private int totalTasks = 0;

    @PostMapping("/dnsTraceEntries")
    public List<DomainDnssEntity> createDnssEntity(@Valid @RequestBody Iterable<DomainDnssEntity> entities) {

        System.out.println(entities);

        ProcessHop job = ctx.getBean(ProcessHop.class);

        taskExecutor.execute(job);
        totalTasks++;

        return domainDnssRepository.saveAll(entities);
    }

    @GetMapping("/processedDomains")
    public List<String> getProcessedDomains(@RequestParam(value = "trace_version") Integer traceVersion) {
        return domainDnssRepository.getAlreadyProcessedDomains(traceVersion);
    }

}
