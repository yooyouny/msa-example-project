package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.domain.OrderProduct;

public record OrderProductResponse(
    Long id,
    Long orderId,
    Long productId
) {
  public static OrderProductResponse of(OrderProduct orderProduct){
   return new OrderProductResponse(orderProduct.getId(), orderProduct.getOrder().getOrderId(), orderProduct.getProductId());
  }
}
