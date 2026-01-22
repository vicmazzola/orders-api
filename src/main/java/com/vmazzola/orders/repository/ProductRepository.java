package com.vmazzola.orders.repository;

import com.vmazzola.orders.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findTop3ByOrderByPriceDesc();

    @Query("SELECT AVG(p.price) FROM Product p")
    BigDecimal findAveragePrice();


}
