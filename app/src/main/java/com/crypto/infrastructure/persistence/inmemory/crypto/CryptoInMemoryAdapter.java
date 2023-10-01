package com.crypto.infrastructure.persistence.inmemory.crypto;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CryptoInMemoryAdapter implements CryptoRepositoryPort {

    private final ConcurrentHashMap<String, Rate> database = new ConcurrentHashMap<>();



    @Override
    public List<Rate> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Rate save(Rate rate) {
        //return crypto != null ? database.putIfAbsent(crypto.getSymbol(), crypto): new Rate() ;
        database.putIfAbsent(rate.getSymbol(), rate);
        return database.putIfAbsent(rate.getSymbol(), rate);
    }
}




