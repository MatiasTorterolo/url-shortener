package com.url.shortener.url_shortener.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.url.shortener.url_shortener.entities.UrlEntity;

@Repository
public interface IUrlRepository extends JpaRepository<UrlEntity, Long> {

    @Query("select u from UrlEntity u where u.longUrl = ?1")
    Optional<UrlEntity> findByLongUrl(String longUrl);

    @Query("select u from UrlEntity u where u.urlShortener = ?1")
    Optional<UrlEntity> findByUrlShortener(String urlShortener);

}
