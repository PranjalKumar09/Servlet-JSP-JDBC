package com.e_notes.controller;

import com.e_notes.enitity.User;
import com.e_notes.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session) {
//        System.out.println(user);
        User user1 = userService.addUser(user);
        if (user1 != null) {
            session.setAttribute("msg", "Successfully registered");
        }
        else session.setAttribute("msg", "Failed to register");
        return "redirect:/register";
    }




}
