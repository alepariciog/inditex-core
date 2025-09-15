package com.alepariciog.inditex.rest;

import com.alepariciog.inditex.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/** Domain to DTO price mapper. */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceDtoMapper {

    PriceDto map(Price price);

}
