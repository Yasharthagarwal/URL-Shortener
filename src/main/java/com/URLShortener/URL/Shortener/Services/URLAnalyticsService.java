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
        String shortId = url.substring(url.length()-8);

        ArrayList<URLAnalytics> analytics = urlAnalyticsRepo.findByUrl(shortId);
        URLAnalyticResponse urlAnalyticResponse = new URLAnalyticResponse();
        ArrayList<LocalDateTime> timestamps = new ArrayList<>();
        Long totalClicks = Long.valueOf(analytics.size());

        for(URLAnalytics urlAnalytics : analytics){
            timestamps.add(urlAnalytics.getLocalDateTime());
        }
        urlAnalyticResponse.setTimeStamp(timestamps);
        urlAnalyticResponse.setClicks(totalClicks);

        return ResponseEntity.ok().body(urlAnalyticResponse);
    }


}
