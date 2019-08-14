package com.gslandtreter.dnstracer.common.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(DomainDnssPKEntity.class)
@Table(name = "domain_dnss")
public class DomainDnssEntity {

    private String domain;
    private Integer traceVersion;
    private String nsName;
    private Integer position;
    private String nsIp;
    private String hopName;
    private String hopIp;
    private Integer hopAsn;
    private String hopSubnet;
    private String nsIpv6;
    private Integer nsAsn;
    private String nsSubnet;
    private Integer hopPosition;
    private Integer isHbtl;
    private Integer isLastHop;

    @Id
    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Id
    @Column(name = "ns_name")
    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName = nsName;
    }

    @Basic
    @Column(name = "ns_ip")
    public String getNsIp() {
        return nsIp;
    }

    public void setNsIp(String nsIp) {
        this.nsIp = nsIp;
    }

    @Basic
    @Column(name = "hop_name")
    public String getHopName() {
        return hopName;
    }

    public void setHopName(String hopName) {
        this.hopName = hopName;
    }

    @Basic
    @Column(name = "hop_ip")
    public String getHopIp() {
        return hopIp;
    }

    public void setHopIp(String hopIp) {
        this.hopIp = hopIp;
    }

    @Basic
    @Column(name = "hop_asn")
    public Integer getHopAsn() {
        return hopAsn;
    }

    public void setHopAsn(Integer hopAsn) {
        this.hopAsn = hopAsn;
    }

    @Basic
    @Column(name = "hop_subnet")
    public String getHopSubnet() {
        return hopSubnet;
    }

    public void setHopSubnet(String hopSubnet) {
        this.hopSubnet = hopSubnet;
    }

    @Basic
    @Column(name = "ns_ipv6")
    public String getNsIpv6() {
        return nsIpv6;
    }

    public void setNsIpv6(String nsIpv6) {
        this.nsIpv6 = nsIpv6;
    }

    @Basic
    @Column(name = "ns_asn")
    public Integer getNsAsn() {
        return nsAsn;
    }

    public void setNsAsn(Integer nsAsn) {
        this.nsAsn = nsAsn;
    }

    @Basic
    @Column(name = "ns_subnet")
    public String getNsSubnet() {
        return nsSubnet;
    }

    public void setNsSubnet(String nsSubnet) {
        this.nsSubnet = nsSubnet;
    }

    @Id
    @Column(name = "hop_position")
    public Integer getHopPosition() {
        return hopPosition;
    }

    public void setHopPosition(Integer hopPosition) {
        this.hopPosition = hopPosition;
    }

    @Basic
    @Column(name = "is_hbtl")
    public Integer getIsHbtl() {
        return isHbtl;
    }

    public void setIsHbtl(Integer isHbtl) {
        this.isHbtl = isHbtl;
    }

    @Basic
    @Column(name = "is_lasthop")
    public Integer getIsLastHop() {
        return isLastHop;
    }

    public void setIsLastHop(Integer isLastHop) {
        this.isLastHop = isLastHop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainDnssEntity that = (DomainDnssEntity) o;
        return Objects.equals(domain, that.domain) &&
                Objects.equals(position, that.position) &&
                Objects.equals(nsName, that.nsName) &&
                Objects.equals(nsIp, that.nsIp) &&
                Objects.equals(hopName, that.hopName) &&
                Objects.equals(hopIp, that.hopIp) &&
                Objects.equals(hopAsn, that.hopAsn) &&
                Objects.equals(hopSubnet, that.hopSubnet) &&
                Objects.equals(nsIpv6, that.nsIpv6) &&
                Objects.equals(nsAsn, that.nsAsn) &&
                Objects.equals(nsSubnet, that.nsSubnet) &&
                Objects.equals(hopPosition, that.hopPosition) &&
                Objects.equals(isHbtl, that.isHbtl) &&
                Objects.equals(traceVersion, that.traceVersion) &&
                Objects.equals(isLastHop, that.isLastHop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, position, nsName, nsIp, hopName, hopIp, hopAsn, hopSubnet, nsIpv6, nsAsn, nsSubnet, hopPosition, isHbtl, isLastHop);
    }

    @Id
    @Column(name = "trace_version")
    public Integer getTraceVersion() {
        return traceVersion;
    }

    public void setTraceVersion(Integer traceVersion) {
        this.traceVersion = traceVersion;
    }
}
