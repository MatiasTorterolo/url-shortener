package com.url.shortener.url_shortener.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Controller
 * 
 * @author MTA
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * This method handles http GET requests in some rutes like "/", "", "/home".
     * 
     * @return The string with view name.
     */
    @GetMapping({ "", "/", "/home" })
    public String home() {

        return "homeView";
    }
}
