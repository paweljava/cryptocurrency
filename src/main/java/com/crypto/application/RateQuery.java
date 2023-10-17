package com.crypto.application;

import com.crypto.domain.model.Crypto;
import com.crypto.domain.model.Rate;
import com.crypto.domain.service.RateService;
import com.crypto.domain.service.SymbolService;
import com.crypto.infrastructure.adapters.inbound.dto.RateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateQuery {

    private final RateService rateService;

    public RateQuery(RateService rateService) {
        this.rateService = rateService;
    }

    public List<Rate> execute() {
        return rateService.getRates();
    }
}
