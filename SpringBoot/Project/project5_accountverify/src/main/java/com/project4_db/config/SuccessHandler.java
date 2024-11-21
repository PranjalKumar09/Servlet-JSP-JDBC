package com.project4_db.config;

import com.project4_db.entity.User;
import com.project4_db.service.UserService;
import com.project4_db.service.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {


    private final UserServiceImpl userServiceImpl;

    public SuccessHandler(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        CustomUser customUser = (CustomUser)authentication.getPrincipal();
        User user = customUser.getUser();

        if (user != null) {
            userServiceImpl.resetAttempt(user.getEmail());
        }

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/profile");
        }
        else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/user/profile");
        }

    }
}
