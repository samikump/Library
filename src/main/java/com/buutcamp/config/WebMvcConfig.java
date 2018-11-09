package com.buutcamp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan("com.buutcamp")
@EnableWebMvc
@EnableTransactionManagement
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setPrefix("/WEB-INF/views/");
        urlBasedViewResolver.setSuffix(".jsp");
        urlBasedViewResolver.setViewClass(JstlView.class);

        return urlBasedViewResolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {

        //Initialize sessionfactorybean
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        //set sessionfactory data source
        sessionFactory.setDataSource(myDataSource());
        //tell hibernate where to find classes to manage
        sessionFactory.setPackagesToScan("com.buutcamp.controller.book");
        //set hibernate proterties
        sessionFactory.setHibernateProperties(hibernateProperties());
        //return sessionfactory
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory ) {

        HibernateTransactionManager txManager =
                    new HibernateTransactionManager();

        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    DataSource myDataSource() throws SQLException {
        //Create datasource
        // MariaDbDataSource dataSource = new MariaDbDataSource();
        BasicDataSource dataSource = new BasicDataSource();

        //Set connection info
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/library?serverTimezone=UTC&allowPublicKeyRetrieval=true");

        dataSource.setUsername("secuser");
        dataSource.setPassword("secpw");

        //return the datasource
        return dataSource;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
            }
        };
    }

}






