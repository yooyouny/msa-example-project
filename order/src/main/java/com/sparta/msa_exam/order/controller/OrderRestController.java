package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderCreateRequest;
import com.sparta.msa_exam.order.dto.OrderReadResponse;
import com.sparta.msa_exam.order.service.OrderFacadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {
  private final OrderFacadeService facadeService;

  @PostMapping
  public OrderReadResponse create(@RequestBody @Valid OrderCreateRequest request) { // TODO :: 유저정보
    return facadeService.create(request.name(), request.productIds());
  }
}
