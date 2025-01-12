package com.pranjal.service;

import com.pranjal.model.User1;
import com.pranjal.repository.User1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class User1ServiceImpl implements User1Service {
    public final User1Repository user1Repository;

    public User1ServiceImpl(User1Repository user1Repository) {
        this.user1Repository = user1Repository;
    }

    @Override
    public User1 save(User1 user1) {
        return user1Repository.save(user1);
    }

    @Override
    public User1 findById(Integer id) {
        return user1Repository.findById(id).orElse(null );
    }

    @Override
    public void delete(Integer id) {
        user1Repository.deleteById(id);
    }

    @Override
    public List<User1> findAll() {
        return user1Repository.findAll();
    }

    @Override
    public User1 update(User1 user1) {
        return user1Repository.save(user1);
    }
}
