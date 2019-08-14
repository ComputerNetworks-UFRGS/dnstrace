package com.gslandtreter.dnstracer.server.asn;

import com.gslandtreter.dnstracer.server.networkUtils.IPUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ASNDatabase {

    public static final Logger LOGGER = LogManager.getLogger();

    private HashMap<Long, Long> subnets;
    private HashMap<Long, String> names;

    private void loadSubnetFile(File file) throws IOException {
        subnets = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String tokens[] = line.split("\\s+");

                String ip = tokens[0].split("/")[0];
                long ipLong = IPUtils.ipToLong(ip);
                subnets.put(ipLong, Long.parseLong(tokens[1]));
            }
        }
    }

    private void loadNamesDatabase(File file) throws IOException {
        names = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String tokens[] = line.trim().split("\\s+", 2);
                names.put(Long.parseLong(tokens[0]), tokens[1]);
            }
        }
    }

    public ASEntry getASEntry(String ip) {

        long ipLong = IPUtils.ipToLong(ip);

        for (int i = 0; i < 32; i++) {
            long ipToTest = (ipLong >> i) << i;

            if (subnets.containsKey(ipToTest)) {

                ASEntry entry = new ASEntry();
                entry.setAsNumber(subnets.get(ipToTest));

                entry.setAsSubnet(
                        String.format("%s/%d", IPUtils.longToIp(ipToTest), 32 - i)
                );

                return entry;
            }
        }
        return null;
    }

    public String getAsName(long asn) {
        return names.getOrDefault(asn, null);
    }

    public String getAsName(ASEntry as) {
        return names.getOrDefault(as.getAsNumber(), null);
    }

    public ASNDatabase(File asnVsSubnetFile, File asnVsNameFile) throws IOException {

        LOGGER.info("Loading subnet database...");
        loadSubnetFile(asnVsSubnetFile);
        LOGGER.info("Subnet database loaded!");

        LOGGER.info("Loading AS Names database...");
        loadNamesDatabase(asnVsNameFile);
        LOGGER.info("Loading AS Names database loaded!");
    }
}
