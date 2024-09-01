package com.example.multidb.mysql;

import com.example.multidb.mysql.entity.Item;
import com.example.multidb.mysql.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void getItemsTest() {
        var items = itemRepository.getItems();
        Assertions.assertThat(items).hasSize(1);
    }
}
