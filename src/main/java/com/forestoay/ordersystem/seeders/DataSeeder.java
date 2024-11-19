package com.forestoay.ordersystem.seeders;

import com.forestoay.ordersystem.model.Order;
import com.forestoay.ordersystem.model.Type;
import com.forestoay.ordersystem.repository.OrderRepository;
import com.forestoay.ordersystem.repository.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

  private final TypeRepository typeRepository;
  private final OrderRepository orderRepository;

  public DataSeeder(TypeRepository typeRepository, OrderRepository orderRepository) {
    this.typeRepository = typeRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public void run(String... args) {
    seedTypes();
    seedInitialOrder();
  }

  private void seedTypes() {
    // Cek apakah Type sudah ada di database
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
    // Cek apakah order dengan ID 1 sudah ada
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
}
