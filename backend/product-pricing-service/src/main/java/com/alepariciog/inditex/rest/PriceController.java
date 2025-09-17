package com.alepariciog.inditex.rest;

import com.alepariciog.inditex.application.PriceService;
import com.alepariciog.inditex.domain.Price;
import com.alepariciog.inditex.generated.prices.PriceDto;
import com.alepariciog.inditex.generated.prices.PricesApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * Rest controller for price related requests.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final PriceService priceService;
    private final PriceDtoMapper priceDtoMapper;

    @Override
    public PriceDto getPrice(Long productId, Long brandId, LocalDateTime datetime) {
        log.debug("Request to retrieve price for a given brandId {}, "
                + "productId {} and datetime {} was received", productId, brandId, datetime);
        Price price = priceService.findPrice(productId, brandId, datetime);
        return priceDtoMapper.map(price);
    }
}
