package com.sparta.msa_exam.order.service;

import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product")
public interface ProductClient {
  @GetMapping("/products/{id}")
  Optional<Long> getProductId(@PathVariable("id") Long id);
}
