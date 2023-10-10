package com.crypto.infrastructure.externalapi;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.List;

public class BinanceWebSocketClient {

    private final static String API_HOST = "wss://stream.binance.com:9443/ws/btcusdt@trade";
    private BinanceApiDto response;

    public BinanceApiDto connectToServer(List<String> currencyPairs) {
        try {
            final var uri = new URI(API_HOST);
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
                            var symbol = jsonObject.getString("s");
                            var cena = jsonObject.getDouble("p");
                            response = new BinanceApiDto(symbol, cena);

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
        return response;
    }
}

