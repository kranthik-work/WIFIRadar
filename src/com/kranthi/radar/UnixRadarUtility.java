package com.kranthi.radar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kranthi on 8/24/2017.
 */
public class UnixRadarUtility {

    public static List<WifiSettings> getEssids() {

        String commandUnix = "sudo iwlist scanning";
        Process runtimeProcess;
        String appendText = "";

        List<WifiSettings> essids = new ArrayList<>();
        try {
            System.out.println("In Try");
            runtimeProcess = Runtime.getRuntime().exec(commandUnix);
            runtimeProcess.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));

            String line;

            while((line = reader.readLine()) != null){

                if(line.indexOf("Address") >= 0){
                    WifiSettings wifi = new WifiSettings();
                    wifi.setIpAddress(line.substring(line.indexOf(":")+2));
                    reader.readLine();
                    reader.readLine();
                    line = reader.readLine();
                    wifi.setSignal(Integer.parseInt(line.substring(line.lastIndexOf("=")+1, line.length()-6)));
                    int indexOfSlash = line.indexOf("/");
                    wifi.setUpIndex(Integer.parseInt(line.substring(indexOfSlash-2,indexOfSlash)));
                    wifi.setDownIndex(Integer.parseInt(line.substring(indexOfSlash+1,indexOfSlash+2)));
                    reader.readLine();
                    line = reader.readLine();
                    wifi.setEssid(line.substring(line.indexOf(":")+2,line.lastIndexOf("\"")));
                    essids.add(wifi);
                }
            }

            System.out.println("End of Try");

        } catch (IOException e) {
            System.out.println("In IOE");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("In IE");
            e.printStackTrace();
        }

        for(WifiSettings wifi: essids){
            System.out.println("ESSID: "+ wifi.getEssid()+" IP: "+wifi.getIpAddress()+" Signal: "+wifi.getSignal());
        }

        return essids;
    }
}
