package com.service;

import com.entity.User;

public interface UserService {
    int insertUser(User user);
    User loginUser(String email, String password);
}
