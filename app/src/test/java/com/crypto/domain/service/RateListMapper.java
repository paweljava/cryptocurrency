package com.crypto.domain.service;

import com.crypto.domain.model.Rate;
import com.crypto.infrastructure.rest.dto.RateDto;

import java.util.List;

class RateListMapper {

    public static List<Rate> rateListMapper(List<RateDto> rateDtoList) {
        return rateDtoList.stream().map(
                        r -> new Rate(
                                r.symbol(),
                                r.price()))
                .toList();
    }
}
