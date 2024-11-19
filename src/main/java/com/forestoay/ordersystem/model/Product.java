package com.forestoay.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private Type type;

  private Double price;

  public Product(String name, Type type, Double price) {
    this.name = name;
    this.type = type;
    this.price = price;
  }
}
