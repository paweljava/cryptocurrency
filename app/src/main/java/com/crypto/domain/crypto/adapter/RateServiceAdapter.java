package com.crypto.domain.crypto.adapter;

import com.crypto.infrastructure.application.rest.dto.RateDto;
import com.crypto.domain.crypto.port.inbound.RateServicePort;
import com.crypto.domain.crypto.service.RateService;

import java.util.List;

public class RateServiceAdapter implements RateServicePort {

    private final RateService rateService;

    public RateServiceAdapter(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public List<RateDto> getRates() {
        return rateService.getRates();
    }
}
