package com.crypto.domain.adapter;

import com.crypto.domain.port.inbound.CryptoServicePort;
import com.crypto.domain.service.CryptoService;
import com.crypto.domain.model.Crypto;

public class CryptoServiceAdapter implements CryptoServicePort {

    private final CryptoService cryptoService;

    public CryptoServiceAdapter(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Override
    public Crypto addCryptoSymbol(Crypto crypto) {
        return cryptoService.addCryptoSymbol(crypto);
    }
}
