package com.sparta.msa_exam.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.dto.ProductCreateRequest;
import com.sparta.msa_exam.product.repository.ProductRepository;
import com.sparta.msa_exam.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  private static Product product;
  private static ProductCreateRequest requestDto;
  @Mock private ProductRepository productRepository;
  @InjectMocks private ProductService productService;

  @BeforeEach
  void init() {
    requestDto = new ProductCreateRequest("요아정", 12000);
    product = createProduct(requestDto.name(), requestDto.supplyPrice());
  }

  @Test
  void create() {
    when(productRepository.save(any(Product.class))).thenReturn(product);
    Assertions.assertDoesNotThrow(
        () -> productService.createProduct(requestDto.name(), requestDto.supplyPrice()));
  }

  private Product createProduct(String name, int supplyPrice) {
    return Product.builder().name(name).supplyPrice(supplyPrice).build();
  }
}
