package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.domain.OrderProduct;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderProductResponse implements Serializable {
  private Long id;
  private Long orderId;
  private Long productId;

  public OrderProductResponse(Long id, Long orderId, Long productId) {
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
  }
  public static OrderProductResponse of(OrderProduct orderProduct){
   return new OrderProductResponse(orderProduct.getId(), orderProduct.getOrder().getOrderId(), orderProduct.getProductId());
  }
}
