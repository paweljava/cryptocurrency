package com.crypto.domain.adapter;

import com.crypto.domain.port.outbound.BinanceWebSocketOutPort;
import com.crypto.infrastructure.externalapi.BinanceApiDto;
import com.crypto.infrastructure.externalapi.BinanceWebSocketClient;
import com.crypto.infrastructure.rest.dto.RateDto;

import java.util.List;

public class BinanceWebSocketAdapterPort implements BinanceWebSocketOutPort {

    private final BinanceWebSocketClient binanceWebSocketClient;

    BinanceWebSocketAdapterPort(BinanceWebSocketClient binanceWebSocketClient) {
        this.binanceWebSocketClient = binanceWebSocketClient;
    }

    /*public BinanceApiDto getRates(){
        return rateService.getRates();
    }*/
}
