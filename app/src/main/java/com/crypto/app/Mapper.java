package com.crypto.app;

import com.crypto.web.client.BianceApiDto;

import java.util.List;
import java.util.stream.Collectors;

class Mapper {
    public static List<Rate> map(List<BianceApiDto> bianceApiDto) {

        return bianceApiDto.stream().map(
                        currency -> new Rate(
                                currency.getSymbol(),
                                currency.getPrice()))
                .collect(Collectors.toList());
    }
}