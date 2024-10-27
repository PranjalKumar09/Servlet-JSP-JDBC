package com.service;

import com.dao.User2Dao;
import com.entity.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User2ServiceImpl implements User2Service {
    @Override
    public int insertUser(User2 user) {
        return userDao.insert(user);
    }

    @Autowired
    private User2Dao userDao;

}
