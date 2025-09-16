package com.alepariciog.inditex.jpa;

import com.alepariciog.inditex.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * JPA to domain price mapper.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceJpaMapper {

    @Mapping(target = "currency", source = "curr")
    Price map(PriceJpa priceJpa);

}
