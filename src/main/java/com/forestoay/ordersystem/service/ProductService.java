package com.forestoay.ordersystem.service;

import com.forestoay.ordersystem.dto.ProductRequest;
import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.model.Type;
import com.forestoay.ordersystem.repository.ProductRepository;
import com.forestoay.ordersystem.repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private TypeRepository typeRepository;

  public Page<Product> getProducts(int page, int size) {
    return productRepository.findAll(PageRequest.of(page, size)); // Type ditampilkan dengan id dan name
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public Product saveProduct(ProductRequest productRequest) {
    // Validasi Type berdasarkan typeId
    Type type = typeRepository.findById(productRequest.getTypeId())
        .orElseThrow(() -> new RuntimeException("Type with ID " + productRequest.getTypeId() + " not found"));

    // Buat objek Product
    Product product = new Product();
    product.setName(productRequest.getName());
    product.setType(type); // Isi Type berdasarkan hasil query
    product.setPrice(productRequest.getPrice());

    return productRepository.save(product);
  }

  public Product updateProduct(Long id, ProductRequest productRequest) {
    // Validasi Type berdasarkan typeId
    Type type = typeRepository.findById(productRequest.getTypeId())
        .orElseThrow(() -> new RuntimeException("Type with ID " + productRequest.getTypeId() + " not found"));

    // Cari produk berdasarkan ID
    return productRepository.findById(id).map(existingProduct -> {
      existingProduct.setName(productRequest.getName());
      existingProduct.setType(type); // Perbarui Type
      existingProduct.setPrice(productRequest.getPrice());
      return productRepository.save(existingProduct);
    }).orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
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
