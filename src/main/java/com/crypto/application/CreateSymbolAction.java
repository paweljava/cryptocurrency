package com.crypto.application;

import com.crypto.domain.model.Crypto;
import com.crypto.domain.service.SymbolService;
import org.springframework.stereotype.Service;

@Service
public class CreateSymbolAction {

    private final SymbolService symbolService;

    public CreateSymbolAction(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    public Crypto execute(Crypto crypto) {
        final var cryptoEntity = symbolService.addCryptoSymbol(crypto);
        return new Crypto(cryptoEntity.symbol());
    }
}
