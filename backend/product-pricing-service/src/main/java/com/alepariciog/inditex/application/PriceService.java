package com.alepariciog.inditex.application;

import com.alepariciog.inditex.domain.Price;
import com.alepariciog.inditex.domain.PriceNotFoundException;
import com.alepariciog.inditex.domain.PriceRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/** Service for the find price use case. */
@Slf4j
@Named
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository repository;

    public Price findPrice(Long productId, Long brandId, LocalDateTime datetime) {

        List<Price> price = repository
                .findByProductAndBrandAndDatetime(productId, brandId, datetime);

        if (price.isEmpty()) {
            throw new PriceNotFoundException(productId, brandId, datetime);
        }

        log.debug("At least one price was found for the given arguments.");
        return price.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .get();
    }
}
