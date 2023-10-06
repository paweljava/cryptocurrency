package com.crypto.domain.crypto.adapter;

import com.crypto.domain.crypto.model.Crypto;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.crypto.service.CryptoService;
import com.crypto.infrastructure.persistence.inmemory.crypto.CryptoInMemoryAdapter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CryptoServiceAdapterTest {

    private final CryptoRepositoryPort cryptoRepositoryPort = new CryptoInMemoryAdapter();
    private final CryptoServiceAdapter cryptoServiceAdapter = new CryptoServiceAdapter(new CryptoService(cryptoRepositoryPort));

    @Test
    void should_add_crypto_symbol() {
        // given
        final var symbol = new Crypto("TUSDUSDT");

        // when
        final var result = cryptoServiceAdapter.addCryptoSymbol(symbol);

        // then
        assertThat(result.symbol()).isNotNull();
        assertThat(result.symbol()).isEqualTo(symbol.symbol());
    }
}