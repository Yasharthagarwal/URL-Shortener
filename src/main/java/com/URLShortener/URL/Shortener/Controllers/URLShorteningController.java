package com.URLShortener.URL.Shortener.Controllers;


import com.URLShortener.URL.Shortener.Repository.URLVerificationRepo;
import com.URLShortener.URL.Shortener.Services.URLShorteningService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class URLShorteningController {


    @Autowired
    private URLShorteningService urlShorteningService;

    @Autowired
    private URLVerificationRepo urlVerificationRepo;



    @PostMapping("/shorten-url")
    public String getShortenUrl(@RequestBody String longUrl){

        return urlShorteningService.getShortUrl(longUrl);

    }

    @GetMapping("{shortId}")
    public void redirect(@PathVariable("shortId") String shortId, HttpServletResponse httpServletResponse) throws IOException {
        String longUrl = urlVerificationRepo.findByshortId(shortId);
        httpServletResponse.sendRedirect(longUrl);
    }


}
