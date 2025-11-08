package com.studentmgmt;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.studentmgmt")
@EnableTransactionManagement // *** CRITICAL: Enables @Transactional annotation ***
public class AppConfig {

    // 1. Define the DataSource (H2 In-Memory Database)
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        // DB_CLOSE_DELAY=-1 keeps the in-memory database alive
        dataSource.setUrl("jdbc:h2:mem:studentdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    // 2. Configure the Hibernate SessionFactory
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        // Tell Hibernate where to find the @Entity classes
        sessionFactory.setPackagesToScan("com.studentmgmt.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    // 3. Define Hibernate properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", "true"); // Show SQL in console
        properties.put("hibernate.format_sql", "true"); // Pretty-print SQL
        // hbm2ddl.auto = create-drop
        // 'create-drop' automatically creates the schema on startup and drops it on shutdown
        // Perfect for a mini-project!
        properties.put("hibernate.hbm2ddl.auto", "create-drop"); 
        return properties;
    }

    // 4. Configure the Transaction Manager
    // This bean is used by @EnableTransactionManagement to manage transactions
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}