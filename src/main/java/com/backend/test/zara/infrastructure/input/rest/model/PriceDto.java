package com.backend.test.zara.infrastructure.input.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record PriceDto(
        @Schema(description = "Identificador del producto")
        int productId,
        @Schema(description = "Identificador de la cadena")
        String brand,
        @Schema(description = "Tarifa a aplicar")
        int priceList,
        @Schema(description = "Fecha de inicio")
        LocalDateTime startDate,
        @Schema(description = "Fecha de fin")
        LocalDateTime endDate,
        @Schema(description = "Precio")
        double price
) {
}
