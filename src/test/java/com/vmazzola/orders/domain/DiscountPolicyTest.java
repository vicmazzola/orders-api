package com.vmazzola.orders.domain;

import com.vmazzola.orders.domain.discount.NoDiscount;
import com.vmazzola.orders.domain.discount.PercentageDiscount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountPolicyTest {

    @Test
    void noDiscountShouldReturnSameValue() {

        NoDiscount noDiscount = new NoDiscount();
        BigDecimal result = noDiscount.apply(new BigDecimal("100.00"));

        assertEquals(new BigDecimal("100.00"), result);

    }

    @Test
    void percentageDiscountShouldApplyCorrectly() {

        PercentageDiscount percentageDiscount = new PercentageDiscount(new BigDecimal("0.10"));
        BigDecimal result = percentageDiscount.apply(new BigDecimal("100.00"));

        assertEquals(new BigDecimal("90.00"), result);
    }

    @Test
    void percentageDiscountShouldRejectInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new PercentageDiscount(new BigDecimal("1.5")));
    }
}
