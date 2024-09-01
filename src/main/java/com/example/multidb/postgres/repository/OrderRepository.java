package com.example.multidb.postgres.repository;

import com.example.multidb.postgres.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
}
