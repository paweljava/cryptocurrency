package com.crypto.web.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BianceApiDto {

    private String symbol;
    @JsonProperty("price")
    private double price;

    public BianceApiDto(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    BianceApiDto() {
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}