package org.project03_springsecurity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService customUserDetailsService) {
        return customUserDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF (for testing)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Require authentication for all requests
                )
                .formLogin(form -> form
                        .loginPage("/sginIn") // Custom login page URL
                        .loginProcessingUrl("/login") // Custom login processing URL
                        .defaultSuccessUrl("/profile", true) // Redirect to profile after successful login
                        .permitAll() // Allow access to login page for everyone
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Trigger logout on /logout URL
                        .logoutSuccessUrl("/sginIn?logout=true") // Redirect to login page after logout
                        .invalidateHttpSession(true) // Invalidate the session on logout
                        .clearAuthentication(true) // Clear authentication context
                );

        return http.build();
    }

*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for testing (remove in production for security)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signIn", "/login").permitAll()  // Allow access to login and sign-in URLs
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .formLogin(form -> form
                        .loginPage("/signIn")  // Custom login page URL
                        .loginProcessingUrl("/login")  // Custom login processing URL
                        .defaultSuccessUrl("/profile", true)  // Redirect to profile after successful login
//                        .failureUrl("/invalid")
                        .permitAll()  // Allow access to login page for everyone
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout1", "POST"))
                        .logoutSuccessUrl("/logout1?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .addLogoutHandler(new CookieClearingLogoutHandler("JSESSIONID"))  // Explicitly clear the JSESSIONID cookie
                        .permitAll()
                );

        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService); // Inject CustomUserDetailsService here
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}
