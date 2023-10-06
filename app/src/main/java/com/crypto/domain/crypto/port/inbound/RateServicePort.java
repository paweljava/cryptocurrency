package com.crypto.domain.crypto.port.inbound;

import com.crypto.infrastructure.application.rest.dto.RateDto;

import java.util.List;

public interface RateServicePort {

    List<RateDto> getRates();
}
