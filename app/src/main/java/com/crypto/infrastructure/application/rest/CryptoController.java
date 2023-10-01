package com.crypto.infrastructure.application.rest;

import com.crypto.domain.crypto.model.RateDto;
import com.crypto.domain.crypto.port.inbound.CryptoServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.crypto.domain.crypto.model.Mapper.map;

@RestController
class CryptoController {


    private final CryptoServicePort cryptoServicePort;

    CryptoController(CryptoServicePort cryptoServicePort) {
        this.cryptoServicePort = cryptoServicePort;
    }

    @GetMapping("/api")
    List<RateDto> getCurrency() {
        final var response = cryptoServicePort.getCrypto();
        return response;
    }

    @PostMapping("/api")
    RateDto addCrypto(@RequestParam RateDto symbol) {
        cryptoServicePort.addCrypto(map(symbol));
        return symbol;
    }
}
