package com.vmazzola.orders.service;

import com.vmazzola.orders.api.dto.CreateOrderRequest;
import com.vmazzola.orders.domain.Order;

public interface OrderService {

    Order create(CreateOrderRequest request);

    Order findById(Long id);
}
