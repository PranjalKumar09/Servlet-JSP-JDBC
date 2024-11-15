package org.project03_springsecurity.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/about")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String about(){
        return "about";
    }

    @RequestMapping("/home")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String home(){
        return "home";
    }

    @RequestMapping("/signIn")
    public String sginin(){
        return "login";
    }
    @RequestMapping("/invalid")
    public String error(){
        return "error";
    }
    // Ensure spelling consistency in the controller and security config
    @GetMapping("/logout1")
    public String logout(@RequestParam(value = "logout", required = false) String logout) {

            return "logout";  // Serve the custom logout page (logout.html) if the logout parameter is true

//        return "redirect:/signIn";  // Redirect to the login page if not logged out
    }


    @RequestMapping("/profile")
    public String profile(){
        return "profile";
    }

}
