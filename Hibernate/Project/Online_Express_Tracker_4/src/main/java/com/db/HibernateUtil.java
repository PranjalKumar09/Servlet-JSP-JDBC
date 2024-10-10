package com.db;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();

                // Add annotated classes (add all your entity classes here)
                configuration.addAnnotatedClass(com.entity.User.class);  // Don't forget to add the actual entities
                configuration.addAnnotatedClass(com.entity.Expense.class);

                // Build session factory using the service registry
                ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("There was an error building the session factory", e);
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        // Set Hibernate properties for JDBC connection
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/my_db");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "09072005");

        // Hibernate settings
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.HBM2DDL_AUTO, "update");  // Adjust this as necessary
        properties.put(Environment.SHOW_SQL, true);  // Enable SQL logging

        // Set these properties to the configuration
        configuration.setProperties(properties);
        return configuration;
    }
}
