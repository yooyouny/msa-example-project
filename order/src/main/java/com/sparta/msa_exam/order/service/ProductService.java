package com.sparta.msa_exam.order.service;

import java.util.Optional;

public interface ProductService {
  Optional<Long> getProductId(Long id);
}
