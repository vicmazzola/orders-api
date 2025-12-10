package com.vmazzola.orders.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CreateOrderRequest(
        @NotNull @Positive Long id,
        @NotEmpty List<OrderItemRequest> items
) {}
