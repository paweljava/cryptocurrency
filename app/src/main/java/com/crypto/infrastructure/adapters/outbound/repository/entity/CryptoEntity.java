package com.crypto.infrastructure.adapters.outbound.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "crypto_symbols")
public
class CryptoEntity {


    @Id
    private String symbol;

    public CryptoEntity(String symbol) {
        this.symbol = symbol;
    }

    public CryptoEntity() {
    }

    public String getSymbol() {
        return symbol;
    }
}
