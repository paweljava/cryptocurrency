package com.crypto.infrastructure.adapters.inbound;

import com.crypto.application.RateQuery;
import com.crypto.domain.model.Rate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CryptoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RateQuery rateQuery;

    @Test
    public void should_get_rates() throws Exception {
        //given
        final var btcusdt = new Rate("BTCUSDT", 10.255);
        final var bnbusdt = new Rate("BNBUSDT", 11.255);
        final var ethusdt = new Rate("ETHUSDT", 12.255);
        final var xrpusdt = new Rate("XRPUSDT", 13.255);
        final var eurusdt = new Rate("EURUSDT", 14.255);
        final var rates = List.of(btcusdt, bnbusdt, ethusdt, xrpusdt, eurusdt);
        when(rateQuery.getRates()).thenReturn(rates);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/rates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        //then
        final var response = asList(objectMapper.readValue(result.getResponse()
                .getContentAsString(UTF_8), Rate[].class));

        assertFalse(response.isEmpty());
        assertTrue(response.stream().anyMatch(rate -> rate.symbol().contains(btcusdt.symbol())));
        assertTrue(response.stream().anyMatch(rate -> rate.symbol().contains(eurusdt.symbol())));
        assertTrue(response.stream().anyMatch(rate -> rate.symbol().contains(bnbusdt.symbol())));
        assertTrue(response.stream().anyMatch(rate -> rate.symbol().contains(ethusdt.symbol())));
        assertTrue(response.stream().anyMatch(rate -> rate.symbol().contains(xrpusdt.symbol())));
    }
}