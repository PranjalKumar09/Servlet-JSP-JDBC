package com.spring.mvc.controller;

import com.spring.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeControler {

    @RequestMapping("/home")
    public String home(Model m) {

        return "home";
    }
    @RequestMapping("/register")
    public  ModelAndView register (Model m) {
        ModelAndView mv = new ModelAndView("register");

        return mv;  
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam("age") int age){
        System.out.println(user);
        System.out.println("Age = "+age);
        return "register";
    }




}
/*
