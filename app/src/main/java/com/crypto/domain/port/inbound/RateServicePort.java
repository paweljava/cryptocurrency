package com.crypto.domain.port.inbound;

import com.crypto.infrastructure.rest.dto.RateDto;

import java.util.List;

public interface RateServicePort {

    List<RateDto> getRates();
}
