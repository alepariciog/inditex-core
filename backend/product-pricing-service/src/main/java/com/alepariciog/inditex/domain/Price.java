package com.alepariciog.inditex.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Price entity.
 * Disclaimer: As long as I dont have enough domain knowledge, I just left this
 * entity with no methods, validations nor value objects implemented.
 * This should be a proper domain, but I don't have knowledge to do so right now.
 */
@Getter
@AllArgsConstructor
public class Price {

    UUID id;
    Long priceList;
    Long brandId;
    Long productId;
    Integer priority;
    BigDecimal price;
    String currency;
    LocalDateTime startDate;
    LocalDateTime endDate;

}
