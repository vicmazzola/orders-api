package com.vmazzola.orders.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {

        Objects.requireNonNull(product, "Product cannot be null");

        if (quantity <= 0) {
            throw new IllegalArgumentException("You can’t order 0 or negative items");
        }

        this.product = product;
        this.quantity = quantity;

    }

    public BigDecimal getTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
