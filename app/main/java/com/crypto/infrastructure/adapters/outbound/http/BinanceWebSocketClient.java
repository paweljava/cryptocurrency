package com.crypto.infrastructure.adapters.outbound.http;

import com.crypto.infrastructure.adapters.outbound.repository.CryptoRepository;
import com.crypto.infrastructure.adapters.outbound.repository.entity.EntityMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

// TODO find a way to run rates fetching before asking for rates
//
// 1. App run
// 2. Binance client listening starts(define initial list)
// 3. When POST is called, ready rate is waiting for the customer(pre-processing)
//


/*
 ETH, BTC

 POST /symbols/SOL

 ETH, BTC, SOL
 */
@Component
public class BinanceWebSocketClient {
    //public class BinanceWebSocketClient implements CommandLineRunner {
    @Value("${api_host}")
    private String apiHost;

    //private final static String API_HOST = "wss://stream.binance.com:9443/ws/btcusdt@trade";
    //private final static String API_HOST = "wss://stream.binance.com:9443/ws/btcusdt@trade";
    private final Map<String, BinanceApiDto> response = new HashMap<>();
    private final CryptoRepository cryptoRepository;

    public BinanceWebSocketClient(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public void connectToServer() {
        final var currencyPairs = cryptoRepository.findAll().stream()
                .map(EntityMapper::cryptoToCryptoEntityMapper)
                .map(pair -> pair.symbol().toLowerCase() + "@trade")
                .collect(toSet());
        try {
            final var uri = new URI(apiHost + "btcusdt@trade");
            final var client = new WebSocketClient(uri, new Draft_6455()) {

                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("Connected to Binance WebSocket");

                    final var subscribeMessageJson = new JSONObject();
                    subscribeMessageJson.putOpt("method", "SUBSCRIBE");
                    subscribeMessageJson.putOnce("params", currencyPairs);
                    subscribeMessageJson.put("id", 1);

                    final var subscribeMessage = subscribeMessageJson.toString();
                    send(subscribeMessage);
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Received message: " + message);
                    try {
                        final var jsonObject = new JSONObject(message);

                        if (jsonObject.has("s") && jsonObject.has("p")) {
                            final var symbol = jsonObject.getString("s");
                            final var price = jsonObject.getDouble("p");
                            response.put(symbol, new BinanceApiDto(symbol, price));
                        } else {
                            System.err.println("Invalid message format: " + message);
                        }
                    } catch (JSONException e) {
                        System.err.println("Error parsing JSON: " + e.getMessage());
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Closed with code " + code + ", reason: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    System.err.println("Error occurred: " + ex.getMessage());
                }
            };
            client.connect();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void run(String... args) {
        *//*final Set<String> currencyPairs = cryptoRepository.findAll().stream()
                .map(EntityMapper::cryptoToCryptoEntityMapper)
                .map(a -> a.symbol().toLowerCase() + "@trade")
                .collect(toSet());*//*
        connectToServer();
    }*/

    public Map<String, BinanceApiDto> getCurrencyRates() {
        return response;
    }
}