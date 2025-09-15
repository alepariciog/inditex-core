package com.alepariciog.inditex.domain;

import java.time.LocalDateTime;

/** Exception representing a situation were a price was not found. */
public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long brandId, Long productId, LocalDateTime datetime) {
        super(String.format(
                "Price not found by brandId %s, productId %s and datetime %s.",
                brandId, productId, datetime));
    }
}