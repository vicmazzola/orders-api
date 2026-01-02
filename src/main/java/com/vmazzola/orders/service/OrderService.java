package com.vmazzola.orders.service;

import com.vmazzola.orders.api.dto.CreateOrderRequest;
import com.vmazzola.orders.api.dto.OrderResponse;
import com.vmazzola.orders.domain.Order;

import java.util.List;

public interface OrderService {

    Order create(CreateOrderRequest request);

    Order findById(Long id);

    OrderResponse findResponseById(Long id);

    List<OrderResponse> findAll();
}
