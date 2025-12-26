package com.vmazzola.orders.api;

import com.vmazzola.orders.api.dto.CreateOrderRequest;
import com.vmazzola.orders.api.dto.OrderResponse;
import com.vmazzola.orders.domain.Order;
import com.vmazzola.orders.exception.OrderNotFoundException;
import com.vmazzola.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /orders
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {

        Order created = orderService.create(request);

        OrderResponse response = new OrderResponse(
                created.getId(),
                created.getTotal()
        );

        return ResponseEntity.ok(response);

    }

    // GET /orders{id}
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.findById(id);

        return ResponseEntity.ok(order);
    }

    // GET /orders
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
