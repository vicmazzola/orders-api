package com.vmazzola.orders.domain.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscount implements DiscountPolicy {

    private final BigDecimal percentage;

    public PercentageDiscount(BigDecimal percentage) {
        if (percentage.compareTo(BigDecimal.ZERO) < 0 ||
                percentage.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Percentage must be between 0 and 1");
        }
        this.percentage = percentage;
    }

    @Override
    public BigDecimal apply(BigDecimal price) {
        BigDecimal discount = price.multiply(percentage);
        return price.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }
}
