package com.e_notes.service;

import com.e_notes.enitity.User;
import com.e_notes.reposistory.UserReposistory;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserReposistory userReposistory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userReposistory.save(user);
    }

    @Override
    public boolean existemailCheck(String email) {
        return userReposistory.existsByEmail(email);
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }
    public void removeSessionMessage(){
        HttpSession session = ((ServletRequestAttributes)(Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest().getSession();
        session.removeAttribute("msg");
    }
}
