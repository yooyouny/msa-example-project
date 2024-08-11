package com.sparta.msa_exam.product.repository;

import com.sparta.msa_exam.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQuery {
  Page<Product> findFetchAll(int minPrice, int maxPrice, Pageable pageable);
}