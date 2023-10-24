package com.URLShortener.URL.Shortener.Services;


import com.URLShortener.URL.Shortener.DTO.URLAnalyticResponse;
import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import com.URLShortener.URL.Shortener.Repository.URLAnalyticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class URLAnalyticsService {

    @Autowired
    private URLAnalyticsRepo urlAnalyticsRepo;

    public URLAnalyticResponse getAnalytics(String url) {
        ArrayList<URLAnalytics> analytics = urlAnalyticsRepo.findByUrl(url);
        URLAnalyticResponse urlAnalyticResponse = new URLAnalyticResponse();
        ArrayList<LocalDateTime> timestamps = new ArrayList<>();

        for(URLAnalytics urlAnalytics : analytics){
            timestamps.add(urlAnalytics.getLocalDateTime());
        }
        urlAnalyticResponse.setTimeStamp(timestamps);
        urlAnalyticResponse.setClicks(analytics.get(0).getUrlAttributes().getClicks());

        return urlAnalyticResponse;
    }


}
