package com.backend.test.zara.infrastructure.input.rest.controller;

import com.backend.test.zara.application.usecase.PriceUsecase;
import com.backend.test.zara.domain.model.PriceCo;
import com.backend.test.zara.infrastructure.input.rest.mapper.PriceMapper;
import com.backend.test.zara.infrastructure.input.rest.model.PriceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(
            path = "final-price",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceDto> getFinalPrice(
            @RequestParam(name = "date") LocalDateTime date,
            @RequestParam(name = "productId") String productId,
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
