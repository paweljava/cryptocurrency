package com.crypto.domain.crypto.model;

import com.crypto.web.client.BianceApiDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Mapper {

    public static Rate map(RateDto rateDto) {
        return new Rate(rateDto.getSymbol());
    }

    public static List<RateDto> mapper(List<BianceApiDto> bianceApiDtoList) {
        return bianceApiDtoList.stream().map(
                        apiDto -> new RateDto(
                                apiDto.getSymbol(),
                                apiDto.getPrice()))
                .collect(toList());
    }
}