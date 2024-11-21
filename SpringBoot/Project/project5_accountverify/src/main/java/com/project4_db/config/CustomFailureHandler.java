package com.project4_db.config;

import com.project4_db.entity.User;
import com.project4_db.repository.UserRepo;
import com.project4_db.service.UserService;
import com.project4_db.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String email = request.getParameter("username");
        User user = userRepo.findByEmail(email);


        if (user != null) {

            if (user.isEnabled()){

                if (user.isAccountNotLocked()){

                    if (user.getFailedAttempt() < UserServiceImpl.Attempt_time-1)
                        userService.increaseFailedAttemp(user);

                    else {
                        userService.lock(user);
                        exception = new LockedException("Your account is locked !! failed attempt");
                    }

                } else {
                    if (userService.unlockAccountTimeExpired(user)){
                        exception = new LockedException("Account is Unlocked! Please try to login");
                    } else {
                        exception = new LockedException("Account is locked! Please try after 24 hours");
                    }
                }
            }
            else{
                exception = new LockedException("Account is inactive.. Verify account");
            }
        }
        super.setDefaultFailureUrl("/login?error=");
        super.onAuthenticationFailure(request, response, exception);
    }


}
