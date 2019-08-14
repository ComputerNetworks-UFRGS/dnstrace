package com.gslandtreter.dnstracer.agent.rest;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;
import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;

import java.util.List;

public interface DnsTracerService {
    List<DomainDnssEntity> insertDnsServerEntries(Iterable<DomainDnssEntity> entries);

    VersionInfoEntity getVersionInfo(String nodeId);

    VersionInfoEntity finishExecution(VersionInfoEntity info);

    List<String> getAlreadyProcessedDomains(VersionInfoEntity info);

}
