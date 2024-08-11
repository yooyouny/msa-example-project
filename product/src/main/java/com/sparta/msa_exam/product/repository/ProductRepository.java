package com.sparta.msa_exam.product.repository;

import com.sparta.msa_exam.product.domain.Product;
import java.awt.print.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findAll(Pageable pageable);
}
