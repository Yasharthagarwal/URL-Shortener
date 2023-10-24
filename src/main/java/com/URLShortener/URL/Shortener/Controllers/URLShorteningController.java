package com.URLShortener.URL.Shortener.Controllers;


import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import com.URLShortener.URL.Shortener.Entity.URLAttributes;
import com.URLShortener.URL.Shortener.Repository.URLAnalyticsRepository;
import com.URLShortener.URL.Shortener.Repository.URLVerification;
import com.URLShortener.URL.Shortener.Services.RandomUtilGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/")
public class URLShorteningController {

    private final int SHORT_ID_LENGTH = 8;


    @Autowired
    private RandomUtilGenerator randomUtilGenerator;

    @Autowired
    private URLVerification urlVerification;

    @Autowired
    private URLAnalyticsRepository urlAnalyticsRepo;

    @PostMapping("/shorten-url")
    public String getShortenUrl(@RequestBody String longUrl){

        String shortId = randomUtilGenerator.generateAlphanumeric(SHORT_ID_LENGTH);
        Optional<URLAttributes> presentUrlInDB = urlVerification.hasURL(longUrl);
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime);

        if(!presentUrlInDB.isEmpty()){
            URLAttributes urlAttributes = presentUrlInDB.get();
            URLAnalytics urlAnalytics = new URLAnalytics();
            urlAnalytics.setLocalDateTime(localDateTime);
            urlAnalytics.setUrlAttributes(urlAttributes);
            urlAnalyticsRepo.save(urlAnalytics);
            urlAttributes.getUrlAnalytics().add(urlAnalytics);
            urlAttributes.setClicks(urlAttributes.getClicks()+1);
            urlVerification.save(urlAttributes);

            return urlAttributes.getShortId();

        }
        else{
            URLAttributes urlAttributes = new URLAttributes();
            urlAttributes.setLongUrl(longUrl);
            urlAttributes.setShortId(shortId);
            urlAttributes.setClicks(1L);
            URLAnalytics urlAnalytics = new URLAnalytics();
            urlAnalytics.setLocalDateTime(localDateTime);
            Set<URLAnalytics> urlAnalyticsSet = new HashSet<>();
            urlAnalyticsSet.add(urlAnalytics);
            urlAnalytics.setUrlAttributes(urlAttributes);
            urlAnalyticsRepo.save(urlAnalytics);
            urlAttributes.setUrlAnalytics(urlAnalyticsSet);
            urlVerification.save(urlAttributes);

            return shortId;

        }



    }

    @RequestMapping(value = "{shortId}", method = RequestMethod.GET)
    public void redirectToTwitter(@PathVariable("shortId") String shortId, HttpServletResponse httpServletResponse) throws IOException {
        String longUrl = urlVerification.findByshortId(shortId);

        httpServletResponse.sendRedirect(longUrl);
    }


}
