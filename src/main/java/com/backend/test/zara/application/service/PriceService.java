package com.backend.test.zara.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

import com.backend.test.zara.domain.model.PriceCo;
import com.backend.test.zara.domain.port.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.test.zara.application.usecase.PriceUsecase;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PriceService implements PriceUsecase {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<PriceCo> getPrice(LocalDateTime date, String productId, String brandId) {
        return priceRepository.getPrices(date, productId, brandId)
                .stream()
                .max(Comparator.comparingInt(PriceCo::getPriority));

    }

}
