package com.crypto.domain.crypto.adapter;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.model.RateDto;
import com.crypto.domain.crypto.port.inbound.CryptoServicePort;
import com.crypto.domain.crypto.service.RateService;

import java.util.List;

public class CryptoServiceAdapter implements CryptoServicePort {

    private final RateService rateService;

    public CryptoServiceAdapter(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public List<RateDto> getCrypto() {
        return rateService.getCurrencies();
    }

    @Override
    public Rate addCrypto(Rate rate) {
        return rateService.addCrypto(rate);
    }
}
