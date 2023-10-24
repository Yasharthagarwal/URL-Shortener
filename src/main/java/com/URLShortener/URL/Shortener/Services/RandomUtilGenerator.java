package com.URLShortener.URL.Shortener.Services;


import com.URLShortener.URL.Shortener.Repository.URLVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@Service
public class RandomUtilGenerator {

        @Autowired
        private URLVerification urlVerification;




        public String generateAlphanumeric(int length) {
            String shortId = RandomStringUtils.randomAlphanumeric(8);
            return urlVerification.hasShortId(shortId) == shortId?generateAlphanumeric(8):shortId;
        }


}
