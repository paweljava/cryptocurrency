package com.crypto.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication//(scanBasePackages = {"com.crypto.app"})
//@EnableJpaRepositories
//@EnableFeignClients
//@AutoConfiguration
//@ComponentScan
public class CryptoCurrencyApp {
    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyApp.class, args);
    }
}