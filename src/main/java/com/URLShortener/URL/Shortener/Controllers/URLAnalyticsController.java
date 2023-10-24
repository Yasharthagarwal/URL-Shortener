package com.URLShortener.URL.Shortener.Controllers;

import com.URLShortener.URL.Shortener.DTO.URLAnalyticResponse;
import com.URLShortener.URL.Shortener.Services.URLAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/analytics")
public class URLAnalyticsController {




    @Autowired
    private URLAnalyticsService urlAnalyticsService;


    @GetMapping("/{url}")
    public URLAnalyticResponse getAnalytics(@PathVariable("url") String url){

        return urlAnalyticsService.getAnalytics(url);

    }


}
