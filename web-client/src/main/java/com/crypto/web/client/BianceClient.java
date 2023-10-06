package com.crypto.web.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class BianceClient {

    //Adnotacja @Value nie działa, nie wstrzykuje url = https://api.binance.com
    @Value("${api_host}")
    private String apiHost;
    private final RestTemplate restTemplate = new RestTemplate();

    public BianceApiDto getCurrencyBySymbol(String symbol) {
        final var response = callGetMethod(
                "/api/v3/avgPrice?symbol={symbol}",
                BianceApiDto.class, symbol);
        return new BianceApiDto(symbol, response.getPrice());
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        //return restTemplate.getForEntity(this.apiHost + url, responseType, objects).getBody();
        //return restTemplate.getForObject(this.apiHost + url, responseType, objects);
        return restTemplate.getForObject("https://api.binance.com" + url, responseType, objects);
    }
}