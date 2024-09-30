package com.url.shortener.url_shortener.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.UUID;

@Controller
public class UrlController {

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("url") String url, Model model) {

        if (isValidUrl(url)) {

            String urlShortened = shortUrl(url);

            model.addAttribute("url", url);
            model.addAttribute("show", true);
            model.addAttribute("showUrlShortened", urlShortened);

            return "homeView";

        } else {

            model.addAttribute("show", false);
            return "homeView";
        }
    }

    public boolean isValidUrl(String url) {

        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {

            return false;
        }
    }

    public String shortUrl(String url) {

        url = UUID.randomUUID().toString().replace("-", "").substring(0, 5);

        return url;
    }
}
