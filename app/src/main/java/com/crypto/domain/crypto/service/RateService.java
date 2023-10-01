package com.crypto.domain.crypto.service;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.model.RateDto;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.web.client.BianceClient;

import java.util.List;

import static com.crypto.domain.crypto.model.Mapper.mapper;

public class RateService {

    private final BianceClient bianceClient;
    private final CryptoRepositoryPort cryptoRepositoryPort;

    public RateService(CryptoRepositoryPort cryptoRepositoryPort, BianceClient bianceClient) {
        this.bianceClient = bianceClient;
        this.cryptoRepositoryPort = cryptoRepositoryPort;
    }

    public List<RateDto> getCurrencies() {
        return mapper(cryptoRepositoryPort.findAll().stream()
                .map(crypto -> bianceClient.getCurrencyBySymbol(crypto.getSymbol()))
                .toList());
    }

    public Rate addCrypto(Rate crypto) {
        return cryptoRepositoryPort.save(crypto);
    }
}
