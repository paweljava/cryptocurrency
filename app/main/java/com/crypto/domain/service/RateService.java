package com.crypto.domain.service;

import com.crypto.domain.model.Rate;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import com.crypto.infrastructure.adapters.outbound.repository.RateRepository;
import com.crypto.infrastructure.adapters.outbound.repository.entity.RateEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    private final BinanceWebSocketClient binanceWebSocketClient;
    private final RateRepository rateRepository;

    public RateService(BinanceWebSocketClient binanceWebSocketClient,
                       RateRepository rateRepository) {
        this.binanceWebSocketClient = binanceWebSocketClient;
        this.rateRepository = rateRepository;
    }

    public List<Rate> getRates() {
        final var binanceResponse = binanceWebSocketClient.getCurrencyRates();
        return binanceResponse.values().stream()
                .map(apiDto -> new Rate(apiDto.getSymbol(), apiDto.getPrice()))
                .toList();
    }

    public List<Rate> saveRates(List<Rate> rateList) {
        rateList.forEach(rate -> rateRepository.save(new RateEntity(rate.symbol(), rate.price())));
        return rateList;
    }

    /*public List<Rate> saveRates(List<Rate> rateList) {
        rateList.forEach(rate -> rateEntityToRateMapper(
                rateRepository.save(
                        rateToRateEntityMapper(
                                new Rate(rate.symbol(), rate.price())))));
        return rateList;
    }*/
}