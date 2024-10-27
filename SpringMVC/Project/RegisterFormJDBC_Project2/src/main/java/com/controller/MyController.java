package com.controller;


import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @Autowired
    UserService userService;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @RequestMapping(path="/createUser")
    public String registerUser(@ModelAttribute User user, Model model){
        userService.insertUser(user);
//        model.addAttribute("msg", "Registered Successfully");
//        return "redirect:/register";
        model.addAttribute("user", user);
        return "success";
    }

}
