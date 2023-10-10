package com.crypto.domain.service;

import com.crypto.domain.model.Rate;
import com.crypto.infrastructure.adapters.inbound.dto.RateDto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import com.crypto.infrastructure.adapters.outbound.repository.CryptoRepository;
import com.crypto.infrastructure.adapters.outbound.repository.RateRepository;
import com.crypto.infrastructure.adapters.outbound.repository.entity.EntityMapper;

import java.util.List;

import static com.crypto.domain.model.Mapper.rateDtoListMapper;
import static com.crypto.infrastructure.adapters.outbound.repository.entity.EntityMapper.rateEntityToRateMapper;
import static com.crypto.infrastructure.adapters.outbound.repository.entity.EntityMapper.rateToRateEntityMapper;
import static java.util.stream.Collectors.toSet;

public class RateService {

    private final BinanceWebSocketClient binanceWebSocketClient;
    private final RateRepository rateRepository;
    private final CryptoRepository cryptoRepository;

    public RateService(BinanceWebSocketClient binanceWebSocketClient,
                       RateRepository rateRepository,
                       CryptoRepository cryptoRepository) {
        this.binanceWebSocketClient = binanceWebSocketClient;
        this.rateRepository = rateRepository;
        this.cryptoRepository = cryptoRepository;
    }

    public List<RateDto> getRates() {
        final var currencies = cryptoRepository.findAll().stream()
                .map(EntityMapper::cryptoToCryptoEntityMapper)
                .map(a -> a.symbol().toLowerCase() + "@trade")
                .collect(toSet());

        final var binanceResponse = binanceWebSocketClient.connectToServer(currencies);
        final var results = binanceResponse.values().stream()
                .map(dto -> new Rate(dto.getSymbol(), dto.getPrice())).toList();
        return rateDtoListMapper(results);
    }

    public List<RateDto> saveRates(List<RateDto> rateDtoList) {
        rateDtoList.forEach(rate -> rateToRateEntityMapper(rateRepository.save(rateEntityToRateMapper(new Rate(rate.symbol(), rate.price())))));
        return rateDtoList;
    }
}
