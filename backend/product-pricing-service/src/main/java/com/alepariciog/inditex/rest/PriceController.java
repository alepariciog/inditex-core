package com.alepariciog.inditex.rest;

import com.alepariciog.inditex.application.PriceService;
import com.alepariciog.inditex.domain.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/** Rest controller for price related requests.*/
@Slf4j
@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;
    private final PriceDtoMapper priceDtoMapper;

    @GetMapping("/product/{productId}/brand/{brandId}")
    public PriceDto getPrice(
            @PathVariable("productId") Long brandId,
            @PathVariable("brandId") Long productId,
            @RequestParam("datetime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime
    ) {
        log.debug("Request to retrieve price for a given brandId {}, "
                + "productId {} and datetime {} was received", productId, brandId, datetime);
        Price price = priceService.findPrice(brandId, productId, datetime);
        return priceDtoMapper.map(price);
    }

}
