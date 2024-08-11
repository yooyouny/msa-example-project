package com.sparta.msa_exam.product.repository;

import com.sparta.msa_exam.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>,
    QuerydslPredicateExecutor<Product>, ProductRepositoryQuery {}
