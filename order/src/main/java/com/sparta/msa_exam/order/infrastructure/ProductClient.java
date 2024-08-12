package com.sparta.msa_exam.order.infrastructure;

import com.sparta.msa_exam.order.service.ProductService;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product")
public interface ProductClient extends ProductService {
  @GetMapping("/products/{id}")
  Optional<Long> getProductIds(@PathVariable("id") Long id);
}
