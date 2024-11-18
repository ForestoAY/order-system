package com.forestoay.ordersystem.service;

import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public Page<Product> getAllProducts(int page, int size) {
    return productRepository.findAll(PageRequest.of(page, size));
  }

  public Product addProduct(Product product) {
    return productRepository.save(product);
  }
}
