package com.vmazzola.orders.api.dto;

import java.util.List;

public record ApiError(
        String error,
        List<String> details
) {
}
