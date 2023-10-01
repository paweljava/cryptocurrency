package com.crypto.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.crypto.infrastructure.persistence"})
@EntityScan(basePackages = {"com.crypto.infrastructure.persistence.database"})
@ComponentScan(basePackages = {"com.crypto.infrastructure"})
public class CryptoCurrencyApp {
    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyApp.class, args);
    }
}