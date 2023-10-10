package com.crypto.infrastructure.persistence.inmemory;

import com.crypto.domain.model.Crypto;
import com.crypto.domain.port.outbound.CryptoRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CryptoInMemoryAdapter implements CryptoRepositoryPort {

    private final ConcurrentHashMap<String, Crypto> database = new ConcurrentHashMap<>();

    @Override
    public List<Crypto> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Crypto save(Crypto crypto) {
        database.putIfAbsent(crypto.symbol(), crypto);
        return database.putIfAbsent(crypto.symbol(), crypto);
    }
}