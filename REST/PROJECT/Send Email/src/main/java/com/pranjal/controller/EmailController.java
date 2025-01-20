package com.pranjal.controller;


import com.pranjal.model.EmailRequest;
import com.pranjal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-mail")
    private ResponseEntity<?> sendMail(@RequestBody EmailRequest emailRequest)
    {
        try {
            emailService.sendEmail(emailRequest);

            return  new ResponseEntity<>("Email Send Succes", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Email Send Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/email")
    private ResponseEntity<?> sendMail2(@RequestParam String email, @RequestParam(required = false) MultipartFile file)
    {
        try {
            emailService.sendEmailAndAttachment(email   , file);

            return  new ResponseEntity<>("Email Send Succes", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Email Send Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/email2")
    private ResponseEntity<?> sendMail3(@RequestParam String email, @RequestParam(required = false) MultipartFile[] file)
    {
        try {
            emailService.sendEmailAndAttachment2(email   , file);

            return  new ResponseEntity<>("Email Send Succes", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Email Send Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
