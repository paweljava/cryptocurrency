package com.crypto.infrastructure.persistence.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "rates")
@Getter
public class RateEntity {

    @Id
    private String symbol;
    private double price;

    public RateEntity(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public RateEntity() {
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

