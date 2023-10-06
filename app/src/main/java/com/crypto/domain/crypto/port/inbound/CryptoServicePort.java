package com.crypto.domain.crypto.port.inbound;

import com.crypto.domain.crypto.model.Crypto;

public interface CryptoServicePort {

    Crypto addCryptoSymbol(Crypto crypto);
}
