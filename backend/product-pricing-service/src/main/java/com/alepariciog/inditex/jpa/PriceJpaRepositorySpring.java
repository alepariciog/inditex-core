package com.alepariciog.inditex.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface PriceJpaRepositorySpring extends JpaRepository<PriceJpa, Long> {

    @Query("""
            SELECT p
            FROM PriceJpa p
            WHERE p.productId = :productId
            AND p.brandId = :brandId
            AND p.startDate <= :datetime
            AND p.endDate >= :datetime
            """)
    Collection<PriceJpa> findByProductAndBrandAndDatetime(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("datetime") LocalDateTime datetime);

}
