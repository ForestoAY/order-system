package com.forestoay.ordersystem.service;

import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public Page<Product> getProducts(int page, int size) {
    return productRepository.findAll(PageRequest.of(page, size));
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product updatedProduct) {
    return productRepository.findById(id).map(existingProduct -> {
      existingProduct.setName(updatedProduct.getName());
      existingProduct.setType(updatedProduct.getType());
      existingProduct.setPrice(updatedProduct.getPrice());
      return productRepository.save(existingProduct);
    }).orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found."));
  }

  public String deleteProduct(Long id) {
    Optional<Product> product = productRepository.findById(id);

    if (product.isPresent()) {
      productRepository.deleteById(id);
      return "Product with ID " + id + " has been deleted.";
    } else {
      return "Product with ID " + id + " not found.";
    }
  }
}
