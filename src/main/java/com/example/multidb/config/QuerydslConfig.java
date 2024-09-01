package com.example.multidb.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

    @Bean(name = "mysqlQueryFactory")
    public JPAQueryFactory mysqlQueryFactory(@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JPAQueryFactory(entityManager);
    }

    @Bean(name = "postgresQueryFactory")
    public JPAQueryFactory postgresQueryFactory(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JPAQueryFactory(entityManager);
    }
}
