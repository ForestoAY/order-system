package com.forestoay.ordersystem.seeders;

import com.forestoay.ordersystem.model.Type;
import com.forestoay.ordersystem.repository.TypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

  private final TypeRepository typeRepository;

  public DataSeeder(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  @Override
  public void run(String... args) {
    seedTypes();
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
}
