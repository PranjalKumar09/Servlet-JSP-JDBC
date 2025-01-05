package com.file.config;

import com.file.model.UserDtls;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private UserDtls userDtls;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return userDtls.getPassword();
    }

    @Override
    public String getUsername() {
        return userDtls.getUsername();
    }

    public MyUserDetails() {
        super();
    }

    public MyUserDetails(UserDtls userDtls) {
        super();
        this.userDtls = userDtls;
    }
    public UserDtls getUserDtls() {
        return userDtls;
    }

    public void setUserDtls(UserDtls userDtls) {
        this.userDtls = userDtls;
    }


}
