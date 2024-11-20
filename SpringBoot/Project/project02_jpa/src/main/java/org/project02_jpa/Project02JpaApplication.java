package org.project02_jpa;

import org.project02_jpa.repository.EmpRepo;
import org.project02_jpa.repository.MobileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project02JpaApplication implements CommandLineRunner{

    @Autowired
    private EmpRepo empRepo;

    @Autowired
    private MobileRepo mobileRepo;

    public static void main(String[] args) {
        SpringApplication.run(Project02JpaApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

    }
}
