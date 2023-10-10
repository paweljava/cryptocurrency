package com.crypto.infrastructure.persistence.database.config;

import com.crypto.domain.adapter.CryptoServiceAdapter;
import com.crypto.domain.adapter.RateServiceAdapter;
import com.crypto.domain.port.inbound.CryptoServicePort;
import com.crypto.domain.port.inbound.RateServicePort;
import com.crypto.domain.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.port.outbound.RateRepositoryPort;
import com.crypto.domain.service.CryptoService;
import com.crypto.domain.service.RateService;
import com.crypto.infrastructure.externalapi.BinanceWebSocketClient;
import com.crypto.infrastructure.persistence.database.adapter.CryptoDatabaseAdapter;
import com.crypto.infrastructure.persistence.database.adapter.RateDatabaseAdapter;
import com.crypto.infrastructure.persistence.database.repository.CryptoRepository;
import com.crypto.infrastructure.persistence.database.repository.RateRepository;
import com.crypto.infrastructure.persistence.inmemory.RateInMemoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public RateRepositoryPort rateRepositoryPort() {
        return new RateInMemoryAdapter();
    }

    @Bean
    public RateServicePort rateServicePort(RateRepositoryPort rateRepositoryPort, CryptoRepositoryPort cryptoRepositoryPort) {
        return new RateServiceAdapter(new RateService(rateRepositoryPort, binanceWebSocketClient(), cryptoRepositoryPort));
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
        return new RateService(rateRepositoryPort(rateRepository), binanceWebSocketClient(), cryptoRepositoryPort);
    }

    @Bean
    BinanceWebSocketClient binanceWebSocketClient() {
        return new BinanceWebSocketClient();
    }
    /*@Bean
    BianceClient bianceClient() {
        return new BianceClient();
    }*/
}

