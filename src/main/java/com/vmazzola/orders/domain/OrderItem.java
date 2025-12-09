package com.vmazzola.orders.domain;

import com.vmazzola.orders.domain.discount.DiscountPolicy;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

    private final Product product;
    private final int quantity;
    private final DiscountPolicy discountPolicy;

    public OrderItem(Product product, int quantity, DiscountPolicy discountPolicy) {

        Objects.requireNonNull(product, "Product cannot be null");

        if (quantity <= 0) {
            throw new IllegalArgumentException("You can’t order 0 or negative items");
        }

        Objects.requireNonNull(discountPolicy);

        this.product = product;
        this.quantity = quantity;
        this.discountPolicy = discountPolicy;

    }

    public BigDecimal getTotal() {
        BigDecimal baseTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        return discountPolicy.apply(baseTotal);
    }
}
