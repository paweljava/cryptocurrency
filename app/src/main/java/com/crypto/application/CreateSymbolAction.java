package com.crypto.application;

import com.crypto.domain.model.Crypto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import com.crypto.infrastructure.adapters.outbound.repository.CryptoRepository;
import com.crypto.infrastructure.adapters.outbound.repository.entity.CryptoEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateSymbolAction {


    private final CryptoRepository cryptoRepository;
    private final BinanceWebSocketClient binanceWebSocketClient;

    public CreateSymbolAction(CryptoRepository cryptoRepository, BinanceWebSocketClient binanceWebSocketClient) {
        this.cryptoRepository = cryptoRepository;
        this.binanceWebSocketClient = binanceWebSocketClient;
    }

    public void execute(Crypto crypto) {
        cryptoRepository.save(new CryptoEntity(crypto.symbol()));
        binanceWebSocketClient.connectToServer();
    }
}
