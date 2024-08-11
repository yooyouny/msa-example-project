package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductCreateRequest;
import com.sparta.msa_exam.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductRestController {
  private final ProductService productService;

  @PostMapping
  public void createProduct(@RequestBody @Valid ProductCreateRequest requestDto) {
    productService.createProduct(requestDto.name(), requestDto.supplyPrice());
  }
}
