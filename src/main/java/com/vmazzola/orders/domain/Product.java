package com.vmazzola.orders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    private Long id;

    private String name;
    private BigDecimal price;

    public Product(Long id, String name, BigDecimal price) {

        Objects.requireNonNull(id, "Id cannot be null");
        if (id.longValue() <= 0) {
            throw new IllegalArgumentException("Id cannot be zero or negative");
        }

        Objects.requireNonNull(name, "Name cannot be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }

        Objects.requireNonNull(price, "Price cannot be null");
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static Product placeholder(Long id){
        return new Product(id, "Placeholder", BigDecimal.TEN);
    }
}
