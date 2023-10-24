package com.crypto.application;

import com.crypto.domain.model.Crypto;
import com.crypto.infrastructure.adapters.outbound.http.BinanceWebSocketClient;
import com.crypto.infrastructure.adapters.outbound.repository.CryptoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.crypto.infrastructure.adapters.outbound.repository.entity.EntityMapper.cryptoToCryptoEntityMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO prosze napisac test
class CreateSymbolActionTest {

    private final CryptoRepository cryptoRepository = mock(CryptoRepository.class);
    private final BinanceWebSocketClient binanceWebSocketClient = mock(BinanceWebSocketClient.class);
    private final CreateSymbolAction createSymbolAction = new CreateSymbolAction(cryptoRepository, binanceWebSocketClient);

    @Test
    void should_add_crypto_symbol() {
        // given
        final var cryptoSymbol = new Crypto("TUSDUSDT");
        when(cryptoRepository.findAll()).thenReturn(List.of(cryptoToCryptoEntityMapper(cryptoSymbol)));

        // when
        createSymbolAction.execute(cryptoSymbol);

        // then
        assertThat(cryptoRepository.findAll()).isNotNull();
        assertThat(cryptoRepository.findAll().get(0).getSymbol()).contains(cryptoSymbol.symbol());
        assertTrue(cryptoRepository.findAll().stream().anyMatch(rate -> rate.getSymbol().equals(cryptoSymbol.symbol())));
    }
}