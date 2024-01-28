package com.backend.test.zara.infrastructure.input.rest.mapper;

import com.backend.test.zara.domain.model.PriceCo;
import com.backend.test.zara.infrastructure.input.rest.model.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface PriceMapper {

    @Mapping(target= "productId", source = "productId")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "priceList", source = "priceList")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "price", source = "price")
    PriceDto toDto(PriceCo price);

}
