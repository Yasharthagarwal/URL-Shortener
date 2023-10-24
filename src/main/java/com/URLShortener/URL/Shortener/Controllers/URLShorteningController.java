package com.URLShortener.URL.Shortener.Controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class URLShorteningController {

    @PostMapping("/shorten-url")
    public String getShortenUrl(@RequestBody String longUrl){
        System.out.println(longUrl);
        return longUrl;
    }


}
