package com.forestoay.ordersystem.controller;

import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.addProduct(product);
  }

  @GetMapping
  public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    return productService.getAllProducts(page, size);
  }
}
