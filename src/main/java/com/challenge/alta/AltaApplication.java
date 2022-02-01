package com.challenge.alta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AltaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AltaApplication.class, args);
    }

}
