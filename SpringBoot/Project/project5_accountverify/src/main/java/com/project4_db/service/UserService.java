package com.project4_db.service;


import com.project4_db.entity.User;

public interface UserService {
    User saveUser(User user, String url);
    void removeSessionMessage();
    void sendEmail(User user, String path);
    public boolean verifyAccount(String verficationCode);


}
