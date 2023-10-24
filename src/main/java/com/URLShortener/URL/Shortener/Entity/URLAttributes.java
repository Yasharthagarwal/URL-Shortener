package com.URLShortener.URL.Shortener.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "url_attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class URLAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private Long id;

    @Column(name = "long_url")
    private String longUrl;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "timeStamps")
    private ArrayList<Date> timestamps;

    @Column(name = "clicks")
    private Long clicks;

}