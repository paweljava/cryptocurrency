package com.crypto.infrastructure.persistence.database.crypto;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.infrastructure.persistence.database.crypto.repository.CryptoRepository;

import java.util.List;

import static com.crypto.infrastructure.persistence.database.crypto.entity.RateMapper.*;

public class RateDatabaseAdapter implements CryptoRepositoryPort {

    private final CryptoRepository cryptoRepository;

    public RateDatabaseAdapter(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public List<Rate> findAll() {
        return rateListMapper(cryptoRepository.findAll());
    }

    @Override
    public Rate save(Rate rate) {
        return rateToRateEntityMapper(cryptoRepository.save(rateEntitytoRateMapper(rate)));
    }
}
