package com.forestoay.ordersystem.dto;

import lombok.Data;

@Data
public class ProductRequest {
  private String name;
  private Long typeId;
  private Double price;
}