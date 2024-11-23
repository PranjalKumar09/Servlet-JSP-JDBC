package com.project4_db.controller;

import com.project4_db.entity.User;
import com.project4_db.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private UserRepo userRepo;
    @RequestMapping("/profile")
    public String profile(Principal principal , Model model) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);

        return "admin_profile";
    }
}
