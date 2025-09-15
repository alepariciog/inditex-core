package com.alepariciog.inditex.domain;

import java.time.LocalDateTime;
import java.util.List;

/** Price repository. */
public interface PriceRepository {

    List<Price> findByProductAndBrandAndDatetime(
            Long productId, Long brandId, LocalDateTime dateTime);

}
