package com.crypto.domain.service;

import com.crypto.domain.model.Rate;
import com.crypto.infrastructure.adapters.inbound.dto.RateDto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceApiDto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import com.crypto.infrastructure.adapters.outbound.repository.RateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateServiceTest {

    private final BinanceWebSocketClient binanceWebSocketClient = mock(BinanceWebSocketClient.class);
    private final RateRepository rateRepository = mock(RateRepository.class);
    private final RateService rateService = new RateService(binanceWebSocketClient, rateRepository);

    @Test
    void should_save_rates() {
        // given
        final var symbol = new Rate("TUSDUSDT", 9.456);
        final List<Rate> rateList = List.of(symbol);

        // when
        final var result = rateService.saveRates(rateList);

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
        final var btcusdt = new Rate("BTCUSDT", 10.255);
        final var bnbusdt = new Rate("BNBUSDT", 11.255);
        final var ethusdt = new Rate("ETHUSDT", 12.255);
        final var xrpusdt = new Rate("XRPUSDT", 13.255);
        final var eurusdt = new Rate("EURUSDT", 14.255);
        final Map<String, BinanceApiDto> rates = Map.of(
                btcusdt.symbol(), new BinanceApiDto(btcusdt.symbol(), btcusdt.price()),
                bnbusdt.symbol(), new BinanceApiDto(bnbusdt.symbol(), bnbusdt.price()),
                ethusdt.symbol(), new BinanceApiDto(ethusdt.symbol(), ethusdt.price()),
                xrpusdt.symbol(), new BinanceApiDto(xrpusdt.symbol(), xrpusdt.price()),
                eurusdt.symbol(), new BinanceApiDto(eurusdt.symbol(), eurusdt.price()));

        when(binanceWebSocketClient.getCurrencyRates()).thenReturn(rates);

        // when
        final var result = rateService.getRates();

        // then
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(btcusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(bnbusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(ethusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(xrpusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(eurusdt.symbol())));
    }
}