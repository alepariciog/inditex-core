package com.alepariciog.inditex.jpa;

import com.alepariciog.inditex.domain.Price;
import com.alepariciog.inditex.domain.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/** Jpa implementation for the price repository. */
@Slf4j
@Component
@RequiredArgsConstructor
public class PriceJpaRepository implements PriceRepository {

    private final PriceJpaRepositorySpring repositorySpring;
    private final PriceJpaMapper mapper;

    @Override
    public List<Price> findByProductAndBrandAndDatetime(
            Long productId, Long brandId, LocalDateTime datetime) {
        log.debug("Finding prices for the given product, brand and datetime.");
        return repositorySpring
                .findByProductAndBrandAndDatetime(productId, brandId, datetime)
                .stream()
                .map(mapper::map)
                .toList();
    }

}
