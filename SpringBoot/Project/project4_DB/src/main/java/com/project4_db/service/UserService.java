package com.project4_db.service;


import com.project4_db.entity.User;

public interface UserService {
    User saveUser(User user);
    void removeSessionMessage();


}
