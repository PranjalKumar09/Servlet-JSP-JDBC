package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

   @RequestMapping("/createUser")
    public String createUser(){
       return "success";
   }

   @RequestMapping("/")
    public String home(){
       return "home";
   }
}
