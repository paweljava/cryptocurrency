package com.crypto.domain.crypto.service;

import com.crypto.domain.crypto.model.Rate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateServiceTest {

    //private final CryptoService cryptoService = new CryptoConfig().cryptoService();
    @Autowired
    private RateService rateService;

    @Test
    void should_add_crypto() {
        // given
        final var symbol = new Rate("TUSDUSDT");

        // when
        final var result = rateService.addCrypto(symbol);

        // then
        assertThat(result).isNotNull();
        assertEquals(result.getSymbol(), symbol.getSymbol());
        assertThat(result.getSymbol()).isEqualTo(symbol.getSymbol());
    }

    @Test
    void should_add_crypto2() {
        // given
        final var symbol = new Rate("TUSDUSDT");
        rateService.addCrypto(symbol);

        // when
        final var result = rateService.getCurrencies();

        // then
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
        rateService.addCrypto(btcusdt);
        rateService.addCrypto(bnbusdt);
        rateService.addCrypto(ethusdt);
        rateService.addCrypto(xrpusdt);
        rateService.addCrypto(eurusdt);

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