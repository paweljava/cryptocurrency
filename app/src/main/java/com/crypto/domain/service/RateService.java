package com.crypto.domain.service;

import com.crypto.domain.port.outbound.CryptoRepositoryPort;
import com.crypto.domain.port.outbound.RateRepositoryPort;
import com.crypto.domain.model.Mapper;
import com.crypto.infrastructure.rest.dto.RateDto;
import com.crypto.infrastructure.externalapi.BinanceApiDto;
import com.crypto.infrastructure.externalapi.BinanceWebSocketClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.crypto.domain.model.Mapper.mapper;
import static com.crypto.domain.model.Mapper.rateDtoListMapper;

public class RateService {

    private final CryptoRepositoryPort cryptoRepositoryPort;
    private final RateRepositoryPort rateRepositoryPort;
    private final BinanceWebSocketClient binanceWebSocketClient;

    public RateService(RateRepositoryPort rateRepositoryPort, BinanceWebSocketClient binanceWebSocketClient, CryptoRepositoryPort cryptoRepositoryPort) {
        this.binanceWebSocketClient = binanceWebSocketClient;
        this.rateRepositoryPort = rateRepositoryPort;
        this.cryptoRepositoryPort = cryptoRepositoryPort;
    }

    public List<RateDto> getRates(){
        final var currencyList = cryptoRepositoryPort.findAll().stream()
                .map(a -> a.symbol().toLowerCase() + "@trade")
                .toList();

        final var rate = binanceWebSocketClient.connectToServer(currencyList);
        List<BinanceApiDto> list = new ArrayList<>();

        for (var crypto : cryptoRepositoryPort.findAll()) {
            if (rate == null) {
                return mapper(list);
            } else if (Objects.equals(crypto.symbol(), rate.getSymbol())) {
                list.add(rate);
            }
        }

        //list.add(rate);
        saveRates(mapper(list));

        return rateDtoListMapper(rateRepositoryPort.findAll());
        /*final var currencies = mapper(cryptoRepositoryPort.findAll().stream()
                .map(crypto -> bianceClient.getCurrencyBySymbol(crypto.symbol()))
                .toList());
        saveRates(currencies);
        return rateDtoListMapper(rateRepositoryPort.findAll());*/
    }

    /*public List<RateDto> getRates() {
        binanceWebSocketClient.connectToServer();
        final var currencies = mapper(cryptoRepositoryPort.findAll().stream()
                .map(crypto -> bianceClient.getCurrencyBySymbol(crypto.symbol()))
                .toList());
        saveRates(currencies);
        return rateDtoListMapper(rateRepositoryPort.findAll());
    }*/

    public List<RateDto> saveRates(List<RateDto> rateDtoList) {
        rateDtoList.forEach(rate -> rateRepositoryPort.save(Mapper.rateMapper(rate)));
        return rateDtoList;
    }
}
