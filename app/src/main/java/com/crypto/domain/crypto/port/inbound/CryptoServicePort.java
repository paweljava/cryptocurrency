package com.crypto.domain.crypto.port.inbound;

import com.crypto.domain.crypto.model.Rate;
import com.crypto.domain.crypto.model.RateDto;

import java.util.List;

public interface CryptoServicePort {

    List<RateDto> getCrypto();

    Rate addCrypto(Rate rate);
}
