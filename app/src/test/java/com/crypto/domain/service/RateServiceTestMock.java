package com.crypto.domain.service;

import com.crypto.domain.model.Rate;
import com.crypto.domain.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.port.outbound.RateRepositoryPort;
import com.crypto.infrastructure.externalapi.BinanceWebSocketClient;
import com.crypto.infrastructure.rest.dto.RateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.crypto.domain.service.RateListMapper.rateListMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateServiceTestMock {

    private final BinanceWebSocketClient binanceWebSocketClient = mock(BinanceWebSocketClient.class);
    private final RateRepositoryPort rateRepositoryPort = mock(RateRepositoryPort.class);
    private final CryptoRepositoryPort cryptoRepositoryPort = mock(CryptoRepositoryPort.class);
    private final RateService rateService = new RateService(rateRepositoryPort, binanceWebSocketClient, cryptoRepositoryPort);

    @Test
    void should_save_rates() {
        // given
        final var symbol = new Rate("TUSDUSDT", 9.456);
        when(rateRepositoryPort.save(any())).thenReturn(symbol);
        when(rateRepositoryPort.findAll()).thenReturn(List.of(symbol));

        // when
        final var result = rateService.getRates();

        // then
        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.get(0).symbol()).isEqualTo(symbol.symbol());
        assertThat(result.get(0).symbol()).isEqualTo("TUSDUSDT");
        assertThat(result.get(0).price()).isEqualTo(symbol.price());
        assertThat(result.get(0).price()).isEqualTo(9.456);
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().equals(symbol.symbol())));
    }

    @Test
    void should_get_rates() {
        // given
        final var btcusdt = new RateDto("BTCUSDT", 10.255);
        final var bnbusdt = new RateDto("BNBUSDT", 11.255);
        final var ethusdt = new RateDto("ETHUSDT", 12.255);
        final var xrpusdt = new RateDto("XRPUSDT", 13.255);
        final var eurusdt = new RateDto("EURUSDT", 14.255);
        final List<Rate> rates = rateListMapper(List.of(btcusdt, bnbusdt, ethusdt, xrpusdt, eurusdt));
        when(rateRepositoryPort.findAll()).thenReturn(rates);

        // when
        final var result = rateService.getRates();

        // then
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(btcusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(bnbusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(ethusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(xrpusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(eurusdt.symbol())));
        assertThat(result.get(0).price()).isEqualTo(btcusdt.price());
        assertThat(result.get(1).price()).isEqualTo(bnbusdt.price());
        assertThat(result.get(2).price()).isEqualTo(ethusdt.price());
        assertThat(result.get(3).price()).isEqualTo(xrpusdt.price());
        assertThat(result.get(4).price()).isEqualTo(eurusdt.price());
    }
}