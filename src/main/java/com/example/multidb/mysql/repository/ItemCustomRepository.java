package com.example.multidb.mysql.repository;

import com.example.multidb.mysql.entity.Item;

import java.util.List;

public interface ItemCustomRepository {
    List<Item> getItems();
}
