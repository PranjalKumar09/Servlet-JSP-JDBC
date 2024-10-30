package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/")
public class UserController {
    @RequestMapping("/add_notes")
    public String add_notes() {
        return "add_notes";
    }
    @RequestMapping("/view_notes")
    public String view_notes() {
        return "view_notes";
    }
    @RequestMapping("/edit_notes")
    public String edit_notes() {
        return "edit_notes";
    }
}
