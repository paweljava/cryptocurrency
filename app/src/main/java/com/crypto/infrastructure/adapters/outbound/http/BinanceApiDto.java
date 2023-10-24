package com.crypto.infrastructure.adapters.outbound.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BinanceApiDto {

    @JsonProperty("s")
    private String symbol;
    @JsonProperty("p")
    private double price;

    public BinanceApiDto(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public BinanceApiDto() {
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double getPrice() {
        return this.price;
    }
}