package com.crypto.domain.crypto.adapter;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.crypto.port.outbound.RateRepositoryPort;
import com.crypto.domain.crypto.service.RateService;
import com.crypto.infrastructure.persistence.inmemory.crypto.CryptoInMemoryAdapter;
import com.crypto.infrastructure.persistence.inmemory.crypto.RateInMemoryAdapter;
import com.crypto.web.client.BianceClient;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateServiceAdapterTest {

    private final RateRepositoryPort rateRepositoryPort = new RateInMemoryAdapter();
    private final CryptoRepositoryPort cryptoRepositoryPort = new CryptoInMemoryAdapter();
    private final RateServiceAdapter rateServiceAdapter = new RateServiceAdapter(
            new RateService(rateRepositoryPort, new BianceClient(), cryptoRepositoryPort));

    @Test
    void should_get_rate() {
        // given
        final var symbol = new Rate("TUSDUSDT", 7.521);
        rateRepositoryPort.save(symbol);

        // when
        final var result = rateServiceAdapter.getRates();

        // then
        assertThat(result).isNotEmpty();
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().equals(symbol.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(symbol.symbol())));
        assertThat(result.get(0).price()).isEqualTo(symbol.price());
    }
}