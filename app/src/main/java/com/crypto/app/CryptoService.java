package com.crypto.app;

import com.crypto.web.client.BianceApiDto;
import com.crypto.web.client.BianceClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CryptoService {

    private final BianceClient bianceClient;
    private final RateRepository rateRepository;
    private final CryptoSymbolRepository cryptoSymbolRepository;

    CryptoService(BianceClient bianceClient, RateRepository rateRepository, CryptoSymbolRepository cryptoSymbolRepository) {
        this.bianceClient = bianceClient;
        this.rateRepository = rateRepository;
        this.cryptoSymbolRepository = cryptoSymbolRepository;
    }

    public List<BianceApiDto> getCurrencies() {
        final var response = cryptoSymbolRepository.findAll().stream()
                .map(crypto -> bianceClient.getCurrencyBySymbol(crypto.getSymbol()))
                .toList();

        /*List<BianceApiDto> response = new ArrayList<>();
        List<CryptoSymbol> cryptoSymbols = cryptoSymbolRepository.findAll();

        for (CryptoSymbol cryptoSymbol : cryptoSymbols) {
            BianceApiDto bianceApiDto = bianceClient.getCurrencyBySymbol(cryptoSymbol.getSymbol());
            response.add(bianceApiDto);
        }*/
        return response;
    }

    void save(List<Rate> rates) {
        if (rates != null) {
            rates.forEach(crypto -> {
                if (crypto != null) {
                    rateRepository.save(crypto);
                }
            });
        }
    }
}
