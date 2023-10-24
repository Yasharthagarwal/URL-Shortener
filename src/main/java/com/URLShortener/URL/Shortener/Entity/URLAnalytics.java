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

    private LocalDateTime localDateTime;


    @JoinColumn(name = "url_id")
    @ManyToOne
    private URLAttributes urlAttributes;

}
