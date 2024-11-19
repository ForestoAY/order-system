package com.forestoay.ordersystem.controller;

import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.model.ResponseMessage;
import com.forestoay.ordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public Page<Product> getProducts(@RequestParam int page, @RequestParam int size) {
    return productService.getProducts(page, size);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.getProductById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Product saveProduct(@RequestBody Product product) {
    return productService.saveProduct(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseMessage> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
    try {
      productService.updateProduct(id, updatedProduct);
      return ResponseEntity.ok(new ResponseMessage("Product with ID " + id + " has been updated."));
    } catch (RuntimeException e) {
      return ResponseEntity.status(404).body(new ResponseMessage(e.getMessage()));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessage> deleteProduct(@PathVariable Long id) {
    String message = productService.deleteProduct(id);
    if (message.contains("not found")) {
        return ResponseEntity.status(404).body(new ResponseMessage(message));
    }
    return ResponseEntity.ok(new ResponseMessage(message));
}
}
