package com.URLShortener.URL.Shortener.Controllers;


import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import com.URLShortener.URL.Shortener.Entity.URLAttributes;
import com.URLShortener.URL.Shortener.Repository.URLAnalyticsRepo;
import com.URLShortener.URL.Shortener.Repository.URLVerificationRepo;
import com.URLShortener.URL.Shortener.Services.URLShorteningService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class URLShorteningController {


    @Autowired
    private URLShorteningService urlShorteningService;

    @Autowired
    private URLVerificationRepo urlVerificationRepo;

    @Autowired
    private URLAnalyticsRepo urlAnalyticsRepo;



    @PostMapping("/shorten-url")
    public ResponseEntity<?> getShortenUrl(@RequestBody String longUrl){

        return urlShorteningService.getShortUrl(longUrl);

    }

    @GetMapping("{shortId}")
    public void redirect(@PathVariable("shortId") String shortId, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(urlShorteningService.saveAnalytics(shortId).getLongUrl());
    }


}
