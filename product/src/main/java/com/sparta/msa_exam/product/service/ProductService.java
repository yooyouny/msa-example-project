package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.dto.ProductReadResponse;
import com.sparta.msa_exam.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  public Page<ProductReadResponse> getProductList(
      int page, int size, String sortBy, boolean isAsc) {
    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

    Page<Product> productPage = productRepository.findAll(pageable);
    List<ProductReadResponse> productList =
        productPage.stream()
            .map(product -> ProductReadResponse.of(product))
            .collect(Collectors.toList());

    return new PageImpl<>(productList, pageable, productPage.getTotalPages());
  }
}
