package com.file.service.impl;

import com.file.dto.UserRequest;
import com.file.model.UserDtls;
import com.file.repository.UserRepository;
import com.file.service.JwtService;
import com.file.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(UserRequest userRequest) {
        Authentication authenticate  =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(userRequest.getUsername());
        }


        return null;
    }

    @Override
    public List<UserDtls> getUserDtls() {
        return userRepository.findAll();
    }
}
