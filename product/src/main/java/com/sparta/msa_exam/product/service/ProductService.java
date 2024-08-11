package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public void createProduct(String name, int supplyPrice) {
    Product product = Product.builder().name(name).supplyPrice(supplyPrice).build();

    productRepository.save(product);
  }
}
