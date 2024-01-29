package com.backend.test.zara.integration;

import com.backend.test.zara.application.service.PriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EndpointValidationTests {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PriceService priceService;

    @Test
    @DisplayName("Http 200")
    void okTest() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31T23:59:59");
        parameters.add("productId", "35455");
        parameters.add("brandId", "1");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Http 400 - Bad date time")
    void badRequestTest_badDateTime() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31");
        parameters.add("productId", "35455");
        parameters.add("brandId", "1");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Http 400 - Without date time")
    void badRequestTest_withoutDateTime() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("productId", "35455");
        parameters.add("brandId", "1");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Http 400 - without productId")
    void badRequestTest_withoutProductId() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31T23:59:59");
        parameters.add("brandId", "1");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Http 400 - Blank productId")
    void badRequestTest_blankProductId() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31T23:59:59");
        parameters.add("productId", "");
        parameters.add("brandId", "1");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Http 400 - Without brandId")
    void badRequestTest_withoutBrandId() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31T23:59:59");
        parameters.add("productId", "35455");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Http 400 - Blank brandId")
    void badRequestTest_blankBrandId() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31T23:59:59");
        parameters.add("productId", "35455");
        parameters.add("brandId", "");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Http 404")
    void notFoundTest() throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31T23:59:59");
        parameters.add("productId", "6");
        parameters.add("brandId", "8");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-prices")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Http 500")
    void internalErrorTest() throws Exception {
        doThrow(RuntimeException.class).when(priceService).getPrice(any(LocalDateTime.class), anyString(), anyString());

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("date", "2020-12-31T23:59:59");
        parameters.add("productId", "35455");
        parameters.add("brandId", "1");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/prices/final-price")
                                .params(parameters)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());
    }

}
