package com.URLShortener.URL.Shortener.Services;


import com.URLShortener.URL.Shortener.DTO.URLAnalyticResponse;
import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import com.URLShortener.URL.Shortener.Repository.URLAnalyticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class URLAnalyticsService {

    @Autowired
    private URLAnalyticsRepo urlAnalyticsRepo;

    public ResponseEntity<?> getAnalytics(String url) {
        try {
            if(url.length()==8) {
                String shortId = url.substring(url.length()-8);

                ArrayList<URLAnalytics> analytics = urlAnalyticsRepo.findByUrl(shortId);
                URLAnalyticResponse urlAnalyticResponse = new URLAnalyticResponse();

                if (!analytics.isEmpty()) {
                    ArrayList<LocalDateTime> timestamps = new ArrayList<>();
                    Long totalClicks = Long.valueOf(analytics.size());

                    for (URLAnalytics urlAnalytics : analytics) {
                        timestamps.add(urlAnalytics.getLocalDateTime());
                    }
                    urlAnalyticResponse.setTimeStamp(timestamps);
                    urlAnalyticResponse.setClicks(totalClicks);

                    return ResponseEntity.ok().body(urlAnalyticResponse);
                } else {
                    urlAnalyticResponse.setClicks(0L);
                    urlAnalyticResponse.setTimeStamp(new ArrayList<>());
                    return ResponseEntity.ok().body(urlAnalyticResponse);
                }
            }
            else{
                return ResponseEntity.ok().body("You have entered the wrong URL. Please enter the URL again !!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
