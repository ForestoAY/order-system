package com.forestoay.ordersystem.repository;

import com.forestoay.ordersystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  Optional<Order> findByIsPlacedFalse(); // Cari order aktif (yang belum selesai)
}
