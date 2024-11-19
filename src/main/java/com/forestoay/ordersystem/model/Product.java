package com.forestoay.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private Type type;

  private Double price;
}
