package com.backend.test.zara.infrastructure.output.bbdd;

import com.backend.test.zara.domain.model.PriceCo;
import com.backend.test.zara.domain.port.PriceRepository;
import com.backend.test.zara.infrastructure.output.mapper.PriceRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    /**
     * Consulta en la base de datos los datos de los precios para una fecha, producto y cadena dados.
     *
     * @param date      Fecha de aplicación.
     * @param productId Identificador de producto.
     * @param brandId   Identificador de cadena.
     * @return Todos los precios que cumplan los criterios establecidos.
     */
    @Override
    public Collection<PriceCo> getPrices(LocalDateTime date, String productId, String brandId) {
        log.info("Consultando a BBDD los precios. Date: {}. Product Id: {}. Brand Id: {}", date, productId, brandId);
        String sql = "SELECT BRAND.NAME, PRICES.START_DATE, PRICES.END_DATE, PRICES.PRICE_LIST, PRICES.PRODUCT_ID, " +
                "PRICES.PRIORITY, PRICES.PRICE, PRICES.CURR " +
                "FROM PRICES " +
                "INNER JOIN BRAND ON PRICES.BRAND_ID = BRAND.BRAND_ID " +
                "WHERE PRICES.BRAND_ID = :brandId " +
                "AND PRICES.PRODUCT_ID = :productId " +
                "AND :date BETWEEN PRICES.START_DATE AND PRICES.END_DATE";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("date", Timestamp.valueOf(date));
        parameters.put("productId", productId);
        parameters.put("brandId", brandId);

        return jdbcTemplate.query(sql, parameters, priceRowMapper);
    }

}
