package com.role.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    @GetMapping("/")
    public ResponseEntity<?> saveProduct(){
        String msg = "Page Opene";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

}
