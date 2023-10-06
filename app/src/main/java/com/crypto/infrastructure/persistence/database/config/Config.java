package com.crypto.infrastructure.persistence.database.config;

import com.crypto.domain.crypto.adapter.CryptoServiceAdapter;
import com.crypto.domain.crypto.adapter.RateServiceAdapter;
import com.crypto.domain.crypto.port.inbound.CryptoServicePort;
import com.crypto.domain.crypto.port.inbound.RateServicePort;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.crypto.port.outbound.RateRepositoryPort;
import com.crypto.domain.crypto.service.CryptoService;
import com.crypto.domain.crypto.service.RateService;
import com.crypto.infrastructure.persistence.database.adapter.CryptoDatabaseAdapter;
import com.crypto.infrastructure.persistence.database.adapter.RateDatabaseAdapter;
import com.crypto.infrastructure.persistence.database.repository.CryptoRepository;
import com.crypto.infrastructure.persistence.database.repository.RateRepository;
import com.crypto.infrastructure.persistence.inmemory.crypto.RateInMemoryAdapter;
import com.crypto.web.client.BianceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public RateRepositoryPort rateRepositoryPort() {
        return new RateInMemoryAdapter();
    }

    @Bean
    public RateServicePort rateServicePort(RateRepositoryPort rateRepositoryPort, CryptoRepositoryPort cryptoRepositoryPort) {
        return new RateServiceAdapter(new RateService(rateRepositoryPort, bianceClient(), cryptoRepositoryPort));
    }

    @Bean
    public RateRepositoryPort rateRepositoryPort(RateRepository rateRepository) {
        return new RateDatabaseAdapter(rateRepository);
    }

    @Bean
    public CryptoRepositoryPort cryptoRepositoryPort(CryptoRepository cryptoRepository) {
        return new CryptoDatabaseAdapter(cryptoRepository);
    }

    @Bean
    public CryptoServicePort cryptoServicePort(CryptoService cryptoService) {
        return new CryptoServiceAdapter(cryptoService);
    }

    @Bean
    public CryptoService cryptoService(CryptoRepositoryPort cryptoRepositoryPort) {
        return new CryptoService(cryptoRepositoryPort);
    }

    @Bean
    public RateService rateService(RateRepository rateRepository, CryptoRepositoryPort cryptoRepositoryPort) {
        return new RateService(rateRepositoryPort(rateRepository), bianceClient(), cryptoRepositoryPort);
    }

    @Bean
    BianceClient bianceClient() {
        return new BianceClient();
    }
}

