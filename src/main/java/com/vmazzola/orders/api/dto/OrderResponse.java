package com.vmazzola.orders.api.dto;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        BigDecimal total
) {
}
