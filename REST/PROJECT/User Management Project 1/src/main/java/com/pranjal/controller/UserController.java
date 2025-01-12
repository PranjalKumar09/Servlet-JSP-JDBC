package com.pranjal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pranjal.model.User1;
import com.pranjal.service.User1Service;

@RestController
public class UserController {

    @Autowired
    User1Service user1Service;

    // Save a new user
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User1 user1) {
        try {
            System.out.println(user1);
            // Save the user
            User1 savedUser = user1Service.save(user1);

            // If the save method returns null, return an internal error
            if (savedUser == null) {
                return new ResponseEntity<>("User not saved", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Successfully saved user, return the user details
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while saving user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all users
    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User1> user1List = user1Service.findAll();
            return new ResponseEntity<>(user1List, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while fetching users: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing user
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User1 user1) {
        try {
            // Save or update the user
            User1 updatedUser = user1Service.save(user1);

            // If update fails, return internal error
            if (updatedUser == null) {
                return new ResponseEntity<>("User not updated", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Return the updated user details
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        try {
           user1Service.delete(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a user by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        try {
            User1 user1 = user1Service.findById(id);

            if (user1 == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while fetching user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}