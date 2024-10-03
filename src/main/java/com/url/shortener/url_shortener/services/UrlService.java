package com.url.shortener.url_shortener.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortener.url_shortener.entities.UrlEntity;
import com.url.shortener.url_shortener.repositories.IUrlRepository;

/**
 * This service handles the logic to create and find url entities, it also check
 * if url exist or not in the data base.
 * 
 * @author MTA
 */
@Service
public class UrlService {

    @Autowired
    private IUrlRepository iUrlRepository;

    /**
     * This method create a new url entity.
     * 
     * @param entity A url entity.
     * @return The saved url entity.
     */
    public UrlEntity createUrlEntity(UrlEntity entity) {

        return iUrlRepository.save(entity);
    }

    /**
     * This method creates a short url from the long url, if the long url exists the
     * saved short url will be shown.
     * 
     * @param longUrl A long url.
     * @return The short url linked to the long url.
     */
    public String generateShortUrl(String longUrl) {

        Optional<UrlEntity> entity = iUrlRepository.findByLongUrl(longUrl);

        if (entity.isPresent()) {

            return entity.get().getUrlShortener();
        } else {

            String shortUrl = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();

            UrlEntity urlEntity = new UrlEntity(longUrl, shortUrl);

            createUrlEntity(urlEntity);

            return shortUrl;
        }

    }

    /**
     * This method searches the url on the data base.
     * 
     * @param shortUrl The short url.
     * @return An optional url entity empty or present.
     */
    public Optional<UrlEntity> findByShortUrl(String shortUrl) {

        return iUrlRepository.findByUrlShortener(shortUrl);
    }
}
