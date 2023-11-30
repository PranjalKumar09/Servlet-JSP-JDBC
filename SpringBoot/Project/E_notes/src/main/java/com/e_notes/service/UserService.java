package com.e_notes.service;

import com.e_notes.enitity.User;

public interface UserService {
    User addUser(User user);
    boolean updateUser(User user);
    boolean existemailCheck(String email);
}
