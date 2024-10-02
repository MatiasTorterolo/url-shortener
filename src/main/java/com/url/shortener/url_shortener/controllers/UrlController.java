package com.url.shortener.url_shortener.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.url.shortener.url_shortener.entities.UrlEntity;
import com.url.shortener.url_shortener.services.UrlService;
import com.url.shortener.url_shortener.services.ValidationService;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private ValidationService validationService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("url") String longUrl, Model model) {

        if (validationService.isValidUrl(longUrl)) {

            String urlShortened = urlService.generateShortUrl(longUrl);

            model.addAttribute("show", true);
            model.addAttribute("showUrlShortened", urlShortened);

        } else {
            model.addAttribute("errorMessage",
                    "Invalid URL address, try again, the url must have the protocol 'http'.");
            model.addAttribute("error", true);
        }

        return "homeView";
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToLongUrl(@PathVariable("shortUrl") String shortUrl, Model model) {

        Optional<UrlEntity> urlEntityOptional = urlService.findByShortUrl(shortUrl);

        if (urlEntityOptional.isPresent()) {
            String longUrl = urlEntityOptional.get().getLongUrl();

            return new RedirectView(longUrl);
        } else {
            model.addAttribute("errorMesagge", "Invalid URL address");
            model.addAttribute("error", true);
            return new RedirectView("/home");
        }

    }

}
