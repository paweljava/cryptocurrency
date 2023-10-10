package com.crypto.domain.port.outbound;

import com.crypto.domain.model.Crypto;

import java.util.List;

public interface CryptoRepositoryPort {

    List<Crypto> findAll();

    Crypto save(Crypto crypto);
}
