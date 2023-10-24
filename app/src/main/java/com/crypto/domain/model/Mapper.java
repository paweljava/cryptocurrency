package com.crypto.domain.model;

import com.crypto.infrastructure.adapters.inbound.dto.CryptoDto;
import com.crypto.infrastructure.adapters.inbound.dto.RateDto;

import java.util.List;

public class Mapper {

    public static Crypto toCrypto(CryptoDto cryptoDto) {
        return new Crypto(cryptoDto.symbol());
    }

    public static List<RateDto> toRateDto(List<Rate> rate) {
        return rate.stream().map(
                        r -> new RateDto(
                                r.symbol(),
                                r.price()))
                .toList();
    }
}