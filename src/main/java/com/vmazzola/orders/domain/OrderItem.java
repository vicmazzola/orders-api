package com.vmazzola.orders.domain;

import com.vmazzola.orders.domain.discount.DiscountPolicy;
import com.vmazzola.orders.domain.discount.NoDiscount;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = false)
    private Product product;


    private int quantity;

    @Transient
    private DiscountPolicy discountPolicy;

    public OrderItem(Order order, Product product, int quantity, DiscountPolicy discountPolicy) {

        Objects.requireNonNull(order, "Order cannot be null");
        Objects.requireNonNull(product, "Product cannot be null");
        Objects.requireNonNull(discountPolicy, "Discount cannot be null");

        if (quantity <= 0) {
            throw new IllegalArgumentException("You can’t order 0 or negative items");
        }

        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.discountPolicy = discountPolicy;

    }

    public BigDecimal getTotal() {
        DiscountPolicy policy = discountPolicy != null ? discountPolicy : new NoDiscount();

        BigDecimal baseTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));

        return policy.apply(baseTotal);
    }
}
