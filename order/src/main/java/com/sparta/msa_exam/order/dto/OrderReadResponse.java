package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.domain.Order;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderReadResponse(Long id, String name, Set<OrderProductResponse> productIds) {
  public static OrderReadResponse of(Order order) {
    Set<OrderProductResponse> productsIds =
        order.getProductsIds().stream()
            .map(
                orderProduct ->
                    new OrderProductResponse(
                        orderProduct.getId(),
                        orderProduct.getOrder().getOrderId(),
                        orderProduct.getProductId()))
            .collect(Collectors.toSet());
    return new OrderReadResponse(order.getOrderId(), order.getName(), productsIds);
  }
}
