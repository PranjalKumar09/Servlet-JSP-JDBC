package com.pranjal.service;

import com.pranjal.model.User1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface User1Service {
     User1 save(User1 user1);
    List<User1> findAll();
    User1 update(User1 user1);
    void delete(Integer id);
    User1 findById(Integer id);


}
