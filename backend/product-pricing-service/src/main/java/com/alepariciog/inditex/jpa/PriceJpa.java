package com.alepariciog.inditex.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PRICES")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceJpa {

    @Id
    private UUID id;

    private Long priceList;

    private Long brandId;

    private Long productId;

    private Integer priority;

    private BigDecimal price;

    private String curr;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}