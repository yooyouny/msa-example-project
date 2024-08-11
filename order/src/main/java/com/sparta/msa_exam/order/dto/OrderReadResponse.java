package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.domain.Order;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderReadResponse implements Serializable {
  private Long id;
  private String name;
  private Set<OrderProductResponse> productIds;

  public OrderReadResponse(Long id, String name, Set<OrderProductResponse> productIds) {
    this.id = id;
    this.name = name;
    this.productIds = productIds;
  }

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
