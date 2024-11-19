package com.forestoay.ordersystem.seeders;

import com.forestoay.ordersystem.model.Order;
import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.model.Type;
import com.forestoay.ordersystem.repository.OrderRepository;
import com.forestoay.ordersystem.repository.ProductRepository;
import com.forestoay.ordersystem.repository.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

  private final TypeRepository typeRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  public DataSeeder(TypeRepository typeRepository, OrderRepository orderRepository,
      ProductRepository productRepository) {
    this.typeRepository = typeRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  @Override
  public void run(String... args) {
    seedTypes();
    seedInitialOrder();
    seedProducts();
  }

  private void seedTypes() {
    if (typeRepository.count() == 0) {
      List<Type> types = Arrays.asList(
          new Type("Electronic"),
          new Type("Book"),
          new Type("Food"));

      typeRepository.saveAll(types);
      System.out.println("Type data seeded!");
    } else {
      System.out.println("Type data already exists.");
    }
  }

  private void seedInitialOrder() {
    if (!orderRepository.existsById(1L)) {
      Order initialOrder = new Order();
      initialOrder.setId(1L); // Hardcode ID 1
      initialOrder.setIsPlaced(false); // Set sebagai aktif
      orderRepository.save(initialOrder);
      System.out.println("Seeded active order with ID 1.");
    } else {
      System.out.println("Order with ID 1 already exists.");
    }
  }

  private void seedProducts() {
    if (productRepository.count() == 0) {
      // Ambil data Type dari database
      List<Type> types = typeRepository.findAll();

      Type electronic = types.stream()
          .filter(t -> t.getName().equalsIgnoreCase("Electronic"))
          .findFirst()
          .orElseThrow(() -> new RuntimeException("Type 'Electronic' not found"));
      Type book = types.stream()
          .filter(t -> t.getName().equalsIgnoreCase("Book"))
          .findFirst()
          .orElseThrow(() -> new RuntimeException("Type 'Book' not found"));
      Type food = types.stream()
          .filter(t -> t.getName().equalsIgnoreCase("Food"))
          .findFirst()
          .orElseThrow(() -> new RuntimeException("Type 'Food' not found"));

      // Buat data produk
      List<Product> products = Arrays.asList(
          new Product("Laptop", electronic, 1500.0),
          new Product("Smartphone", electronic, 1000.0),
          new Product("Java Programming", book, 50.0),
          new Product("Fiction Novel", book, 30.0),
          new Product("Pizza", food, 20.0));

      productRepository.saveAll(products);
      System.out.println("Product data seeded!");
    } else {
      System.out.println("Product data already exists.");
    }
  }
}