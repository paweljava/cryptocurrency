package com.crypto.infrastructure.application.rest.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
public record RateDto(@NonNull String symbol, double price) {
}
