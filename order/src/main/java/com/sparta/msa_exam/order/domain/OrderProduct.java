package com.sparta.msa_exam.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  private Long productId;

  public OrderProduct(Long productId) {
    this.productId = productId;
  }
  public void setOrder(Order order) {
    this.order = order;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderProduct that = (OrderProduct) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productId);
  }
}
