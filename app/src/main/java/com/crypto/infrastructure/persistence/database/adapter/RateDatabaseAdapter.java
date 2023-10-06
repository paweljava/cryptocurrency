package com.crypto.infrastructure.persistence.database.adapter;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.port.outbound.RateRepositoryPort;
import com.crypto.infrastructure.persistence.database.repository.RateRepository;

import java.util.List;

import static com.crypto.infrastructure.persistence.database.entity.EntityMapper.*;

public class RateDatabaseAdapter implements RateRepositoryPort {

    private final RateRepository rateRepository;

    public RateDatabaseAdapter(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> findAll() {
        return rateListMapper(rateRepository.findAll());
    }

    @Override
    public Rate save(Rate rate) {
        return rateToRateEntityMapper(rateRepository.save(rateEntityToRateMapper(rate)));
    }
}
