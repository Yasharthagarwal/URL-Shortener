package com.URLShortener.URL.Shortener.Services;


import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import com.URLShortener.URL.Shortener.Entity.URLAttributes;
import com.URLShortener.URL.Shortener.Repository.URLAnalyticsRepo;
import com.URLShortener.URL.Shortener.Repository.URLVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class URLShorteningService {

        @Autowired
        private URLVerificationRepo urlVerificationRepo;

        @Autowired
        private URLAnalyticsRepo urlAnalyticsRepo;

        private final int SHORT_ID_LENGTH = 8;




        public String generateAlphanumeric(int length) {
            String shortId = RandomStringUtils.randomAlphanumeric(SHORT_ID_LENGTH);
            return urlVerificationRepo.hasShortId(shortId) == shortId?generateAlphanumeric(SHORT_ID_LENGTH):shortId;
        }


        public String getShortUrl(String longUrl) {
            String shortId = generateAlphanumeric(SHORT_ID_LENGTH);
            Optional<URLAttributes> presentUrlInDB = urlVerificationRepo.hasURL(longUrl);
            LocalDateTime localDateTime = LocalDateTime.now();

            if(!presentUrlInDB.isEmpty()){
                URLAttributes urlAttributes = presentUrlInDB.get();

                URLAnalytics urlAnalytics = new URLAnalytics();
                urlAnalytics.setLocalDateTime(localDateTime);
                urlAnalytics.setUrlAttributes(urlAttributes);

                urlAttributes.getUrlAnalytics().add(urlAnalytics);
                urlAttributes.setClicks(urlAttributes.getClicks()+1);

                urlAnalyticsRepo.save(urlAnalytics);
                urlVerificationRepo.save(urlAttributes);

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
                urlAttributes.setUrlAnalytics(urlAnalyticsSet);


                urlAnalyticsRepo.save(urlAnalytics);
                urlVerificationRepo.save(urlAttributes);

                return shortId;

            }
    }
}
