package com.alepariciog.inditex.rest;

import com.alepariciog.inditex.application.PriceService;
import com.alepariciog.inditex.domain.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Rest controller for price related requests.
 */
@Slf4j
@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;
    private final PriceDtoMapper priceDtoMapper;

    @GetMapping
    @Operation(summary = "Queries price by product, brand and application datetime")
    public PriceDto getPrice(
            @Parameter(description = "Id of the product", example = "35455")
            @RequestParam("productId") Long productId,
            @Parameter(description = "Id of the brand", example = "1")
            @RequestParam("brandId") Long brandId,
            @Parameter(
                    description = "Application datetime in ISO 8601 format",
                    example = "2020-06-14T10:00:00")
            @RequestParam("datetime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime
    ) {
        log.debug("Request to retrieve price for a given brandId {}, "
                + "productId {} and datetime {} was received", productId, brandId, datetime);
        Price price = priceService.findPrice(productId, brandId, datetime);
        return priceDtoMapper.map(price);
    }

}
