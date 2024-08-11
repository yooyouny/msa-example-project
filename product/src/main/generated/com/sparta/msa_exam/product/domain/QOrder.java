package com.sparta.msa_exam.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 939101864L;

    public static final QOrder order = new QOrder("order1");

    public final StringPath name = createString("name");

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final SetPath<OrderProduct, QOrderProduct> productsIds = this.<OrderProduct, QOrderProduct>createSet("productsIds", OrderProduct.class, QOrderProduct.class, PathInits.DIRECT2);

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}

