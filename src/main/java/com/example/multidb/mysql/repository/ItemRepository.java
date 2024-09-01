package com.example.multidb.mysql.repository;

import com.example.multidb.mysql.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemCustomRepository {

}
