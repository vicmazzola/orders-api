package com.vmazzola.orders.domain;

import com.vmazzola.orders.domain.discount.NoDiscount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void shouldSumAllItemTotals() {
        Order order = new Order();
        Product playstation = new Product("Playstation", new BigDecimal("500.00"));
        Product dualshock = new Product("Dualshock", new BigDecimal("60.00"));

        order.addItem(playstation, 1, new NoDiscount());
        order.addItem(dualshock, 1, new NoDiscount());

        assertEquals(new BigDecimal("560.00"), order.getTotal());
    }

    @Test
    void shouldReturnZeroWhenNoItems() {
        Order order = new Order();

        assertEquals(new BigDecimal("0"), order.getTotal());
    }
}
