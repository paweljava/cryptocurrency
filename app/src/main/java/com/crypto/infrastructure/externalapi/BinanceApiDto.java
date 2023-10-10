package com.crypto.infrastructure.externalapi;

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

    BinanceApiDto() {
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}