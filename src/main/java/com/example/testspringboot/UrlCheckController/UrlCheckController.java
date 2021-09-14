package com.example.testspringboot.UrlCheckController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String siteUp = "Site is up!";
    private final String siteDown = "Site is down!";
    private final String incorrectURL = "URL is incorrect!";


    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode() / 100;
            if(responseCode/ 100 != 2 || responseCode != 3) {
                returnMessage = siteDown;
            }
            else{
                returnMessage = siteUp;
            }
        }

        catch(IOException e) {
            returnMessage = incorrectURL;
        }


        return returnMessage;
    }

}
