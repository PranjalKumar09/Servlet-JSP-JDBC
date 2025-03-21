package com.file.config;

import com.file.model.UserDtls;
import com.file.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsSercviceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDtls userDtls  = userRepository.findByUsername(username) ;
        if (userDtls == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(userDtls);
    }
}
