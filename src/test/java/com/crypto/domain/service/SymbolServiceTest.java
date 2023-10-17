package com.crypto.domain.service;

import com.crypto.domain.model.Crypto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import com.crypto.infrastructure.adapters.outbound.repository.CryptoRepository;
import org.junit.jupiter.api.Test;

import static com.crypto.infrastructure.adapters.outbound.repository.entity.EntityMapper.cryptoToCryptoEntityMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SymbolServiceTest {

    private final CryptoRepository cryptoRepository = mock(CryptoRepository.class);
    private final BinanceWebSocketClient binanceWebSocketClient = mock(BinanceWebSocketClient.class);
    private final SymbolService symbolService = new SymbolService(cryptoRepository, binanceWebSocketClient);

    @Test
    void should_add_crypto_symbol() {
        // given
        final var cryptoSymbol = new Crypto("TUSDUSDT");
        when(cryptoRepository.save(any())).thenReturn(cryptoToCryptoEntityMapper(cryptoSymbol));

        // when
        final var result = symbolService.addCryptoSymbol(cryptoSymbol);

        // then
        assertThat(result.symbol()).isNotNull();
        assertThat(result.symbol()).isEqualTo(cryptoSymbol.symbol());
    }
}