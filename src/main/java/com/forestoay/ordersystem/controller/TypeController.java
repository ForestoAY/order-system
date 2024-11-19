package com.forestoay.ordersystem.controller;

import com.forestoay.ordersystem.model.Type;
import com.forestoay.ordersystem.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeController {

  @Autowired
  private TypeRepository typeRepository;

  @GetMapping
  public ResponseEntity<List<Type>> getAllTypes() {
    List<Type> types = typeRepository.findAll();
    return ResponseEntity.ok(types);
  }
}
