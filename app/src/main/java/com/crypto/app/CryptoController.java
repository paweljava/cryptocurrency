package com.crypto.app;

import com.crypto.web.client.BianceApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.crypto.app.Mapper.map;

@RestController
class CryptoController {


    private final CryptoService cryptoService;

    CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/api")
    public List<BianceApiDto> getCurrency() {
        //checkThat(validateDate(date), INCORRECT_DATE_RANGE_EXCEPTION_MESSAGE);
        final var response = cryptoService.getCurrencies();
        cryptoService.save(map(response));
        return response;
    }
}
