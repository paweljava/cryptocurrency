package com.crypto.application;

import com.crypto.domain.model.Rate;
import com.crypto.infrastructure.adapters.outbound.http.BinanceApiDto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO Prosze napisac test
class RateQueryTest {

    private final BinanceWebSocketClient binanceWebSocketClient = mock(BinanceWebSocketClient.class);
    private final RateQuery rateQuery = new RateQuery(binanceWebSocketClient);

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
        final var result = rateQuery.getRates();

        // then
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(btcusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(bnbusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(ethusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(xrpusdt.symbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.symbol().contains(eurusdt.symbol())));
    }
}