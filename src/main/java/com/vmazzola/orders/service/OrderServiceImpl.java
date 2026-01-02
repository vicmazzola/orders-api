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
    public OrderResponse findResponseById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return toResponse(order);
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

    @Override
    public OrderResponse update(Long id, CreateOrderRequest request) {

        // 1. Load existing order or fail
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));


        // 2. Remove all existing items (orphanRemoval will delete them)
        order.clearItems();

        // 3. Add new items from request (full replacement)
        request.items().forEach(dto -> {

            Product product = productRepository.findById(dto.productId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Product not found: " + dto.productId())
                    );

            order.addItem(
                    product,
                    dto.quantity(),
                    new NoDiscount()
            );

        });

        // 4. Save and return DTO
        Order saved = orderRepository.save(order);

        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {

        // 1. Ensure order exists (consistent error handling)
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        // 2. Delete aggreate root (items are deleted via cascade)
        orderRepository.delete(order);
    }
}
