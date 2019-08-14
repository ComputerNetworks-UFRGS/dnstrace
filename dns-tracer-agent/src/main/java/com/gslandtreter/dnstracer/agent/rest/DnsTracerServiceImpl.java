package com.gslandtreter.dnstracer.agent.rest;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;
import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class DnsTracerServiceImpl implements DnsTracerService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${rest.server.endpoint}")
    String endpoint;

    @Override
    public List<DomainDnssEntity> insertDnsServerEntries(Iterable<DomainDnssEntity> entries) {
        DomainDnssEntity[] response = restTemplate.postForObject(endpoint+ "/dnsTraceEntries", entries, DomainDnssEntity[].class);
        return Arrays.asList(response);
    }

    @Override
    public List<String> getAlreadyProcessedDomains(VersionInfoEntity info) {
        String[] response = restTemplate.getForObject(
                endpoint + "/processedDomains?trace_version=" + info.getId(), String[].class);
        return Arrays.asList(response);
    }

    @Override
    public VersionInfoEntity getVersionInfo(String nodeId) {
        return restTemplate.getForObject(endpoint + "/versionInfo?region=" + nodeId, VersionInfoEntity.class);
    }

    @Override
    public VersionInfoEntity finishExecution(VersionInfoEntity info) {
        return restTemplate.postForEntity(endpoint + "/versionInfo/" + info.getId() + "/finish",
                null, VersionInfoEntity.class).getBody();
    }


}
