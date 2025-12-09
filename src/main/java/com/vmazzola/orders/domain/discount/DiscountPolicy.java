package com.vmazzola.orders.domain.discount;

import java.math.BigDecimal;

public interface DiscountPolicy {
    BigDecimal apply(BigDecimal price);
}
