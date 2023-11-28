package com.e_notes.config;

import com.e_notes.enitity.User;
import com.e_notes.reposistory.UserReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserReposistory userReposistory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReposistory.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        else  return  new CustomUser(user);
    }
}
