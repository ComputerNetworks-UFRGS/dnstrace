package com.gslandtreter.dnstracer.server.networkUtils;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;



import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NSResolver {

    public static List<String> getDomainNS(String domainName) throws TextParseException {
        List<String> nameServers = new ArrayList<>();

        Record records[] = new Lookup(domainName, Type.NS).run();

        if (records == null) {
            return null;
        }

        for (int i = 0; i < records.length; i++) {
            nameServers.add(records[i].getAdditionalName().toString(true));
        }

        return nameServers;
    }

    private static <T extends InetAddress> T getTAddress(String hostname, Class<T> clazz) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName(hostname);

            for (InetAddress addr : addresses) {
                if (clazz.isInstance(addr)) {
                    return (T) addr;
                }
            }
            return null;
        } catch (UnknownHostException e) {
            return null;
        }
    }

    public static Inet6Address getIPv6Address(String hostname) {
        return getTAddress(hostname, Inet6Address.class);
    }

    public static Inet4Address getIPv4Address(String hostname) {
        return getTAddress(hostname, Inet4Address.class);
    }
}
