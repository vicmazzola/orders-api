package com.vmazzola.orders.domain;

import com.vmazzola.orders.domain.discount.DiscountPolicy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Getter
public class Order {
    private final Long id;
    private final List<OrderItem> items;

    public Order(Long id) {

        Objects.requireNonNull(id, "Id cannot be null");
        if (id.longValue() <= 0) {
            throw new IllegalArgumentException("Id cannot be zero or negative");
        }

        this.id = id;
        this.items = new ArrayList<>();

    }

    public Order(Long id, List<OrderItem> items) {
        Objects.requireNonNull(id, "Id cannot be null");
        this.id = id;
        this.items = new ArrayList<>(items);
    }

    public void addItem(Product product, int quantity, DiscountPolicy discountPolicy) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be zero or negative");
        }

        items.add(new OrderItem(product, quantity, discountPolicy));
    }


    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
