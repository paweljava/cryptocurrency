package com.crypto.infrastructure.adapters.inbound;

import com.crypto.application.CreateSymbolAction;
import com.crypto.domain.service.RateService;
import com.crypto.infrastructure.adapters.inbound.dto.CryptoDto;
import com.crypto.infrastructure.adapters.inbound.dto.RateDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crypto.domain.model.Mapper.map;

@RestController
@RequestMapping("/api")
class CryptoController {

    private final RateService rateService;

    private final CreateSymbolAction createSymbolAction;
    public CryptoController(RateService rateService, CreateSymbolAction createSymbolAction) {
        this.rateService = rateService;
        this.createSymbolAction = createSymbolAction;
    }

    @GetMapping("/rates")
    public List<RateDto> getRates() {
        return rateService.getRates();
    }

    @PostMapping("/symbols")
    public CryptoDto addCryptoSymbol(@RequestParam CryptoDto symbol) {
        createSymbolAction.execute(map(symbol));
        return symbol;
    }

}
