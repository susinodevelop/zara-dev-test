package com.backend.test.zara.infrastructure.input.rest.controller;

import com.backend.test.zara.application.usecase.PriceUsecase;
import com.backend.test.zara.domain.model.PriceCo;
import com.backend.test.zara.infrastructure.input.rest.mapper.PriceMapper;
import com.backend.test.zara.infrastructure.input.rest.model.PriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path = "prices")
@Slf4j
@Validated
public class PriceController {

    private final PriceUsecase priceUsecase;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceController(
            PriceUsecase priceUsecase,
            PriceMapper priceMapper
    ) {
        this.priceUsecase = priceUsecase;
        this.priceMapper = priceMapper;
    }


    /**
     * Endpoint para obtener un precio final para el producto de una cadena.
     *
     * @param date      Fecha y hora del precio deseado.
     * @param productId Identificador del producto.
     * @param brandId   Identificador de la cadena.
     * @return {@link PriceDto} Datos del precio final, fechas de aplicación, identificador de cadena y tarifa a aplicar.
     */
    @Operation(summary = "Endpoint para obtener los datos del precio a aplicar para el producto de una cadena en una fecha",
            responses = {
                    @ApiResponse(description = "Datos del precio",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PriceDto.class))),
                    @ApiResponse(description = "Parámetros incorrectos",
                            responseCode = "400"),
                    @ApiResponse(description = "Not found",
                            responseCode = "404"),
                    @ApiResponse(description = "Error interno",
                            responseCode = "500")
            })
    @GetMapping(
            path = "final-price",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceDto> getFinalPrice(
            @Parameter(description = "Fecha de aplicación",
                    name = "date",
                    required = true)
            @NotNull(message = "La fecha no puede ser nula")
            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            @RequestParam(name = "date") LocalDateTime date,
            @Parameter(description = "Identificador del producto",
                    name = "productId",
                    required = true)
            @NotBlank(message = "El productId no puede estar vacio")
            @RequestParam(name = "productId") String productId,
            @Parameter(description = "Identificador de la cadena",
                    name = "brandId",
                    required = true)
            @NotBlank(message = "El brandId no puede estar vacio")
            @RequestParam(name = "brandId") String brandId
    ) {
        log.info(
                "Petición para consultar el precio final. Date: {}, Product Id: {}, Brand Id: {}",
                date, productId, brandId
        );
        Optional<PriceCo> price = priceUsecase.getPrice(date, productId, brandId);
        return price
                .map(priceCo -> ResponseEntity.ok(priceMapper.toDto(priceCo)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
