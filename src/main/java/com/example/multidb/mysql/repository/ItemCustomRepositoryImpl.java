package com.example.multidb.mysql.repository;

import com.example.multidb.mysql.entity.Item;
import com.example.multidb.mysql.entity.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class ItemCustomRepositoryImpl implements ItemCustomRepository {

    private final QItem ITEM = QItem.item;

    private final JPAQueryFactory queryFactory;

    // 생성자 주입
    public ItemCustomRepositoryImpl(@Qualifier("mysqlQueryFactory") JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Item> getItems() {
        return queryFactory.selectFrom(ITEM).fetch();
    }
}
