package com.URLShortener.URL.Shortener.Controllers;


import com.URLShortener.URL.Shortener.Entity.URLAttributes;
import com.URLShortener.URL.Shortener.Repository.URLVerification;
import com.URLShortener.URL.Shortener.Services.RandomUtilGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class URLShorteningController {

    private final int SHORT_ID_LENGTH = 8;


    @Autowired
    private RandomUtilGenerator randomUtilGenerator;

    @Autowired
    private URLVerification urlVerification;

    @PostMapping("/shorten-url")
    public String getShortenUrl(@RequestBody String longUrl){

        String shortId = randomUtilGenerator.generateAlphanumeric(SHORT_ID_LENGTH);
        Optional<URLAttributes> presentUrlInDB = urlVerification.hasURL(longUrl);
        LocalDateTime localDateTime = LocalDateTime.now();

        if(!presentUrlInDB.isEmpty()){
            URLAttributes urlAttributes = presentUrlInDB.get();
            ArrayList<LocalDateTime> timeStamps = urlAttributes.getTimestamps();
            timeStamps.add(localDateTime);
            urlAttributes.setTimestamps(new ArrayList<>(timeStamps));
            urlAttributes.setClicks(urlAttributes.getClicks()+1);
            urlVerification.save(urlAttributes);

            return urlAttributes.getShortId();

        }
        else{
            URLAttributes urlAttributes = new URLAttributes();
            urlAttributes.setLongUrl(longUrl);
            urlAttributes.setShortId(shortId);
            urlAttributes.setClicks(1L);
            urlAttributes.setTimestamps(new ArrayList<>(Collections.singleton(localDateTime)));
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
