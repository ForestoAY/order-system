package com.forestoay.ordersystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore // Hilangkan ID dari serialisasi JSON jika tidak diperlukan
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  @JsonIgnore // Hilangkan referensi balik ke Order untuk menghindari loop
  private Order order;

  @Transient // Properti ini tidak disimpan di database
  public Double getTotal() {
    return this.product.getPrice() * this.quantity; // Hitung total
  }

  public void setQuantity(Integer quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than 0");
    }
    this.quantity = quantity;
  }
}
