package com.URLShortener.URL.Shortener.Services;


import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import com.URLShortener.URL.Shortener.Entity.URLAttributes;
import com.URLShortener.URL.Shortener.Repository.URLAnalyticsRepo;
import com.URLShortener.URL.Shortener.Repository.URLVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            try {
                String shortId = RandomStringUtils.randomAlphanumeric(SHORT_ID_LENGTH);
                return urlVerificationRepo.hasShortId(shortId) == shortId?generateAlphanumeric(SHORT_ID_LENGTH):shortId;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        public ResponseEntity<?> getShortUrl(String longUrl) {
            try {
                String shortId = generateAlphanumeric(SHORT_ID_LENGTH);
                Optional<URLAttributes> presentUrlInDB = urlVerificationRepo.hasURL(longUrl);

                if(!presentUrlInDB.isEmpty()){
                    URLAttributes urlAttributes = presentUrlInDB.get();

                    return ResponseEntity.ok().body("http://localhost:8080/" + urlAttributes.getShortId());
                }
                else{
                    URLAttributes urlAttributes = new URLAttributes();
                    urlAttributes.setLongUrl(longUrl);
                    urlAttributes.setShortId(shortId);

                    urlVerificationRepo.save(urlAttributes);

                    return ResponseEntity.ok().body("http://localhost:8080/" + shortId);

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public URLAttributes saveAnalytics(String shortId) {
            try {
                Optional<URLAttributes> urlObject = urlVerificationRepo.findByshortId(shortId);
                LocalDateTime localDateTime = LocalDateTime.now();
                URLAnalytics urlAnalytics = new URLAnalytics();
                urlAnalytics.setClicks(1L);
                urlAnalytics.setLocalDateTime(localDateTime);
                urlAnalytics.setUrlAttributes(urlObject.get());
                urlObject.get().getUrlAnalytics().add(urlAnalytics);
                urlAnalyticsRepo.save(urlAnalytics);

                urlVerificationRepo.save(urlObject.get());

                return urlObject.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
