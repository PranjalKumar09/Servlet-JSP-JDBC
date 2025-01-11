package com.role.config;

import com.role.model.User;
import com.role.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        System.out.println("User found: " + user.getUsername());
        System.out.println("User roles and privileges: " + user.getRole().getName());
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = Stream.concat(
                user.getRole().getPrivilleges().stream().map(priv -> new SimpleGrantedAuthority(priv.getName())),
                Stream.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()))
        ).toList();
        System.out.println("Assigned Authorities: " + simpleGrantedAuthorityList);
        userDetails.setAuthorities(simpleGrantedAuthorityList);

        return userDetails;
    }
}
