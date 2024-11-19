package com.forestoay.ordersystem.repository;

import com.forestoay.ordersystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
