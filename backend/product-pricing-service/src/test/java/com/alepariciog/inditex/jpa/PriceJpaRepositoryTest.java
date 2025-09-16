package com.alepariciog.inditex.jpa;

import com.alepariciog.inditex.domain.Price;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/** PriceJpaRepository unit tests. */
class PriceJpaRepositoryTest {

    private final PriceJpaRepositorySpring springRepository = mock(PriceJpaRepositorySpring.class);

    private final PriceJpaMapper mapper = Mappers.getMapper(PriceJpaMapper.class);

    private final PriceJpaRepository repository = new PriceJpaRepository(springRepository, mapper);

    @Test
    @DisplayName("Verify that retrieves domain prices when they have been found.")
    void should_RetrievePrices_When_TheyHaveFound() {

        // GIVEN
        Long productId = 35545L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        List<PriceJpa> priceJpas = Instancio.ofList(PriceJpa.class)
                .size(5)
                .set(field(PriceJpa.class, "productId"), productId)
                .set(field(PriceJpa.class, "brandId"), brandId)
                .set(field(PriceJpa.class, "startDate"), applicationDate.minusDays(1))
                .set(field(PriceJpa.class, "endDate"), applicationDate.plusDays(1))
                .create();

        given(springRepository
                .findByProductAndBrandAndDatetime(productId, brandId, applicationDate))
                .willReturn(priceJpas);

        // WHEN
        List<Price> prices = repository
                .findByProductAndBrandAndDatetime(productId, brandId, applicationDate);

        // THEN
        List<Price> expectedPrices = priceJpas.stream().map(mapper::map).toList();
        assertThat(prices)
                .as("Retrieved prices should match the expectations")
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(expectedPrices);
    }
}