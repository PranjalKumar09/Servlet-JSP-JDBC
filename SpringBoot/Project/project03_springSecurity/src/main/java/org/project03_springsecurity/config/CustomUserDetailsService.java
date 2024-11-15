package org.project03_springsecurity.config;

import org.project03_springsecurity.entity.Employee;
import org.project03_springsecurity.reposistory.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmpRepo empRepo;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = empRepo.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException(email);
        }
        else {
            return new CustomUser(employee);
        }

    }
}
