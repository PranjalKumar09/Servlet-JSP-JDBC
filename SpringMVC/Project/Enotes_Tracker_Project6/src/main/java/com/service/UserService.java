package com.service;

import com.entity.User;

public interface UserService {
    public User login(String email, String password);
    public int insertUser(User user);
}
