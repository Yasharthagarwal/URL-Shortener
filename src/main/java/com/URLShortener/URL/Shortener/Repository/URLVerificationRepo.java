package com.URLShortener.URL.Shortener.Repository;

import com.URLShortener.URL.Shortener.Entity.URLAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface URLVerificationRepo extends JpaRepository<URLAttributes,Long> {


    @Query("select u.shortId from URLAttributes u where u.shortId=:newShortId")
    public String hasShortId(@Param("newShortId") String newShortId);

    @Query("select u from URLAttributes u where u.longUrl=:newLongUrl")
   public Optional<URLAttributes> hasURL(@Param("newLongUrl") String newLongUrl);

    @Query("select u.longUrl from URLAttributes u where u.shortId=:newShortId")
   public String findByshortId(@Param("newShortId") String newShortId);
}
