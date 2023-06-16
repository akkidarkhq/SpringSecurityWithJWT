package com.unoveo.springjwt.persistence;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.hibernate.dialect.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;
import com.unoveo.springjwt.models.User;
import com.unoveo.springjwt.models.Role;


@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
//@PropertySource("classpath:application.properties")
public class PersistenceJPAConfig {

//    @PersistenceContext
//    EntityManager em1;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
//        emf.setPackagesToScan(new String[] { "com.unoveo.springjwt.model" });
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setJpaProperties(additionalProperties());
        return emf;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public static DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
//        dataSource.setUrl("jdbc:mariadb://localhost:3306/testjwt?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true");
//
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;



       return DataSourceBuilder.create()
               .driverClassName("org.mariadb.jdbc.Driver")
               .url("jdbc:mariadb://localhost:3306/testjwt?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true")
               .username("root")
               .password("root").build();


    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.format_sql","true");
        return properties;
    }
}

//spring.datasource.url=jdbc:mariadb://localhost:3306/testjwt?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
//        spring.datasource.username=root
//        spring.datasource.password=root
//
//        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
//        spring.jpa.hibernate.ddl-auto=update
