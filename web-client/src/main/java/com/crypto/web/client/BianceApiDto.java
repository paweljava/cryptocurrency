package com.crypto.web.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;


public class BianceApiDto {

    private String symbol;

    /*@JsonProperty("mins")
    private final Integer mins;*/
    @JsonProperty("price")
    private double price;

    public BianceApiDto(String symbol,/* Integer mins, */double price) {
        this.symbol = symbol;
       // this.mins = mins;
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
