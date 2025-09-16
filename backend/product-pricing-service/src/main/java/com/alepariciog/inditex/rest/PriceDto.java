package com.alepariciog.inditex.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDto(
        Long priceList,
        Long brandId,
        Long productId,
        BigDecimal price,
        String currency,
        LocalDateTime startDate,
        LocalDateTime endDate) {

}