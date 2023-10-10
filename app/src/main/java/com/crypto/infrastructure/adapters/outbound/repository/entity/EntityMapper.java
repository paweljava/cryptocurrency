package com.crypto.infrastructure.adapters.outbound.repository.entity;

import com.crypto.domain.model.Crypto;
import com.crypto.domain.model.Rate;

import java.util.List;

public class EntityMapper {

    public static List<Rate> rateListMapper(List<RateEntity> rateEntity) {
        return rateEntity.stream()
                .map(rate -> new Rate(
                        rate.getSymbol(),
                        rate.getPrice()))
                .toList();
    }

    public static Rate rateMapper(Rate rate) {
        final var value = new RateEntity(rate.symbol(), rate.price());
        return new Rate(value.getSymbol(), value.getPrice());
    }

    public static RateEntity rateEntityToRateMapper(Rate rate) {
        return new RateEntity(rate.symbol(), rate.price());
    }

    public static Rate rateToRateEntityMapper(RateEntity rarateEntitye) {
        return new Rate(rarateEntitye.getSymbol(), rarateEntitye.getPrice());
    }

    public static CryptoEntity cryptoEntityToCryptoMapper(Crypto crypto) {
        return new CryptoEntity(crypto.symbol());
    }

    public static Crypto cryptoToCryptoEntityMapper(CryptoEntity cryptoEntity) {
        return new Crypto(cryptoEntity.getSymbol());
    }

    public static List<Crypto> cryptoListMapper(List<CryptoEntity> cryptoEntities) {
        return cryptoEntities.stream()
                .map(rate -> new Crypto(
                        rate.getSymbol()))
                .toList();
    }
}
