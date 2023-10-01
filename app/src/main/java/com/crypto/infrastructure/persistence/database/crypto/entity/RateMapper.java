package com.crypto.infrastructure.persistence.database.crypto.entity;

import com.crypto.domain.crypto.model.Rate;

import java.util.List;

public class RateMapper {

    public static List<Rate> rateListMapper(List<RateEntity> rateEntity) {
        return rateEntity.stream()
                .map(rate -> new Rate(
                        rate.getSymbol(),
                        rate.getPrice()))
                .toList();
    }

    public static Rate rateMapper(Rate rate) {
        final var value =  new RateEntity(rate.getSymbol(), rate.getPrice());
        return new Rate(value.getSymbol(), value.getPrice());
    }
    public static RateEntity rateEntitytoRateMapper(Rate rate) {
        return new RateEntity(rate.getSymbol(), rate.getPrice());
    }

    public static Rate rateToRateEntityMapper(RateEntity rarateEntitye) {
        return new Rate(rarateEntitye.getSymbol(), rarateEntitye.getPrice());
    }
}
