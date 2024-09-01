package com.example.multidb.postgres;

import com.example.multidb.postgres.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void getOrdersTest() {
        var orders = orderRepository.getOrders();
        Assertions.assertThat(orders).hasSize(1);
    }
}
