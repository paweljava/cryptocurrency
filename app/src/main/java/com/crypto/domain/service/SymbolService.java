package com.crypto.domain.service;

import com.crypto.domain.model.Crypto;
import com.crypto.infrastructure.adapters.outbound.repository.CryptoRepository;
import com.crypto.infrastructure.adapters.outbound.repository.entity.CryptoEntity;

public class SymbolService {

    private final CryptoRepository cryptoRepository;

    public SymbolService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public Crypto addCryptoSymbol(Crypto crypto) {
        final var cryptoEntity = cryptoRepository.save(new CryptoEntity(crypto.symbol()));
        return new Crypto(cryptoEntity.getSymbol());
    }
}
