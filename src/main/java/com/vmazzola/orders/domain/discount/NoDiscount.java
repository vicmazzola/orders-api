package com.vmazzola.orders.domain.discount;

import java.math.BigDecimal;

public class NoDiscount implements DiscountPolicy {
    @Override
    public BigDecimal apply(BigDecimal price) {
        return price;
    }
}
