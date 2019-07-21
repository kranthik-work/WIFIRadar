package com.kranthi.radar;

/**
 * Created by Kranthi on 8/24/2017.
 */
public class WifiSettings implements Comparable<WifiSettings> {

    String ipAddress;
    String essid;
    Integer signal;
    Integer upIndex;
    Integer downIndex;

    public WifiSettings() {

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEssid() {
        return essid;
    }

    public void setEssid(String essid) {
        this.essid = essid;
    }

    public Integer getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }

    public Integer getUpIndex() {
        return upIndex;
    }

    public void setUpIndex(Integer upIndex) {
        this.upIndex = upIndex;
    }

    public Integer getDownIndex() {
        return downIndex;
    }

    public void setDownIndex(Integer downIndex) {
        this.downIndex = downIndex;
    }

    @Override
    public int compareTo(WifiSettings o) {
        return o.signal - this.signal;
    }
}
