package com.e_notes.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    protected BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF (use with caution in production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").hasRole("USER")  // Requires USER role for /user/**
//                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Requires ADMIN role for /admin/**
                        .requestMatchers("/**").permitAll()  // Allows all other requests
                ).formLogin(form -> form
                        .loginPage("/login")  // Custom login page
                        .loginProcessingUrl("/userLogin")  // URL to submit login form
                        .defaultSuccessUrl("/user/add_notes", true)  // Redirect on successful login
//                        .failureHandler(customFailureHandler)  // Custom failure handler
//                        .successHandler(successHandler)  // Custom success handler
                        .permitAll()
                );

        return http.build();
    }



}
