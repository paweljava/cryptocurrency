package com.crypto.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "crypto_symbols")
class CryptoSymbol {


    @Id
    private String symbol;

    public CryptoSymbol(String symbol) {
        this.symbol = symbol;
    }

    CryptoSymbol() {
    }

    String getSymbol() {
        return symbol;
    }
}
