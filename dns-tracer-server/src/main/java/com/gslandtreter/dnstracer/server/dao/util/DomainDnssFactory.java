package com.gslandtreter.dnstracer.server.dao.util;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;

public class DomainDnssFactory {

    public static DomainDnssEntity getDomainDnssEntity(
            Integer position,
            String domain,
            String nameServer,
            String nsIPv4,
            String nsIPv6,
            String hop,
            String hopName,
            Integer hopAs,
            String hopAsSubnet,
            Integer nsAsn,
            String nsSubnet,
            Integer traceVersion,
            Integer hopPosition,
            Integer isHbtl,
            Integer isLastHop
    ) {
        DomainDnssEntity entity = new DomainDnssEntity();

        entity.setPosition(position);
        entity.setDomain(domain);
        entity.setNsName(nameServer);
        entity.setNsIp(nsIPv4);
        entity.setNsIpv6(nsIPv6);
        entity.setHopIp(hop);
        entity.setHopName(hopName);
        entity.setHopAsn(hopAs);
        entity.setHopSubnet(hopAsSubnet);
        entity.setNsAsn(nsAsn);
        entity.setNsSubnet(nsSubnet);
        entity.setTraceVersion(traceVersion);
        entity.setHopPosition(hopPosition);
        entity.setIsHbtl(isHbtl);
        entity.setIsLastHop(isLastHop);

        return entity;
    }

}
