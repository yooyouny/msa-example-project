package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.domain.Product;
import java.io.Serializable;

public record ProductReadResponse(Long id, String name, int supplyPrice) implements Serializable {
  public static ProductReadResponse of(Product product){
    return new ProductReadResponse(product.getId(), product.getName(), product.getSupplyPrice());
  }
}
