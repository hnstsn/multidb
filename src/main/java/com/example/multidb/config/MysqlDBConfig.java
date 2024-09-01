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
        basePackages = "com.example.multidb.mysql.repository",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
//@PropertySource("classpath:/application-${spring.profiles.active}.yml")
public class MysqlDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.mysql-db.datasource.hikari")
    public HikariConfig mysqlHikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean
    public DataSource mysqlDataSource() {
        return new HikariDataSource(mysqlHikariConfig());
    }


    @Primary
    @Bean
    public EntityManagerFactory mysqlEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mysqlDataSource());
        em.setPackagesToScan("com.example.multidb.mysql.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Primary
    @Bean
    public PlatformTransactionManager mysqlTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mysqlEntityManagerFactory());
        return transactionManager;
    }

}

