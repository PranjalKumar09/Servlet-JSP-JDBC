package com.pranjal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileApplication implements CommandLineRunner {

    // This will automatically inject the active profile value from the application context
    @Value("${my.config}")
    private String myconfig;

    @Value("${spring.profiles.active[0]}")
    private String profile;

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Print the active profile to the console
        System.out.println("Active myconfig: " + myconfig);
    }
}
