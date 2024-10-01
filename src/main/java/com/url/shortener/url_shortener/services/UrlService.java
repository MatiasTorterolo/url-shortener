package com.url.shortener.url_shortener.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortener.url_shortener.entities.UrlEntity;
import com.url.shortener.url_shortener.repositories.IUrlRepository;

@Service
public class UrlService {

    @Autowired
    private IUrlRepository iUrlRepository;

    public UrlEntity createUrlEntity(UrlEntity entity) {

        return iUrlRepository.save(entity);
    }

    public String generateShortUrl(String longUrl) {

        String shortUrl = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();

        UrlEntity urlEntity = new UrlEntity(longUrl, shortUrl);

        createUrlEntity(urlEntity);

        return shortUrl;
    }

    public Optional<UrlEntity> findByShortUrl(String shortUrl) {

        return iUrlRepository.findByUrlShortenerOptional(shortUrl);
    }
}
