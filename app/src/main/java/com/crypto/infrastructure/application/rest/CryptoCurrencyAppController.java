package com.crypto.infrastructure.application.rest;

import com.crypto.infrastructure.application.rest.dto.CryptoDto;
import com.crypto.infrastructure.application.rest.dto.RateDto;
import com.crypto.domain.crypto.port.inbound.CryptoServicePort;
import com.crypto.domain.crypto.port.inbound.RateServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.crypto.domain.crypto.model.Mapper.map;

@RestController
class CryptoCurrencyAppController {


    private final RateServicePort rateServicePort;
    private final CryptoServicePort cryptoServicePort;

    CryptoCurrencyAppController(RateServicePort rateServicePort, CryptoServicePort cryptoServicePort) {
        this.rateServicePort = rateServicePort;
        this.cryptoServicePort = cryptoServicePort;
    }

    @GetMapping("/api")
    List<RateDto> getRates() {
        return rateServicePort.getRates();
    }

    @PostMapping("/api")
    CryptoDto addCryptoSymbol(@RequestParam CryptoDto symbol) {
        cryptoServicePort.addCryptoSymbol(map(symbol));
        return symbol;
    }
}
