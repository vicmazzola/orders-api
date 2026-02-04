package com.vmazzola.orders.service;

import com.vmazzola.orders.api.dto.CreateOrderRequest;
import com.vmazzola.orders.api.dto.OrderItemRequest;
import com.vmazzola.orders.domain.Order;
import com.vmazzola.orders.domain.Product;
import com.vmazzola.orders.repository.OrderRepository;
import com.vmazzola.orders.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void create_shouldCreateAndSaveOrder() {
        Product product = new Product("Laptop", BigDecimal.valueOf(3000));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        CreateOrderRequest request = new CreateOrderRequest(List.of(new OrderItemRequest(1L, 2)));

        Order result = orderService.create(request);

        assertNotNull(result);

        assertEquals(BigDecimal.valueOf(6000), result.getTotal());

        verify(productRepository).findById(1L);
        verify(orderRepository).save(any(Order.class));


    }

    @Test
    void findById_whenOrdersExists_shouldReturnOrder() {
        Order order = new Order();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.findById(1L);

        assertNotNull(result);
        verify(orderRepository).findById(1L);
    }


}
