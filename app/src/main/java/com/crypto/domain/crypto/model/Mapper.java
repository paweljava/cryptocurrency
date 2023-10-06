package com.crypto.domain.crypto.model;

import com.crypto.infrastructure.application.rest.dto.CryptoDto;
import com.crypto.infrastructure.application.rest.dto.RateDto;
import com.crypto.web.client.BianceApiDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Mapper {

    public static Crypto map(CryptoDto cryptoDto) {
        return new Crypto(cryptoDto.symbol());
    }

    public static Rate rateMapper(RateDto rateDto) {
        return new Rate(rateDto.symbol(), rateDto.price());
    }

    public static List<RateDto> rateDtoListMapper(List<Rate> rate) {
        return rate.stream().map(
                r -> new RateDto(
                        r.symbol(),
                        r.price()))
                .toList();
    }

    public static List<RateDto> mapper(List<BianceApiDto> bianceApiDtoList) {
        return bianceApiDtoList.stream().map(
                        apiDto -> new RateDto(
                                apiDto.getSymbol(),
                                apiDto.getPrice()))
                .collect(toList());
    }
}