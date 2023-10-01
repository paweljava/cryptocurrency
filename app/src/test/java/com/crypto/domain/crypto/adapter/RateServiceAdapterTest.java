package com.crypto.domain.crypto.adapter;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.crypto.service.RateService;
import com.crypto.infrastructure.persistence.inmemory.crypto.CryptoInMemoryAdapter;
import com.crypto.web.client.BianceClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateServiceAdapterTest {

    private final CryptoRepositoryPort cryptoRepositoryPort = new CryptoInMemoryAdapter();
    private final CryptoServiceAdapter cryptoServiceAdapter = new CryptoServiceAdapter(new RateService(cryptoRepositoryPort, new BianceClient()));

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCrypto() {
        // given
        final var symbol = new Rate("TUSDUSDT");

        // when
        final var result = cryptoServiceAdapter.addCrypto(symbol);

        // then
        assertThat(result.getSymbol()).isNotNull();
        assertThat(result.getSymbol()).isEqualTo(symbol.getSymbol());
    }

    @Test
    void getCrypto() {
        // given
        final var symbol = new Rate("TUSDUSDT");
        cryptoRepositoryPort.save(symbol);
        // when
        final var result = cryptoServiceAdapter.getCrypto();

        // then
        assertThat(result).isNotEmpty();
        assertTrue(result.stream().anyMatch(rate -> rate.getSymbol().equals(symbol.getSymbol())));

    }
}