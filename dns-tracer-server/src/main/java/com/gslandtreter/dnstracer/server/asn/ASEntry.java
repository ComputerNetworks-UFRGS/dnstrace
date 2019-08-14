package com.gslandtreter.dnstracer.server.asn;

public class ASEntry {

    private long asNumber;
    private String asSubnet;

    public long getAsNumber() {
        return asNumber;
    }

    public void setAsNumber(long asNumber) {
        this.asNumber = asNumber;
    }

    public String getAsSubnet() {
        return asSubnet;
    }

    public void setAsSubnet(String asSubnet) {
        this.asSubnet = asSubnet;
    }

    @Override
    public String toString() {
        return "ASEntry{" +
                "asNumber=" + asNumber +
                ", asSubnet='" + asSubnet + '\'' +
                '}';
    }
}

