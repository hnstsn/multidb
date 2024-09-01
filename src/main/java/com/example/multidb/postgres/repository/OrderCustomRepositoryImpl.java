package com.example.multidb.postgres.repository;

import com.example.multidb.postgres.entity.Order;
import com.example.multidb.postgres.entity.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final QOrder ORDER = QOrder.order;

    private final JPAQueryFactory queryFactory;

    // 생성자 주입
    public OrderCustomRepositoryImpl(@Qualifier("postgresQueryFactory") JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Order> getOrders() {
        return queryFactory.selectFrom(ORDER).fetch();
    }
}
