package com.alepariciog.inditex.rest;

import com.alepariciog.inditex.application.PriceService;
import com.alepariciog.inditex.domain.Price;
import com.alepariciog.inditex.generated.prices.PriceDto;
import com.alepariciog.inditex.jpa.PriceJpa;
import com.alepariciog.inditex.jpa.PriceJpaMapper;
import com.alepariciog.inditex.jpa.PriceJpaRepository;
import com.alepariciog.inditex.jpa.PriceJpaRepositorySpring;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/** PriceController unit tests. */
class PriceControllerTest {

    private final PriceService service = mock(PriceService.class);

    private final PriceDtoMapper mapper = Mappers.getMapper(PriceDtoMapper.class);

    private final PriceController controller = new PriceController(service, mapper);

    @Test
    @DisplayName("Verify that retrieves proper dto when service has return a price.")
    void should_RetrievePriceDto_When_PriceHasBeenFound() {

        // GIVEN
        LocalDateTime applicationDate = LocalDateTime.now();
        Price foundPrice = Instancio.of(Price.class)
                .set(field(Price.class, "startDate"), applicationDate.minusDays(1))
                .set(field(Price.class, "endDate"), applicationDate.plusDays(1))
                .create();

        given(service.findPrice(
                foundPrice.getProductId(),
                foundPrice.getBrandId(),
                applicationDate))
                .willReturn(foundPrice);

        // WHEN
        PriceDto retrievedPrice = controller.getPrice(
                foundPrice.getProductId(),
                foundPrice.getBrandId(),
                applicationDate);

        // THEN
        assertThat(retrievedPrice)
                .as("Retrieved price fields should match found price")
                .extracting(
                        PriceDto::getProductId,
                        PriceDto::getBrandId,
                        PriceDto::getPrice,
                        PriceDto::getPriceList,
                        PriceDto::getCurrency,
                        PriceDto::getStartDate,
                        PriceDto::getEndDate)
                .containsExactly(
                        foundPrice.getProductId(),
                        foundPrice.getBrandId(),
                        foundPrice.getPrice().doubleValue(),
                        foundPrice.getPriceList(),
                        foundPrice.getCurrency(),
                        foundPrice.getStartDate(),
                        foundPrice.getEndDate());
    }
}
