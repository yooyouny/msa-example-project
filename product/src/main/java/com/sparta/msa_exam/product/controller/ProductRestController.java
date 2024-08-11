package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductCreateRequest;
import com.sparta.msa_exam.product.dto.ProductReadResponse;
import com.sparta.msa_exam.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/{id}")
  public Optional<Long> getProductId(@RequestParam("id") @NotNull Long id){
    return productService.getProductId(id);
  }

  @GetMapping
  public Page<ProductReadResponse> getProductList(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size,
      @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
      @RequestParam(value = "minPrice", required = false, defaultValue = "0") int minPrice,
      @RequestParam(value = "maxPrice", required = false) int maxPrice,
      @RequestParam("isAsc") boolean isAsc) {
    return productService.getProductList(page, size, sortBy, minPrice, maxPrice, isAsc);
  }
}
