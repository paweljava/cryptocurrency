package com.crypto.domain.service;

import com.crypto.domain.model.Crypto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import com.crypto.infrastructure.adapters.outbound.repository.CryptoRepository;
import com.crypto.infrastructure.adapters.outbound.repository.entity.CryptoEntity;
import org.springframework.stereotype.Service;

import static com.crypto.infrastructure.adapters.outbound.repository.entity.EntityMapper.cryptoToCryptoEntityMapper;

@Service
public class SymbolService {

    private final CryptoRepository cryptoRepository;
    private final BinanceWebSocketClient binanceWebSocketClient;

    public SymbolService(CryptoRepository cryptoRepository, BinanceWebSocketClient binanceWebSocketClient) {
        this.cryptoRepository = cryptoRepository;
        this.binanceWebSocketClient = binanceWebSocketClient;
    }

    public Crypto addCryptoSymbol(Crypto crypto) {
        final var cryptoEntity = cryptoRepository.save(new CryptoEntity(crypto.symbol()));
        binanceWebSocketClient.connectToServer();
        return cryptoToCryptoEntityMapper(cryptoEntity);
        // robić mapper czy nie, mapper jest użyty w dwóch różnych klasach?
        //return new Crypto(cryptoEntity.getSymbol());
    }
}