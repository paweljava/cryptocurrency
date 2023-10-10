package com.crypto.infrastructure.adapters.inbound.dto;

import org.springframework.lang.NonNull;

public record RateDto(@NonNull String symbol, double price) {
}
