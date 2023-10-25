package com.URLShortener.URL.Shortener.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class URLAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dateTime")
    private LocalDateTime localDateTime;

    @Column(name = "clicks")
    private Long clicks;


    @JoinColumn(name = "urlId")
    @ManyToOne(cascade = CascadeType.ALL)
    private URLAttributes urlAttributes;

}
