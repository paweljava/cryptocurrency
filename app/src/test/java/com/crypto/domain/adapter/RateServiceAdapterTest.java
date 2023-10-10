package com.crypto.domain.adapter;

import com.crypto.domain.model.Rate;
import com.crypto.domain.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.port.outbound.RateRepositoryPort;
import com.crypto.domain.service.RateService;
import com.crypto.infrastructure.externalapi.BinanceWebSocketClient;
import com.crypto.infrastructure.persistence.inmemory.CryptoInMemoryAdapter;
import com.crypto.infrastructure.persistence.inmemory.RateInMemoryAdapter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RateServiceAdapterTest {

    //private final BinanceWebSocketClient binanceWebSocketClient = mock(BinanceWebSocketClient.class);
    private final RateRepositoryPort rateRepositoryPort = new RateInMemoryAdapter();
    private final CryptoRepositoryPort cryptoRepositoryPort = new CryptoInMemoryAdapter();
    private final RateServiceAdapter rateServiceAdapter = new RateServiceAdapter(
            new RateService(rateRepositoryPort, new BinanceWebSocketClient(), cryptoRepositoryPort));
            //new RateService(rateRepositoryPort, binanceWebSocketClient, cryptoRepositoryPort));

    @Test
    void should_get_rate() {
        // given
        //final var crypto = new Crypto("BTCUSDT");
        //cryptoRepositoryPort.save(crypto);
        //when(binanceWebSocketClient.connectToServer(any())).thenReturn(new BinanceApiDto("BTCUSDT", 7.521));
        final var symbol = new Rate("BTCUSDT", 7.521);
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