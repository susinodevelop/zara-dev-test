package com.backend.test.zara.infrastructure.output.mapper;

import com.backend.test.zara.domain.model.PriceCo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PriceRowMapper implements RowMapper<PriceCo> {

    @Override
    public PriceCo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PriceCo.builder()
                .brand(rs.getString("NAME"))
                .startDate(rs.getTimestamp("START_DATE").toLocalDateTime())
                .endDate(rs.getTimestamp("END_DATE").toLocalDateTime())
                .priceList(rs.getInt("PRICE_LIST"))
                .productId(rs.getInt("PRODUCT_ID"))
                .priority(rs.getInt("PRIORITY"))
                .price(rs.getDouble("PRICE"))
                .currency(rs.getString("CURR"))
                .build();
    }

}
