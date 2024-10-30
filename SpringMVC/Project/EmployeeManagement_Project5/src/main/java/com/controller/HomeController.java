package com.controller;

import com.entity.Emp;
import com.entity.User;
import com.service.EmpService;
import com.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private EmpService empService;
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        List<Emp> list = empService.getEmpList();
        model.addAttribute("empList", list);
        return "home";
    }

    @RequestMapping("/addEmp")
    public String addEmp() {
        return "AddEmp";
    }
    @RequestMapping(path = "/createEmp", method = RequestMethod.POST)
    public String createEmp(@ModelAttribute Emp emp, HttpSession session) {

        empService.addEmp(emp);
        session.setAttribute("msg", "Registered Successfully");
        return "redirect:/addEmp";
    }
    @RequestMapping("/edit_emp/{id}")
    public String edit_emp(@PathVariable("id") int id, Model model) {
        model.addAttribute("emp",empService.getEmpById(id));
        return "edit_emp";
    }
    @RequestMapping(path = "/updateEmp", method = RequestMethod.POST)
    public String updateEmp(HttpSession session, @ModelAttribute Emp emp) {
        empService.updateEmp(emp);
        session.setAttribute("msg", "Updated Successfully");
        return "redirect:/home";
    }

    @RequestMapping("/deleteEmp/{id}")
    public String deleteEmp(HttpSession session,@PathVariable("id") int id) {
        empService.deleteEmp( empService.getEmpById(id));
        session.setAttribute("msg", "Deleted  Successfully");
        return "redirect:/home";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user, HttpSession session) {
        userService.insertUser(user);
        session.setAttribute("msg", "User registered Successfully");
        return "redirect:/register";
    }
    @RequestMapping(path = "/loginUser", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email")  String email, @RequestParam ("password") String password , HttpSession model) {
        User user = userService.loginUser(email, password);
       if (user !=null) {
           model.setAttribute("user",user);
           return "profile";
       };
       model.setAttribute("msg", "Login Failed");
        return "redirect:/login";
    }
    @RequestMapping("/myProfile")
    public String profile(){
        return "profile";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


}
