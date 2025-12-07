package com.vmazzola.orders.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be zero or negative");
        }

        items.add(new OrderItem(product, quantity));
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
