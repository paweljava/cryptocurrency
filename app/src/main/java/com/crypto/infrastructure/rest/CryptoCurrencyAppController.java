package com.crypto.infrastructure.rest;

import com.crypto.infrastructure.rest.dto.CryptoDto;
import com.crypto.infrastructure.rest.dto.RateDto;
import com.crypto.domain.port.inbound.CryptoServicePort;
import com.crypto.domain.port.inbound.RateServicePort;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crypto.domain.model.Mapper.map;

@RestController
class CryptoCurrencyAppController {

    private final RateServicePort rateServicePort;
    private final CryptoServicePort cryptoServicePort;

    CryptoCurrencyAppController(RateServicePort rateServicePort, CryptoServicePort cryptoServicePort) {
        this.rateServicePort = rateServicePort;
        this.cryptoServicePort = cryptoServicePort;
    }

    @GetMapping("/api")
    List<RateDto> getRates(){
        return rateServicePort.getRates();
    }

    @PostMapping("/api")
    CryptoDto addCryptoSymbol(@RequestParam CryptoDto symbol) {
        cryptoServicePort.addCryptoSymbol(map(symbol));
        return symbol;
    }

    /*@GetMapping("/index")
    public String index() {
        return "index"; // Wczytuje stronę internetową do wyświetlenia danych WebSocket
    }*/

   /* @MessageMapping("/subscribe") // Obsługuje wiadomości przychodzące na "/app/subscribe"
    @SendTo("/topic/trades") // Wysyła odpowiedź na "/topic/trades"
    public String subscribe(String pair) {
        if ("btcusdt".equals(pair)) {
            return "Subscribed to " + pair;
        } else {
            return "Unsupported pair: " + pair;
        }
    }*/
}
