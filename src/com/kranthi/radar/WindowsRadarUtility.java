package com.kranthi.radar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kranthi on 8/24/2017.
 */
public class WindowsRadarUtility {

    public static List<WifiSettings> getEssids() {

        String commandWindows = "netsh wlan show networks mode=Bssid";
        Process runtimeProcess;
        String appendText = "";

        List<WifiSettings> essids = new ArrayList<>();
        try {
            System.out.println("In Try");
            runtimeProcess = Runtime.getRuntime().exec("cmd /c " + commandWindows);
            runtimeProcess.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));

            String line;

            while((line = reader.readLine()) != null){

                if(line.indexOf("SSID") >= 0){
                    WifiSettings wifi = new WifiSettings();
                    wifi.setEssid(line.substring(line.indexOf(":")+2));
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    line = reader.readLine();
                    wifi.setIpAddress(line.substring(line.indexOf(":")+2).toUpperCase());
                    line = reader.readLine();
                    System.out.println(line + "\t"+line.indexOf(":")+"\t"+line.length()+"\t"+line.indexOf("%"));
                    Integer signal = Integer.parseInt(line.substring(line.indexOf(":")+2, line.indexOf("%")));
                    wifi.setSignal(signal/2 - 100);
                    wifi.setUpIndex(signal);
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
