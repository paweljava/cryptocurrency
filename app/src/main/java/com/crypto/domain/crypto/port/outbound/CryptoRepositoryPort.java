package com.crypto.domain.crypto.port.outbound;

import com.crypto.domain.crypto.model.Crypto;

import java.util.List;

public interface CryptoRepositoryPort {

    List<Crypto> findAll();

    Crypto save(Crypto crypto);
}
