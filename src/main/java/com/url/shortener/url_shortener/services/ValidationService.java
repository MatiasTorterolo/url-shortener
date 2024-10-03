package com.url.shortener.url_shortener.services;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

/**
 * This service is responsible for validating the url addresses.
 * 
 * @author MTA
 */
@Service
public class ValidationService {

    /**
     * This method validates the long url.
     * 
     * @param longUrl A long url.
     * @return True if valid or false if invalid.
     */
    public boolean isValidUrl(String longUrl) {

        try {
            new URL(longUrl);
            return true;
        } catch (MalformedURLException e) {

            return false;
        }
    }
}
