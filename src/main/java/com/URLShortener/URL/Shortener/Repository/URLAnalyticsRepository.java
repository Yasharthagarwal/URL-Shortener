package com.URLShortener.URL.Shortener.Repository;

import com.URLShortener.URL.Shortener.Entity.URLAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLAnalyticsRepository extends JpaRepository<URLAnalytics,Long> {
}
