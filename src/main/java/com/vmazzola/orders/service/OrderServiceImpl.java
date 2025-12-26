package com.vmazzola.orders.service;

import com.vmazzola.orders.api.dto.CreateOrderRequest;
import com.vmazzola.orders.api.dto.OrderResponse;
import com.vmazzola.orders.domain.Order;
import com.vmazzola.orders.domain.Product;
import com.vmazzola.orders.domain.discount.NoDiscount;
import com.vmazzola.orders.exception.OrderNotFoundException;
import com.vmazzola.orders.repository.OrderRepository;
import com.vmazzola.orders.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

    }

    @Override
    public Order create(CreateOrderRequest request) {

        Order order = new Order();

        request.items().forEach(dto -> {

            Product product = productRepository.findById(dto.productId()).orElseThrow(() ->
                    new IllegalArgumentException("Product not found: " + dto.productId()));


            order.addItem(
                    product,
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

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getTotal()
        );
    }
}
