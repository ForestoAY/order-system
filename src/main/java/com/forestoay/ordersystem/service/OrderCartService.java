package com.forestoay.ordersystem.service;

import com.forestoay.ordersystem.model.OrderCart;
import com.forestoay.ordersystem.model.Product;
import com.forestoay.ordersystem.repository.OrderCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCartService {

  @Autowired
  private OrderCartRepository orderCartRepository;

  public OrderCart createOrderCart(OrderCart orderCart) {
    return orderCartRepository.save(orderCart);
  }

  public OrderCart getOrderCart(Long id) {
    return orderCartRepository.findById(id).orElse(null);
  }

  public void addProductToCart(OrderCart orderCart, Product product) {
    orderCart.getProducts().add(product);
    orderCart.setTotalAmount(orderCart.getTotalAmount() + product.getPrice());
    orderCartRepository.save(orderCart);
  }
}
