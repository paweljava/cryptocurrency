package com.crypto.web.client;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_symbols")
public class Crypto {


    @Id
    private String symbol;

    public Crypto(String symbol) {
        this.symbol = symbol;
    }

    Crypto() {
    }

    public String getSymbol() {
        return symbol;
    }
}
