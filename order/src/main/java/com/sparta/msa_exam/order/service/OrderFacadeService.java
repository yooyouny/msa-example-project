package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.domain.OrderProduct;
import com.sparta.msa_exam.order.dto.OrderProductRequest;
import com.sparta.msa_exam.order.dto.OrderReadResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderFacadeService {
  private final OrderService orderService;
  private final ProductClient productClient;

  @Transactional
  public OrderReadResponse create(String name, List<OrderProductRequest> requestDtos) {
    Set<OrderProduct> orderProducts = getOrderProducts(requestDtos);
    return orderService.create(name, orderProducts);
  }

  @Transactional
  public OrderReadResponse addProduct(Long orderId, List<Long> productIds) {
    Set<Long> findIds =
        productIds.stream()
            .map(productId -> productClient.getProductId(productId))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
    return orderService.addProduct(orderId, findIds);
  }

  private Set<OrderProduct> getOrderProducts(List<OrderProductRequest> requestDtos) {
    return requestDtos.stream()
        .map(requestDto -> productClient.getProductId(requestDto.productId()))
        .filter(Optional::isPresent)
        .map(optionalProductId -> new OrderProduct(optionalProductId.get()))
        .collect(Collectors.toSet());
  }
}
