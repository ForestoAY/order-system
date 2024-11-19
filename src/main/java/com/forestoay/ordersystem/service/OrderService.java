package com.forestoay.ordersystem.service;

import com.forestoay.ordersystem.model.Order;
import com.forestoay.ordersystem.model.OrderItem;
import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.repository.OrderItemRepository;
import com.forestoay.ordersystem.repository.OrderRepository;
import com.forestoay.ordersystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Autowired
  private ProductRepository productRepository;

  public Order addToCart(Long productId, int quantity) {
    // Cari order aktif (belum selesai)
    Order activeOrder = orderRepository.findByIsPlacedFalse()
        .orElseGet(() -> {
          // Jika tidak ada order aktif, buat yang baru
          Order newOrder = new Order();
          newOrder.setIsPlaced(false);
          return orderRepository.save(newOrder);
        });

    // Cari produk berdasarkan ID
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));

    // Cek apakah produk sudah ada di keranjang
    Optional<OrderItem> existingItem = activeOrder.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst();

    if (existingItem.isPresent()) {
      // Jika produk sudah ada, tambahkan jumlahnya
      existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
      System.out.println("Updated quantity for product ID: " + productId);
    } else {
      // Jika produk belum ada, tambahkan sebagai item baru
      OrderItem orderItem = new OrderItem();
      orderItem.setProduct(product);
      orderItem.setQuantity(quantity);
      orderItem.setOrder(activeOrder);

      activeOrder.getItems().add(orderItem);
      System.out.println("Added new product ID: " + productId + " to cart ID: " + activeOrder.getId());
    }

    // Simpan dan kembalikan order
    return orderRepository.save(activeOrder);
  }

  public Order placeOrder() {
    // Cari order aktif
    Order activeOrder = orderRepository.findByIsPlacedFalse()
        .orElseThrow(() -> new RuntimeException("No active order found"));

    // Tandai order sebagai selesai
    activeOrder.setIsPlaced(true);
    orderRepository.save(activeOrder);

    // Buat order baru (untuk pembelian berikutnya)
    Order newOrder = new Order();
    newOrder.setIsPlaced(false); // Order baru aktif
    return orderRepository.save(newOrder);
  }

  public Order getActiveOrder() {
    // Ambil order aktif
    return orderRepository.findByIsPlacedFalse()
        .orElseThrow(() -> new RuntimeException("No active cart found"));
  }

  public List<Order> getAllCarts() {
    return orderRepository.findAll(); // Mengambil semua order dari database
  }
}
