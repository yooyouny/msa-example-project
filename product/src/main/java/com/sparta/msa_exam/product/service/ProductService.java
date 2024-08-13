package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.dto.ProductReadResponse;
import com.sparta.msa_exam.product.repository.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
  @Transactional
  @CacheEvict(cacheNames = "productList", allEntries = true)
  public ProductReadResponse createProduct(String name, int supplyPrice) {
    Product product = Product.builder().name(name).supplyPrice(supplyPrice).build();
    return ProductReadResponse.of(productRepository.save(product));
  }

  public Optional<Long> getProductId(Long id){
    return productRepository.findById(id)
        .map(Product::getId);
  }

  @Cacheable(cacheNames = "productList")
  public Page<ProductReadResponse> getProductList(
      int page, int size, String sortBy, int minPrice, int maxPrice, boolean isAsc) {
    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
    return productRepository
        .findFetchAll(minPrice, maxPrice, pageable)
        .map(product -> ProductReadResponse.of(product));
  }
}
