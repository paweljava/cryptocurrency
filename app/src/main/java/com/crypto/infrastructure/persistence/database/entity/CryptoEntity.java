package com.crypto.infrastructure.persistence.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "crypto_symbols")
@Getter
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
