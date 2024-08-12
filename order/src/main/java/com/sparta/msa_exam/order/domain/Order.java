package com.sparta.msa_exam.order.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column private String name;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<OrderProduct> productsIds = new LinkedHashSet<>();

  @Builder
  private Order(String name, Set<OrderProduct> productsIds) {
    this.name = name;
    this.productsIds = productsIds;
  }

  public Set<Long> getOrderedProductsIds() {
    return this.productsIds.stream()
        .map(orderProduct -> orderProduct.getProductId())
        .collect(Collectors.toSet());
  }

  public boolean addOrderProduct(OrderProduct orderProduct) {
    if (!this.productsIds.contains(orderProduct)) {
      this.productsIds.add(orderProduct);
      orderProduct.setOrder(this);
      return true;
    }
    return false;
  }
}