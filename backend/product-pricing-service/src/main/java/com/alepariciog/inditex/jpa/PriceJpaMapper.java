package com.alepariciog.inditex.jpa;

import com.alepariciog.inditex.domain.Price;
import com.alepariciog.inditex.rest.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/** JPA to domain price mapper. */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceJpaMapper {

    Price map(PriceJpa priceJpa);

}
