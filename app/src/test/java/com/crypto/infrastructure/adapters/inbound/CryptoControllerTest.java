package com.crypto.infrastructure.adapters.inbound;

import com.crypto.infrastructure.adapters.outbound.http.BinanceApiDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CryptoControllerTest {

    @Value("${api_host}")
    private String apiHost;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(9090);

    @BeforeAll
    public static void startServer() {
        configureFor("localhost", 9090);
        WIRE_MOCK_SERVER.start();
        System.out.println("WireMock Server is running on port " + WIRE_MOCK_SERVER.getOptions().portNumber());
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void should_get_rates() {
    }

    @Test
    void should_add_crypto_symbol() throws Exception {
        // given
        stubBTCUSDT();
        stubBNBUSDT();
        stubETHUSDT();
        stubXRPUSDT();
        stubEURUSDT();

        // when
        final var mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/rates"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        // then
        final var response = asList(objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(UTF_8), BinanceApiDto[].class));

        assertEquals("BTCUSDT", response.get(0).getSymbol());

    }

    private static void stubBTCUSDT() {
        stubFor(WireMock.get(urlPathEqualTo("/btcusdt@trade"))
                //.withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/btcusdt-response.json")));
    }

    private static void stubBNBUSDT() {
        stubFor(WireMock.get(urlPathEqualTo("/bnbusdt@trade"))
                //.withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/bnbusdt-response.json")));
    }

    private static void stubETHUSDT() {
        stubFor(WireMock.get(urlPathEqualTo("/ethusdt@trade"))
                //.withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("ethusdt-response.json")));
    }

    private static void stubXRPUSDT() {
        stubFor(WireMock.get(urlPathEqualTo("/xrpusdt@trade"))
                //.withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/xrpusdt-response.json")));
    }

    private static void stubEURUSDT() {
        stubFor(WireMock.get(urlPathEqualTo("/eurusdt@trade"))
                //.withQueryParam("key", equalTo(API_KEY))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/eurusdt-response.json")));
    }
}

