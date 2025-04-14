package com.icwd.userservice.service;

import com.icwd.userservice.enitity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> findAllUser();

    User getUserById(String id);


}
