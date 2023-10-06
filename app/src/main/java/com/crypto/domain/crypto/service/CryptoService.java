package com.crypto.domain.crypto.service;

import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.crypto.model.Crypto;

public class CryptoService {

    private final CryptoRepositoryPort cryptoRepositoryPort;

    public CryptoService(CryptoRepositoryPort cryptoRepositoryPort) {
        this.cryptoRepositoryPort = cryptoRepositoryPort;
    }

    public Crypto addCryptoSymbol(Crypto crypto) {
        return cryptoRepositoryPort.save(crypto);
    }
}
