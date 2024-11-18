package com.forestoay.ordersystem.controller;

import com.forestoay.ordersystem.model.OrderCart;
import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.service.OrderCartService;
import com.forestoay.ordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-cart")
public class OrderCartController {

  @Autowired
  private OrderCartService orderCartService;

  @Autowired
  private ProductService productService;

  @PostMapping
  public OrderCart createOrderCart(@RequestBody OrderCart orderCart) {
    return orderCartService.createOrderCart(orderCart);
  }

  @GetMapping("/{id}")
  public OrderCart getOrderCart(@PathVariable Long id) {
    return orderCartService.getOrderCart(id);
  }

  @PostMapping("/{orderCartId}/add-product/{productId}")
  public void addProductToCart(@PathVariable Long orderCartId, @PathVariable Long productId) {
    OrderCart orderCart = orderCartService.getOrderCart(orderCartId);
    Product product = productService.getAllProducts(0, 10).getContent().stream()
        .filter(p -> p.getId().equals(productId))
        .findFirst().orElse(null);
    if (orderCart != null && product != null) {
      orderCartService.addProductToCart(orderCart, product);
    }
  }
}
