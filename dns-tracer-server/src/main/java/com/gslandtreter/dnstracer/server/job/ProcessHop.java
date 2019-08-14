package com.gslandtreter.dnstracer.server.job;

import com.gslandtreter.dnstracer.common.entity.DomainDnssEntity;

import com.gslandtreter.dnstracer.server.dao.util.DomainDnssFactory;
import com.gslandtreter.dnstracer.server.asn.ASEntry;
import com.gslandtreter.dnstracer.server.asn.ASNDatabase;
import com.gslandtreter.dnstracer.server.networkUtils.NSResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@Scope("prototype")
public class ProcessHop implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();

    private String domainName;
    private int position;
    private int hop_position;
    private String hop;

    @Autowired
    private ASNDatabase asnDatabase;


//    @Autowired
//    VersionInfoHandler versionInfoHandler;


    private DomainDnssEntity processHopInfo(String nameServer, Integer totalHops, InetAddress hop, Integer hopPosition, Integer versionInfo) {
        ASEntry hopAs;
        Integer isHbtl;
        Integer isLastHop;
        String hopAsName;

        Integer HbtlPosition = totalHops - 2;
        Integer LastHopPosition = totalHops - 1;

        if (HbtlPosition.equals(hopPosition)) {
            isHbtl = 1;
        } else {
            isHbtl = 0;
        }

        if (LastHopPosition.equals(hopPosition)) {
            isLastHop = 1;
        } else {
            isLastHop = 0;
        }

        if (hop == null) {
            return DomainDnssFactory.getDomainDnssEntity(
                    position,
                    domainName,
                    nameServer,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    versionInfo,
                    hopPosition,
                    isHbtl,
                    isLastHop);
        }


        InetAddress nsIpv6 = NSResolver.getIPv6Address(nameServer);
        InetAddress nsIPv4 = NSResolver.getIPv4Address(nameServer);

        if (nsIPv4 == null) {
            return DomainDnssFactory.getDomainDnssEntity(
                    position,
                    domainName,
                    nameServer,
                    null,
                    null,
                    null,
                    null,
                    -1,
                    null,
                    -1,
                    null,
                    versionInfo,
                    hopPosition,
                    isHbtl,
                    isLastHop);
        }

        ASEntry nsAsEntry = asnDatabase.getASEntry(nsIPv4.getHostAddress());

        if (hop != null) {

            hopAs = asnDatabase.getASEntry(hop.getHostAddress());


            if (hopAs == null) {
                DomainDnssEntity entity = DomainDnssFactory.getDomainDnssEntity(
                        position,
                        domainName,
                        nameServer,
                        nsIPv4.getHostAddress(),
                        nsIpv6 != null ? nsIpv6.getHostAddress() : null,
                        null,
                        null,
                        0,
                        null,
                        (int) nsAsEntry.getAsNumber(),
                        nsAsEntry.getAsSubnet(),
                        versionInfo,
                        hopPosition,
                        isHbtl,
                        isLastHop);

                return entity;
            }else {

                hopAsName = asnDatabase.getAsName(hopAs);

                DomainDnssEntity entity = DomainDnssFactory.getDomainDnssEntity(
                        position,
                        domainName,
                        nameServer,
                        nsIPv4.getHostAddress(),
                        nsIpv6 != null ? nsIpv6.getHostAddress() : null,
                        hop.getHostAddress(),
                        hopAsName,
                        (int) hopAs.getAsNumber(),
                        hopAs.getAsSubnet(),
                        (int) nsAsEntry.getAsNumber(),
                        nsAsEntry.getAsSubnet(),
                        versionInfo,
                        hopPosition,
                        isHbtl,
                        isLastHop);

                return entity;
            }
        } else {

            DomainDnssEntity entity = DomainDnssFactory.getDomainDnssEntity(
                    position,
                    domainName,
                    nameServer,
                    nsIPv4.getHostAddress(),
                    nsIpv6 != null ? nsIpv6.getHostAddress() : null,
                    null,
                    null,
                    -1,
                    null,
                    (int) nsAsEntry.getAsNumber(),
                    nsAsEntry.getAsSubnet(),
                    versionInfo,
                    hopPosition,
                    0,
                    0);

            return entity;
        }
    }



    @Override
    public void run() {
//        Integer totalHops;

        try {
//          Thread.currentThread().sleep(10000); // 1 segundo
            System.out.print("passei aqui nessa poha");


        } catch (Exception e) {
            LOGGER.error("Erro ao processar", e);
        }
    }

//    public String getDomainName() {
//        return domainName;
//    }

//    public int getPosition() {
//        return position;
//    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}