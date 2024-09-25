package com.url.shortener.url_shortener.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/url")
public class UrlController {

    @GetMapping("/shorten")
    public String shortenUrl() {

        return "urlShortenerView";
    }
}
