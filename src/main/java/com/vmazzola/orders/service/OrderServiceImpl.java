package com.vmazzola.orders.service;

import com.vmazzola.orders.api.dto.CreateOrderRequest;
import com.vmazzola.orders.domain.Order;
import com.vmazzola.orders.domain.OrderItem;
import com.vmazzola.orders.domain.Product;
import com.vmazzola.orders.domain.discount.NoDiscount;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final Map<Long, Order> storage = new HashMap<>();

    @Override
    public Order create(CreateOrderRequest request) {

        // 1) Convert DTO -> Domain
        List<OrderItem> items = request.items().stream()
                .map(dto -> new OrderItem(
                        Product.placeholder(
                                dto.productId()),
                        dto.quantity(),
                        new NoDiscount()
                ))
                .toList();

        // 2) Create Oder
        Order order = new Order(request.id(), items);

        // 3) Save in storage
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
