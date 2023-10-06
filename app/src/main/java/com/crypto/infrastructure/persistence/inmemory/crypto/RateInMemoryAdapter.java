package com.crypto.infrastructure.persistence.inmemory.crypto;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.port.outbound.RateRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RateInMemoryAdapter implements RateRepositoryPort {

    private final ConcurrentHashMap<String, Rate> database = new ConcurrentHashMap<>();

    @Override
    public List<Rate> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Rate save(Rate rate) {
        database.putIfAbsent(rate.symbol(), rate);
        return database.putIfAbsent(rate.symbol(), rate);
    }
}




