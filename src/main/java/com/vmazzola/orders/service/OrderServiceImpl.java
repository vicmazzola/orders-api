package com.vmazzola.orders.service;

import com.vmazzola.orders.domain.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final Map<Long, Order> storage = new HashMap<>();

    @Override
    public Order create(Order order) {
        storage.put(order.getId(), order);
        return order;
    }

    @Override
    public Order findById(Long id) {
        Order order = storage.get(id);

        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        return order;
    }
}
