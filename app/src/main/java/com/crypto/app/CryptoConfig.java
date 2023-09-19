package com.crypto.app;

import com.crypto.web.client.BianceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CryptoConfig {

    @Bean
    BianceClient bianceClient() {
        return new BianceClient();
    }
}