package com.gslandtreter.dnstracer.common.entity;

import java.io.Serializable;
import java.util.Objects;

public class DomainDnssPKEntity implements Serializable {
    private String domain;
    private String nsName;
    private Integer hopPosition;
    private Integer traceVersion;


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    public Integer getHopPosition() {
        return hopPosition;
    }

    public void setHopPosition(Integer hopPosition) {
        this.hopPosition = hopPosition;
    }

    public Integer getTraceVersion() {
        return traceVersion;
    }

    public void setTraceVersion(Integer traceVersion) {
        this.traceVersion = traceVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainDnssPKEntity that = (DomainDnssPKEntity) o;
        return Objects.equals(domain, that.domain) &&
                Objects.equals(nsName, that.nsName) &&
                Objects.equals(hopPosition, that.hopPosition) &&
                Objects.equals(traceVersion, that.traceVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, nsName, hopPosition, traceVersion);
    }
}
