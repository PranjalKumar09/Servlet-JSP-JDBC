package com.pranjal;

import com.pranjal.config.AuditorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "awareRef")
public class SpringAuditApplication {

    @Bean
    public AuditorAware<Integer> awareRef() {
        return new AuditorConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAuditApplication.class, args);
    }

}
