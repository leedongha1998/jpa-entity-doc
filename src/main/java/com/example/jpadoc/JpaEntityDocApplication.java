package com.example.jpadoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaEntityDocApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaEntityDocApplication.class, args);
    }
}
