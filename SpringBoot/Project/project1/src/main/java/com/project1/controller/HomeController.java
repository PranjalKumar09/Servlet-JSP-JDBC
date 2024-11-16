package com.project1.controller;

import com.project1.enitity.User1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        // Add dynamic data to the model
        model.addAttribute("title", "Index Page");
        model.addAttribute("body", "Welcome to the homepage! This content is dynamically provided by the controller.");
        model.addAttribute("date1", new Date());
        return "index"; // Refers to the "index.html" template in the templates folder
    }
}
