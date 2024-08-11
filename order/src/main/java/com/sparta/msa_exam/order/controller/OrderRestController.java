package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderCreateRequest;
import com.sparta.msa_exam.order.dto.OrderReadResponse;
import com.sparta.msa_exam.order.service.OrderFacadeService;
import com.sparta.msa_exam.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderRestController {
  private final OrderFacadeService facadeService;
  private final OrderService orderService;

  @PostMapping
  public OrderReadResponse create(@RequestBody @Valid OrderCreateRequest request) { // TODO :: 유저정보
    return facadeService.create(request.name(), request.productIds());
  }

  @GetMapping("/{orderId}")
  public OrderReadResponse getOrder(@PathVariable("orderId") @NotNull Long orderId) {
    return orderService.getOrder(orderId);
  }
}
