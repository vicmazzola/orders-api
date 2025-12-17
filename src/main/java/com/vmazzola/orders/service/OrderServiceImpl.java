package com.vmazzola.orders.service;

import com.vmazzola.orders.api.dto.CreateOrderRequest;
import com.vmazzola.orders.domain.Order;
import com.vmazzola.orders.domain.OrderItem;
import com.vmazzola.orders.domain.Product;
import com.vmazzola.orders.domain.discount.NoDiscount;
import com.vmazzola.orders.exception.OrderNotFoundException;
import com.vmazzola.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    @Override
    public Order create(CreateOrderRequest request) {

        Order order = new Order();

        request.items().forEach(dto -> {
            order.addItem(Product.placeholder(dto.productId()),
                    dto.quantity(),
                    new NoDiscount()
            );
        });

        return orderRepository.save(order);
    }


    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
