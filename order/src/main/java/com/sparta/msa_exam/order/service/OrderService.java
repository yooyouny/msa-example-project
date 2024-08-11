package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.domain.Order;
import com.sparta.msa_exam.order.domain.OrderProduct;
import com.sparta.msa_exam.order.dto.OrderReadResponse;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;
import jakarta.ws.rs.NotFoundException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderProductRepository orderProductRepository;

  public OrderReadResponse create(String name, Set<OrderProduct> orderProducts) {
    Order order = Order.builder().name(name).productsIds(orderProducts).build();
    saveOrderProducts(order);
    return OrderReadResponse.of(orderRepository.save(order));
  }

  public OrderReadResponse getOrder(Long orderId){
    Order savedOrder = orderRepository.findById(orderId).orElseThrow(()
        -> new NotFoundException("해당 주문건을 찾을 수 없습니다"));
    return OrderReadResponse.of(savedOrder);
  }

  private void saveOrderProducts(Order order) {
    order.getProductsIds().stream()
        .forEach(
            orderProduct -> {
              orderProduct.setOrder(order);
              orderProductRepository.save(orderProduct);
            });
  }
}
