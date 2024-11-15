package org.project03_springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Project03SpringSecurityApplication {

    public static void main(String[] args) {

        SpringApplication.run(Project03SpringSecurityApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("1234"));

        System.out.println("Just a testing ");
    }

}