package com.crypto.domain.port.inbound;

import com.crypto.domain.model.Crypto;

public interface CryptoServicePort {

    Crypto addCryptoSymbol(Crypto crypto);
}
