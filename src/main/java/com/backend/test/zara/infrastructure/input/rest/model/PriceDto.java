package com.backend.test.zara.infrastructure.input.rest.model;

import java.time.LocalDateTime;

public record PriceDto(
        int productId,
        String brand,
        int priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        double price
) {
}
