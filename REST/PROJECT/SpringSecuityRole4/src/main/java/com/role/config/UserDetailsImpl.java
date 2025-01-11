package com.role.config;

import com.role.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails {

    private User user;
    private List<SimpleGrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify based on your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify based on your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify based on your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify based on your logic
    }
}

