package com.project4_db.service;


import com.project4_db.entity.User;

public interface UserService {
    User saveUser(User user, String url);
    void removeSessionMessage();
    void sendEmail(User user, String path);
    boolean verifyAccount(String verficationCode);

    void increaseFailedAttemp(User user);
    void resetAttempt(String email);
    void lock(User user);
    boolean unlockAccountTimeExpired(User user);
}
