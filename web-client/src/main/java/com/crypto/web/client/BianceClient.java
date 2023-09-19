package com.crypto.web.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;


public class BianceClient {
    @Value("${api_host}")
    private String apiHost;
    //private static final Logger logger = LoggerFactory.getLogger(WeatherClient.class);
    private final RestTemplate restTemplate = new RestTemplate();

    public BianceApiDto getCurrencyBySymbol(String symbol) {
        final var response = callGetMethod(
                "/api/v3/avgPrice?symbol={symbol}",
                BianceApiDto.class, symbol);
        var value = new BianceApiDto(symbol, response.getPrice());
        return value;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForEntity(this.apiHost + url, responseType, objects).getBody();
    }
}