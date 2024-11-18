package com.forestoay.ordersystem.repository;

import com.forestoay.ordersystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
