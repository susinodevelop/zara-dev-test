package com.backend.test.zara.application.usecase;

import java.time.LocalDateTime;
import java.util.Optional;

import com.backend.test.zara.domain.model.PriceCo;

public interface PriceUsecase {

    Optional<PriceCo> getPrice(LocalDateTime date, String productId, String brandId);

}
