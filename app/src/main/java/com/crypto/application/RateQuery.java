package com.crypto.application;

import com.crypto.domain.model.Rate;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateQuery {

    private final BinanceWebSocketClient binanceWebSocketClient;

    public RateQuery(BinanceWebSocketClient binanceWebSocketClient) {
        this.binanceWebSocketClient = binanceWebSocketClient;
    }

    public List<Rate> getRates() {
        final var binanceResponse = binanceWebSocketClient.getCurrencyRates();
        return binanceResponse.values().stream()
                .map(apiDto -> new Rate(apiDto.getSymbol(), apiDto.getPrice()))
                .toList();
    }
}
