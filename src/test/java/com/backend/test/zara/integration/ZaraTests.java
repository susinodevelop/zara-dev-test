package com.backend.test.zara.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class ZaraTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Fecha: 2020-06-14 - Hora: 10:00 - ProductId: 35455 - BrandId: 1")
    void testRequest1() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        MultiValueMap<String, String> requestParams = createRequestParams(date, 35455, 1);

        mockMvc.perform(get("/prices/final-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brand", is("ZARA")))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    @DisplayName("Fecha: 2020-06-14 - Hora: 16:00 - ProductId: 35455 - BrandId: 1")
    void testRequest2() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        MultiValueMap<String, String> requestParams = createRequestParams(date, 35455, 1);

        mockMvc.perform(get("/prices/final-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brand", is("ZARA")))
                .andExpect(jsonPath("$.priceList", is(2)))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T15:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-06-14T18:30:00")))
                .andExpect(jsonPath("$.price", is(25.45)));
    }

    @Test
    @DisplayName("Fecha: 2020-06-14 - Hora: 21:00 - ProductId: 35455 - BrandId: 1")
    void testRequest3() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0);
        MultiValueMap<String, String> requestParams = createRequestParams(date, 35455, 1);

        mockMvc.perform(get("/prices/final-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brand", is("ZARA")))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    @DisplayName("Fecha: 2020-06-15 - Hora: 10:00 - ProductId: 35455 - BrandId: 1")
    void testRequest4() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
        MultiValueMap<String, String> requestParams = createRequestParams(date, 35455, 1);

        mockMvc.perform(get("/prices/final-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brand", is("ZARA")))
                .andExpect(jsonPath("$.priceList", is(3)))
                .andExpect(jsonPath("$.startDate", is("2020-06-15T00:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-06-15T11:00:00")))
                .andExpect(jsonPath("$.price", is(30.50)));
    }

    @Test
    @DisplayName("Fecha: 2020-06-16 - Hora: 21:00 - ProductId: 35455 - BrandId: 1")
    void testRequest5() throws Exception {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0);
        MultiValueMap<String, String> requestParams = createRequestParams(date, 35455, 1);

        mockMvc.perform(get("/prices/final-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brand", is("ZARA")))
                .andExpect(jsonPath("$.priceList", is(4)))
                .andExpect(jsonPath("$.startDate", is("2020-06-15T16:00:00")))
                .andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.price", is(38.95)));
    }

    private MultiValueMap<String, String> createRequestParams(LocalDateTime date, int productId, int brandId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        params.add("productId", String.valueOf(productId));
        params.add("brandId", String.valueOf(brandId));
        return params;
    }

}
