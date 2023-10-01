package com.crypto.domain.crypto.service;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.port.outbound.CryptoRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateServiceTestMock {

    /*@Mock
    private CryptoRepositoryPort cryptoRepositoryPort;
    @Mock
    private BianceClient bianceClient;
    @InjectMocks
    private CryptoService cryptoService;*/

    private final RateService rateService = mock(RateService.class);
    private final CryptoRepositoryPort cryptoRepositoryPort = mock(CryptoRepositoryPort.class);

    //private final CryptoRepositoryPort cryptoRepositoryPort = new CryptoConfig().cryptoRepositoryPort();
    //private final CryptoService cryptoService = new CryptoConfig().cryptoService();

    @Test
    void should_add_crypto() {
        // given
        final var symbol = new Rate("TUSDUSDT");
        when(cryptoRepositoryPort.save(any())).thenReturn(symbol);
        given(cryptoRepositoryPort.save(any())).willReturn(symbol);

        // when
        final var result = rateService.addCrypto(symbol);

        // then
        assertEquals(result.getSymbol(), symbol.getSymbol());
    }

    @Test
    void should_add_crypto2() {
        // given
        final var symbol = new Rate("TUSDUSDT");
        when(cryptoRepositoryPort.save(symbol)).thenReturn(symbol);
        when(cryptoRepositoryPort.findAll()).thenReturn(List.of(symbol));
        rateService.addCrypto(symbol);

        // when
        final var result = rateService.getCurrencies();

        // then
        assertThat(result).isNotNull().hasSize(1);
        assertThat(result.get(0).getSymbol()).isEqualTo(symbol.getSymbol());
        assertTrue(result.stream().anyMatch(rate -> rate.getSymbol().equals(symbol.getSymbol())));
    }

    @Test
    void should_get_currencies() {
        // given
        final var btcusdt = new Rate("BTCUSDT");
        final var bnbusdt = new Rate("BNBUSDT");
        final var ethusdt = new Rate("ETHUSDT");
        final var xrpusdt = new Rate("XRPUSDT");
        final var eurusdt = new Rate("EURUSDT");
        when(cryptoRepositoryPort.findAll()).thenReturn(List.of(btcusdt, bnbusdt, ethusdt, xrpusdt, eurusdt));

        // when
        final var result = rateService.getCurrencies();

        // then
        assertTrue(result.stream().anyMatch(rate -> rate.getSymbol().equals(btcusdt.getSymbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.getSymbol().equals(bnbusdt.getSymbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.getSymbol().equals(ethusdt.getSymbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.getSymbol().equals(xrpusdt.getSymbol())));
        assertTrue(result.stream().anyMatch(rate -> rate.getSymbol().equals(eurusdt.getSymbol())));
    }
}