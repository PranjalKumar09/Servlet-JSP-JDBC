package com.pranjal.conn;

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
            Configuration configuration = new Configuration();
            Properties properties = new Properties();

        properties.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        properties.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/my_db");
        properties.put("jakarta.persistence.jdbc.user", "root");
        properties.put("jakarta.persistence.jdbc.password", "09072005");

            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.SHOW_SQL, true);

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(com.pranjal.entity.Student.class);
            


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry); // Use the service registry to build
                                                                                 // session factory
        }
        return sessionFactory;
    }
}
