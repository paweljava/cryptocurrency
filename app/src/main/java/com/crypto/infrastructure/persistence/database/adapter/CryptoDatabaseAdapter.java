package com.crypto.infrastructure.persistence.database.adapter;

import com.crypto.domain.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.model.Crypto;
import com.crypto.infrastructure.persistence.database.repository.CryptoRepository;

import java.util.List;

import static com.crypto.infrastructure.persistence.database.entity.EntityMapper.*;

public class CryptoDatabaseAdapter implements CryptoRepositoryPort {

    private final CryptoRepository cryptoRepository;

    public CryptoDatabaseAdapter(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    @Override
    public List<Crypto> findAll() {
        return cryptoListMapper(cryptoRepository.findAll());
    }

    @Override
    public Crypto save(Crypto crypto) {
        return cryptoToCryptoEntityMapper(cryptoRepository.save(cryptoEntityToCryptoMapper(crypto)));
    }
}
