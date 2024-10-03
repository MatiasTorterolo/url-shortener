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

/**
 * Controller to handle url shortening
 * 
 * @author MTA
 */
@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private ValidationService validationService;

    /**
     * This method handles http POST requests on the rute /shorten
     * 
     * @param longUrl The url address to shorten
     * @param model   The model for the view
     * @return The home view
     */
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

    /**
     * This method handles http GET requests to redirect a short url to long url
     * (url original)
     * 
     * @param shortUrl The short url
     * @param model    The model for the view
     * @return A RedirectView to longUrl (original url) or homeView if the url is
     *         invalid.
     */
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
