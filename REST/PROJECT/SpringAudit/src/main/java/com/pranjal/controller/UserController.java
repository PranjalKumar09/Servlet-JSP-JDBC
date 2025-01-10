package com.pranjal.controller;

import com.pranjal.model.User;
import com.pranjal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        System.out.println(user);
        User saveUser =   userRepository.save(user);
        System.out.println(saveUser.toString());
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }
}
