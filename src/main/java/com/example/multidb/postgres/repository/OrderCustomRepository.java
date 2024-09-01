package com.example.multidb.postgres.repository;

import com.example.multidb.postgres.entity.Order;

import java.util.List;

public interface OrderCustomRepository {
    List<Order> getOrders();
}
