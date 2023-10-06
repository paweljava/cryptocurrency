package com.crypto.domain.crypto.service;

import com.crypto.infrastructure.application.rest.dto.RateDto;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.crypto.port.outbound.RateRepositoryPort;
import com.crypto.web.client.BianceClient;

import java.util.List;

import static com.crypto.domain.crypto.model.Mapper.*;

public class RateService {

    private final BianceClient bianceClient;
    private final CryptoRepositoryPort cryptoRepositoryPort;
    private final RateRepositoryPort rateRepositoryPort;

    public RateService(RateRepositoryPort rateRepositoryPort, BianceClient bianceClient, CryptoRepositoryPort cryptoRepositoryPort) {
        this.bianceClient = bianceClient;
        this.rateRepositoryPort = rateRepositoryPort;
        this.cryptoRepositoryPort = cryptoRepositoryPort;
    }

    public List<RateDto> getRates() {
        final var currencies = mapper(cryptoRepositoryPort.findAll().stream()
                .map(crypto -> bianceClient.getCurrencyBySymbol(crypto.symbol()))
                .toList());
        saveRates(currencies);
        return rateDtoListMapper(rateRepositoryPort.findAll());
    }

    public List<RateDto> saveRates(List<RateDto> rateDtoList) {
        rateDtoList.forEach(rate -> rateRepositoryPort.save(rateMapper(rate)));
        return rateDtoList;
    }
}
