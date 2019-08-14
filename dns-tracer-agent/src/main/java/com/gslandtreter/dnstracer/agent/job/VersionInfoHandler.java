package com.gslandtreter.dnstracer.agent.job;

import com.gslandtreter.dnstracer.agent.rest.DnsTracerService;
import com.gslandtreter.dnstracer.common.entity.VersionInfoEntity;

import java.util.List;

public class VersionInfoHandler {

    private DnsTracerService dnsTracerService;
    private String nodeId;
    private VersionInfoEntity versionInfoEntity;

    public VersionInfoHandler(DnsTracerService dnsTracerService, String nodeId) {
        this.dnsTracerService = dnsTracerService;
        this.nodeId = nodeId;
    }

    public VersionInfoEntity getVersionInfoEntity() {
        if (versionInfoEntity == null) {
            versionInfoEntity = dnsTracerService.getVersionInfo(nodeId);
        }

        return versionInfoEntity;
    }

    public List<String> getAlreadyProcessedDomains() {
        return dnsTracerService.getAlreadyProcessedDomains(getVersionInfoEntity());
    }

    public VersionInfoEntity finishExecution() {
        versionInfoEntity = dnsTracerService.finishExecution(getVersionInfoEntity());
        return versionInfoEntity;
    }

}
