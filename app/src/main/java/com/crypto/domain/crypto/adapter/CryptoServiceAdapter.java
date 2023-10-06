package com.crypto.domain.crypto.adapter;

import com.crypto.domain.crypto.port.inbound.CryptoServicePort;
import com.crypto.domain.crypto.service.CryptoService;
import com.crypto.domain.crypto.model.Crypto;

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
