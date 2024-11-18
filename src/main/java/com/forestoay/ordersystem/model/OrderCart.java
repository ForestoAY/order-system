package com.forestoay.ordersystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class OrderCart {

  @Id
  private Long id;
  @OneToMany
  private List<Product> products;
  private double totalAmount;

  // Constructor
  public OrderCart() {
  }

  public OrderCart(Long id, List<Product> products, double totalAmount) {
    this.id = id;
    this.products = products;
    this.totalAmount = totalAmount;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }
}
