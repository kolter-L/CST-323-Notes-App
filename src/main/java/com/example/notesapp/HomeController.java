package com.example.notesapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 
     * @return the home page for the notes app
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
