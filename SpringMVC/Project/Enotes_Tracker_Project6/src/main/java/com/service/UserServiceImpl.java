package com.service;

import com.entity.User;
import com.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User login(String email, String password) {
        return userDao.login(email, password);
    }
}
