package com.kranthi.radar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kranthi on 8/24/2017.
 */

@Controller
public class RadarController {

    @RequestMapping(value = "/")
    public String radarResults(Model model){//@ModelAttribute("essids") List<WifiSettings> essids){

        String operatingSystem = System.getProperty("os.name");
        List<WifiSettings> essids;
        if(operatingSystem.toLowerCase().indexOf("windows") >=0 ){
            essids = WindowsRadarUtility.getEssids();
        }
        else{
            essids = UnixRadarUtility.getEssids();
        }

        Collections.sort(essids);
        model.addAttribute("essids",essids);
        model.addAttribute("os",operatingSystem);

        return "results";
    }
}
