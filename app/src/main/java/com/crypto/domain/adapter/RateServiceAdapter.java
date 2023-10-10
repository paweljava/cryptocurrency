package com.crypto.domain.adapter;

import com.crypto.domain.port.inbound.RateServicePort;
import com.crypto.infrastructure.rest.dto.RateDto;
import com.crypto.domain.service.RateService;

import java.util.List;

public class RateServiceAdapter implements RateServicePort {

    private final RateService rateService;

    public RateServiceAdapter(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public List<RateDto> getRates(){
        return rateService.getRates();
    }
}
