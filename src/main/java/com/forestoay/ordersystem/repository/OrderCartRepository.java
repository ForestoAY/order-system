package com.forestoay.ordersystem.repository;

import com.forestoay.ordersystem.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {
}
