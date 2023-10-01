package com.crypto.domain.crypto.model;

import lombok.NonNull;

public class RateDto {

    @NonNull
    private String symbol;
    private double price;

    public RateDto(@NonNull String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public RateDto(@NonNull String symbol) {
        this.symbol = symbol;
    }

    RateDto() {
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}
