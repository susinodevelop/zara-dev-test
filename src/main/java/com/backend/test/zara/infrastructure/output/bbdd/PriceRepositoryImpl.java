package com.backend.test.zara.infrastructure.output.bbdd;

import com.backend.test.zara.domain.model.PriceCo;
import com.backend.test.zara.domain.port.PriceRepository;
import com.backend.test.zara.infrastructure.output.mapper.PriceRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
@Slf4j
public class PriceRepositoryImpl implements PriceRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PriceRowMapper priceRowMapper;

    @Autowired
    public PriceRepositoryImpl(
            NamedParameterJdbcTemplate jdbcTemplate,
            PriceRowMapper priceRowMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.priceRowMapper = priceRowMapper;
    }

    @Override
    public Collection<PriceCo> getPrices(LocalDateTime date, String productId, String brandId) {
        //TODO to implement
        return Collections.emptyList();
    }

}
