package com.controller;

import com.entity.User2;
import com.service.User2Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MyController {

    @Autowired
    private User2Service user2Service;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(path = "/createUser")
    public String registerUser(@ModelAttribute User2 user, Model model) {
        user2Service.insertUser(user);

        model.addAttribute("msg", "Registered Successfully");
        return "redirect:/register"; // Redirect to register after successful registration
    }

    @RequestMapping(path = "/google")
    public String redirectPage() {
        return "redirect:https://www.google.com";
    }

    @RequestMapping(path = "/search", method = POST)
    public String searchIt(@RequestParam("keyword") String keyword) {
        String url = "https://www.google.com/search?q=" + keyword;
        return "redirect:" + url;
    }

    @RequestMapping(path = "/user/{id}")
    public String demo(@PathVariable("id") int id) {
        System.out.println(id);
        return "home";
    }

    @RequestMapping("/file_upload")
    public String fileUploadPage() {
        return "file_upload"; // Show the file upload page
    }

    @RequestMapping(path = "/fileupload", method = POST)
    public String fileUpload(@RequestParam("img") MultipartFile file, Model model, HttpServletRequest request) throws IOException {
        if (!file.isEmpty()) {
            System.out.println("File uploaded: ");
            System.out.println(file.getName());
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(file.getSize());
            System.out.println(file.getResource());

            byte[] bytes = file.getBytes();
            String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources" + File.separator + "img" + File.separator + file.getOriginalFilename();
            System.out.println(path);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(bytes);
                fileOutputStream.close();
                System.out.println("File uploaded successfully");
            }catch (Exception e){
                e.printStackTrace();
            }


        } else {
            System.out.println("No file uploaded.");
        }
        model.addAttribute("imgName", file.getOriginalFilename());
        return "file_success";
    }

}
