/*
package com.crypto.app;

import com.crypto.app.CryptoSymbolService;
import com.crypto.web.client.BianceApiDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.crypto.app.Mapper.map;

@RestController
class CryptoController {


    private final CryptoSymbolService cryptoSymbolService;

    CryptoController(CryptoSymbolService cryptoSymbolService) {
        this.cryptoSymbolService = cryptoSymbolService;
    }

    @GetMapping("/api")
    public List<BianceApiDto> getCurrency() {
        //checkThat(validateDate(date), INCORRECT_DATE_RANGE_EXCEPTION_MESSAGE);
        final var response = cryptoSymbolService.getCurrencies();
        cryptoSymbolService.save(map(response));
        return response;
    }
}
*/
