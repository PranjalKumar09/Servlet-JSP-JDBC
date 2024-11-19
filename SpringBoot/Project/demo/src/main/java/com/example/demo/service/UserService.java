package com.example.demo.service;

import com.example.demo.repostory.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserDao userDao;

    public String saveUser(){
        boolean f = userDao.saveUser();
        if (f){
            return "success";
        }
        return "fail";
    }

}
