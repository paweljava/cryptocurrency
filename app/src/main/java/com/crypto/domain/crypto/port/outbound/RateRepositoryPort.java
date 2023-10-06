package com.crypto.domain.crypto.port.outbound;

import com.crypto.domain.crypto.model.Rate;

import java.util.List;

public interface RateRepositoryPort {

    List<Rate> findAll();

    Rate save(Rate rate);
}
