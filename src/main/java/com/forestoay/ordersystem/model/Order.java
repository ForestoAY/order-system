package com.forestoay.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders") // Ubah nama tabel menjadi "orders"
@Data
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "is_placed", nullable = false)
  private Boolean isPlaced = false; // Default order aktif

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<OrderItem> items = new ArrayList<>();

  @Transient // Tidak disimpan di database
  public Double getTotalAmount() {
    return items.stream()
        .mapToDouble(OrderItem::getTotal) // Ambil total dari masing-masing item
        .sum(); // Jumlahkan semua total
  }
}
