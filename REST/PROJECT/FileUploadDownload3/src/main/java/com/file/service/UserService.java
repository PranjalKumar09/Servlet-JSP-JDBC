package com.file.service;

import com.file.dto.UserRequest;
import com.file.model.UserDtls;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String login(UserRequest userRequest);
    List<UserDtls> getUserDtls();

}
