package com.controller;

import com.entity.User;
import com.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping(path="/registerUser", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user, HttpSession session){
        userService.insertUser(user);
        session.setAttribute("msg","Registered Successfully");
        return "redirect:/register";
    }
    @RequestMapping(path="/loginUser", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session){
        User user = userService.login(email, password);
        if(user != null){
            session.setAttribute("user" , user);
            return "profile";
        }
        session.setAttribute("msg","Invalid Email or Password");
        return "redirect:/login";
    }


}
