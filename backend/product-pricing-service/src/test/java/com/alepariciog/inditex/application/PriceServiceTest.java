package com.alepariciog.inditex.application;

import com.alepariciog.inditex.domain.Price;
import com.alepariciog.inditex.domain.PriceNotFoundException;
import com.alepariciog.inditex.domain.PriceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

/** PriceService unit tests. */
@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceService priceService;

    @Test
    @DisplayName("Verify that finds more priority price when multiple ones are available.")
    void should_findMorePriorityPrice_When_MultiplePricesAvailable() {

        // GIVEN
        long productId = 35455L;
        long brandId = 1L;
        long priceList = 0L;
        int basePriority = 0;
        String currency = "EUR";
        LocalDateTime startDate = LocalDateTime.now().minusHours(1L);
        LocalDateTime endDate = LocalDateTime.now().plusHours(1L);
        LocalDateTime applicationDate = LocalDateTime.now();

        Price lowPriority = new Price(
                UUID.randomUUID(),
                priceList,
                brandId,
                productId,
                basePriority,
                BigDecimal.valueOf(10),
                currency,
                startDate,
                endDate);

        Price mediumPriority = new Price(
                UUID.randomUUID(),
                priceList,
                brandId,
                productId,
                basePriority + 1,
                BigDecimal.valueOf(20),
                currency,
                startDate,
                endDate);

        Price highPriority = new Price(
                UUID.randomUUID(),
                priceList,
                brandId,
                productId,
                basePriority + 2,
                BigDecimal.valueOf(30),
                currency,
                startDate,
                endDate);

        when(repository
                .findByProductAndBrandAndDatetime(productId, brandId, applicationDate))
                .thenReturn(List.of(lowPriority, mediumPriority, highPriority));

        // WHEN
        Price retrievedResult = priceService.findPrice(productId, brandId, applicationDate);

        // THEN
        assertThat(retrievedResult)
                .as("Retrieved price should be the one with highest priority")
                .isEqualTo(highPriority);
    }

    @Test
    @DisplayName("Verify that throws a not found exception when prices were not found.")
    void should_ThrowException_When_NoPricesWereFound() {

        // GIVEN
        LocalDateTime applicationDate = LocalDateTime.now();
        long productId = 1L;
        long brandId = 1L;

        when(repository
                .findByProductAndBrandAndDatetime(brandId, productId, applicationDate))
                .thenReturn(Collections.emptyList());

        // WHEN + THEN
        assertThatThrownBy(
                () -> priceService.findPrice(brandId, productId, applicationDate))
                .isInstanceOf(PriceNotFoundException.class);
    }
}