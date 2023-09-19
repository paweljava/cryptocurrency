package com.crypto.app;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rates")
public class Rate {

    @Id
    private String symbol;
    private double price;

    public Rate(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    Rate() {
    }

    String getSymbol() {
        return symbol;
    }

    double getPrice() {
        return price;
    }
}
