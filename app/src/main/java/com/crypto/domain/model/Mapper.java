package com.crypto.domain.model;

import com.crypto.infrastructure.adapters.inbound.dto.CryptoDto;
import com.crypto.infrastructure.adapters.inbound.dto.RateDto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceApiDto;

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

    public static List<RateDto> mapper(List<BinanceApiDto> binanceApiDtoList) {
        return binanceApiDtoList.stream().map(
                        apiDto -> new RateDto(
                                apiDto.getSymbol(),
                                apiDto.getPrice()))
                .collect(toList());
    }
}