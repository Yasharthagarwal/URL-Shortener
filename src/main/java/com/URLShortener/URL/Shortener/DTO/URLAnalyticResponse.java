package com.URLShortener.URL.Shortener.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class URLAnalyticResponse {

    private ArrayList<LocalDateTime> timeStamp;
    private Long clicks;


}
