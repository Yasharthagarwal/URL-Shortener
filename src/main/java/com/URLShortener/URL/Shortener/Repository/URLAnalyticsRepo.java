package com.URLShortener.URL.Shortener.Repository;

import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface URLAnalyticsRepo extends JpaRepository<URLAnalytics,Long> {

    @Query("select u from URLAnalytics u where u.urlAttributes.shortId=:newShortId")
    ArrayList<URLAnalytics> findByUrl(@Param("newShortId") String newShortId);
}
