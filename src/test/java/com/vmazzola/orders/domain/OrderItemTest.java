package com.vmazzola.orders.domain;

import com.vmazzola.orders.domain.discount.NoDiscount;
import com.vmazzola.orders.domain.discount.PercentageDiscount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemTest {

    @Test
    void shouldCalculateTotalWithoutDiscount() {
        Product product = new Product("Pizza", new BigDecimal("50.00"));

        OrderItem orderItem = new OrderItem(new Order(), product, 2, new NoDiscount());

        assertEquals(new BigDecimal("100.00"), orderItem.getTotal());
    }

    @Test
    void shouldApplyDiscountWhenPresent() {
        Product product = new Product("Pizza", new BigDecimal("50.00"));

        OrderItem orderItem = new OrderItem(new Order(), product, 2, new PercentageDiscount(new BigDecimal("0.10")));

        assertEquals(new BigDecimal("90.00"), orderItem.getTotal());

    }
}
