package com.crypto.domain.port.outbound;

import com.crypto.domain.model.Rate;

import java.util.List;

public interface RateRepositoryPort {

    List<Rate> findAll();

    Rate save(Rate rate);
}
