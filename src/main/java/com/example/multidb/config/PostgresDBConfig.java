package com.example.multidb.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.multidb.postgres.repository",
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager"
)
//@PropertySource("classpath:/application-${spring.profiles.active}.yml")
public class PostgresDBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.postgres-db.datasource.hikari")
    public HikariConfig postgresHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource postgresDataSource() {
        return new HikariDataSource(postgresHikariConfig());
    }


    @Bean
    public EntityManagerFactory postgresEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan("com.example.multidb.postgres.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManagerFactory());
        return transactionManager;
    }

}

