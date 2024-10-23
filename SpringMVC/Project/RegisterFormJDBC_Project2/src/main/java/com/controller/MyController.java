package com.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

}
