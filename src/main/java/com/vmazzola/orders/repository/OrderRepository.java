package com.vmazzola.orders.repository;

import com.vmazzola.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long > {
}
