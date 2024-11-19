package com.forestoay.ordersystem.controller;

import com.forestoay.ordersystem.model.Order;
import com.forestoay.ordersystem.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  @Autowired
  private OrderService orderService;

  // Tambahkan produk ke keranjang
  @PostMapping("/add-to-cart")
  public ResponseEntity<Order> addToCart(@RequestParam Long productId, @RequestParam int quantity) {
    Order order = orderService.addToCart(productId, quantity);
    return ResponseEntity.ok(order);
  }

  // Selesaikan order
  @PostMapping("/place-order")
  public ResponseEntity<Order> placeOrder() {
    Order order = orderService.placeOrder();
    return ResponseEntity.ok(order);
  }

  // Tampilkan keranjang aktif
  @GetMapping("/cart")
  public ResponseEntity<List<Order>> getAllCarts() {
    List<Order> orders = orderService.getAllCarts();
    return ResponseEntity.ok(orders);
  }
}
