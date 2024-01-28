package com.backend.test.zara.domain.port;

import com.backend.test.zara.domain.model.PriceCo;

import java.time.LocalDateTime;
import java.util.Collection;

public interface PriceRepository {

    Collection<PriceCo> getPrices(LocalDateTime date, String productId, String brandId);

}
