package com.url.shortener.url_shortener.services;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean isValidUrl(String longUrl) {

        try {
            new URL(longUrl);
            return true;
        } catch (MalformedURLException e) {

            return false;
        }
    }
}
