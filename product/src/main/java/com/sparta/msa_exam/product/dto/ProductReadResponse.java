package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.domain.Product;

public record ProductReadResponse(Long id, String name, int supplyPrice) {
  public static ProductReadResponse of(Product product){
    return new ProductReadResponse(product.getId(), product.getName(), product.getSupplyPrice());
  }
}
