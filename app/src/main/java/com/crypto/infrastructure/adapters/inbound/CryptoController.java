package com.crypto.infrastructure.adapters.inbound;

import com.crypto.application.CreateSymbolAction;
import com.crypto.application.RateQuery;
import com.crypto.infrastructure.adapters.inbound.dto.CryptoDto;
import com.crypto.infrastructure.adapters.inbound.dto.RateDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.crypto.domain.model.Mapper.toCrypto;
import static com.crypto.domain.model.Mapper.toRateDto;

@RestController
@RequestMapping("/api")
public class CryptoController {

    private final RateQuery rateQuery;
    private final CreateSymbolAction createSymbolAction;

    public CryptoController(RateQuery rateQuery, CreateSymbolAction createSymbolAction) {
        this.rateQuery = rateQuery;
        this.createSymbolAction = createSymbolAction;
    }

    @GetMapping("/rates")
    public List<RateDto> getRates() {
        return toRateDto(rateQuery.getRates());
    }

    @PostMapping("/symbols")
    public CryptoDto addCryptoSymbol(@RequestParam CryptoDto cryptoDto) {
        createSymbolAction.execute(toCrypto(cryptoDto));
        return cryptoDto;
    }
}