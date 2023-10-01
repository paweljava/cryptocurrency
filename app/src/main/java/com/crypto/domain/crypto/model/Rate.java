package com.crypto.domain.crypto.model;

public class Rate {

    private String symbol;
    private double price;

    public Rate(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public Rate(String symbol) {
        this.symbol = symbol;
    }

    public Rate() {
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}
