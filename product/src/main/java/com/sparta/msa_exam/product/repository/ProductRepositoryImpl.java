package com.sparta.msa_exam.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.domain.QProduct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryQuery {
  private final JPAQueryFactory queryFactory;
  private final QProduct qProduct = QProduct.product;

  @Override
  public Page<Product> findFetchAll(int minPrice, int maxPrice, Pageable pageable) {
    BooleanExpression priceCondition = createPriceCondition(minPrice, maxPrice);

    JPAQuery<Product> query = queryFactory.selectFrom(qProduct)
        .where(priceCondition)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());

    List<Product> fetch = query.fetch();

    JPAQuery<Long> countQuery = queryFactory.select(Wildcard.count)
        .from(qProduct)
        .where(priceCondition);

    return PageableExecutionUtils.getPage(fetch, pageable, countQuery::fetchOne);
  }

  private BooleanExpression createPriceCondition(int minPrice, int maxPrice) {
    BooleanExpression condition = null;

    if (minPrice > 0) {
      condition = qProduct.supplyPrice.goe(minPrice);
    }
    if (maxPrice > 0) {
      condition = (condition != null)
          ? condition.and(qProduct.supplyPrice.loe(maxPrice))
          : qProduct.supplyPrice.loe(maxPrice);
    }

    return condition;
    }
}
