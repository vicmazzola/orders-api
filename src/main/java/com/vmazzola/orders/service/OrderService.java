package com.vmazzola.orders.service;

import com.vmazzola.orders.domain.Order;

public interface OrderService {

    Order create(Order order);

    Order findById(Long id);
}
