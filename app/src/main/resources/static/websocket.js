var socket = new WebSocket("wss://stream.binance.com:9443/ws/btcusdt@trade");
var currencyPairs = ["btcusdt@trade", "bnbusdt@trade", "ethusdt@trade", "xrpusdt@trade", "eurusdt@trade"];
var lastMessages = {};

socket.onopen = function(event) {
    console.log("WebSocket connection opened");

    var subscribeMessage = JSON.stringify({
        method: "SUBSCRIBE",
        params: currencyPairs,
        id: 1,
    });
    socket.send(subscribeMessage);
};

socket.onmessage = function(event) {
    var data = JSON.parse(event.data);
    var websocketDataDiv = document.getElementById("websocket-data");

    if (data?.result === null && data?.id === 1) {
        return;
    }

    data = [data.s, data.p];

    if (lastMessages[data[0]]) {
        lastMessages[data[0]] = data;
    } else {
        lastMessages[data[0]] = data;
    }

    var messagesHtml = Object.values(lastMessages).map(message => {
        return `${JSON.stringify(message, null, 2)}<br>`;}).join('');

    websocketDataDiv.innerHTML = messagesHtml;
};

socket.onerror = function(event) {
    console.error("WebSocket error:", event);
};

socket.onclose = function(event) {
    console.log("WebSocket connection closed");
};