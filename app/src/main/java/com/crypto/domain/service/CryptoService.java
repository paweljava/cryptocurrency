package com.crypto.domain.service;

import com.crypto.domain.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.model.Crypto;

public class CryptoService {

    private final CryptoRepositoryPort cryptoRepositoryPort;

    public CryptoService(CryptoRepositoryPort cryptoRepositoryPort) {
        this.cryptoRepositoryPort = cryptoRepositoryPort;
    }

    public Crypto addCryptoSymbol(Crypto crypto) {
        return cryptoRepositoryPort.save(crypto);
    }
}
