package com.crypto.infrastructure.rest.dto;

import org.springframework.lang.NonNull;

public record RateDto(@NonNull String symbol, double price) {
}
