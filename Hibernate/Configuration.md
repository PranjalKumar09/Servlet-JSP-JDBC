# Hibernate Configuration in Java

Hibernate is an object-relational mapping (ORM) framework for Java, allowing developers to work with databases using Java objects. There are two primary methods for configuring Hibernate: using XML and Java configuration.

## 1. XML Configuration

This is a traditional way to configure Hibernate using an XML file (`hibernate.cfg.xml`).

### Example XML Configuration

```xml
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 5.3//EN" "http://hibernate.org/dtd/hibernate-configuration-3.0.dtd">  

<hibernate-configuration>
    <session-factory name="java:hibernate/SessionFactory">
        <property name="connection.driver.class">com.mysql.cj.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/my_db</property>
        <property name="connection.username">root</property>
        <property name="connection.password">password</property>
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hbm2ddl.auto">update</property>

        <!-- Optional properties -->
        <property name="show_sql">true</property>
        <property name="format_sql">true</property>
    </session-factory>
</hibernate-configuration>
```
### Key Properties Explained

- **connection.driver.class**: Specifies the JDBC driver for MySQL.
- **connection.url**: The URL for the database connection, including the database name.
- **connection.username**: The username used to connect to the database.
- **connection.password**: The password for the specified username.
- **dialect**: Specifies the SQL dialect for the database being used 
- **hbm2ddl.auto**: This property defines the schema generation strategy. Common values are:
    - **update**: Updates the database schema without losing existing data.
    - **zshow_sql**: If set to true, Hibernate will print SQL statements to the console.
- **format_sql**: If set to true, it formats the printed SQL for readability.


#### Example of Configuring Hibernate Using Java Code

In this section, we will see how to programmatically configure Hibernate in Java using the `Configuration` and `SessionFactory` classes.

```java
// Step 1: Create a Configuration object
Configuration cfg = new Configuration();

// Step 2: Load the default hibernate.cfg.xml configuration file
cfg.configure(); 

// Step 3: Build the SessionFactory from the Configuration object
SessionFactory factory = cfg.buildSessionFactory();

// Step 4: Another method to create SessionFactory
SessionFactory factory2 = new Configuration().configure().buildSessionFactory();

// Step 5: If the configuration file has a different name than hibernate.cfg.xml or is located outside the default location
Configuration customCfg = new Configuration().configure("custom-hibernate.cfg.xml");
SessionFactory customFactory = customCfg.buildSessionFactory();
```


## 2. Java Configuration
#### Configuration making
```java
package com.pranjal;

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

            properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/my_db"); // Removed extra space
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "09072005");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect"); // its 8 version of mysql
            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.SHOW_SQL, true);

            configuration.setProperties(properties);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry); // Use the service registry to build session factory
        }
        return sessionFactory;
    }
}

// Usage example
SessionFactory factory = HibernateUtil.getSessionFactory();
System.out.println(factory);

```
### Key Concepts Explained

- **SessionFactory**: A factory for Session instances. It is a thread-safe object and is typically created once per application.
- **Configuration**: The main configuration class in Hibernate. It holds the settings and mappings needed for Hibernate to function.
- **ServiceRegistry**: A service registry holds various services that Hibernate uses, including the SessionFactory.
- **Environment**: This class provides constants for common Hibernate properties.


``` java
package com.pranjal;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Configuration cfg = new Configuration();
        cfg.configure(); // Loads hibernate.cfg.xml

        SessionFactory factory = cfg.buildSessionFactory();

        // Alternative method to get SessionFactory
        SessionFactory factory2 = new Configuration().configure().buildSessionFactory();

        // Print the factory object
        System.out.println(factory);
    }
}
```

