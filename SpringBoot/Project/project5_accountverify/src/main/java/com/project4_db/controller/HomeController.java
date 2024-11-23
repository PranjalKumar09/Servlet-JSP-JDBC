package com.project4_db.controller;

import com.project4_db.entity.User;
import com.project4_db.repository.UserRepo;
import com.project4_db.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }
     @GetMapping("/register")
    public String register() {
        return "register";
    }
     @GetMapping("/login")
    public String sginin() {
        return "login";
    }

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }

    }

    @RequestMapping(path = "/saveUser",method = RequestMethod.POST )
    public String saveUser(@ModelAttribute User user, HttpSession session) {
        User user1 = userService.saveUser(user);
        if (user1 != null) {
//            System.out.println("Successfully saved user");
            session.setAttribute("msg" , "Registered Successfully");
        }
        else
//            System.out.println("Failed to save user");
            session.setAttribute("msg" , "Failed to Register");

        return "redirect:/register";
    }


}
