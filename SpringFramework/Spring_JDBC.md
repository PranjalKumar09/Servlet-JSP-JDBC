
### Introduction to Spring JdbcTemplate

  - **Spring JdbcTemplate** is a powerful framework for connecting to databases and executing SQL queries.
  - It leverages the JDBC API, addressing many of its common challenges.
### Problems with JDBC API

  - **Boilerplate Code**: Requires extensive code for connection management, statement creation, and resource cleanup (closing result sets and connections).
  - **Exception Handling**: Necessitates repetitive exception handling logic for database operations.
  - **Repetition**: Developers often write similar code for different queries, leading to increased maintenance overhead.
### Advantages of Spring JdbcTemplate

  - **Simplifies Database Interaction**: Reduces boilerplate code and handles resource management automatically.
  - **Direct Query Execution**: Allows developers to write and execute queries with minimal effort.
  - **Comprehensive API**: Provides essential methods for performing database operations efficiently.
### Configuration
**XML-based Configuration:**
``` xml
<bean name="jds" class="org.springframework.jdbc.datasource.DriverManagerDataSource" 
      p:driverClassName="com.mysql.cj.jdbc.Driver" 
      p:username="root" 
      p:password="09072005" 
      p:url="jdbc:mysql://localhost:3306/my_db" /> 

<bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate" 
      p:dataSource-ref="jds" />
```
**Java-based Configuration:**
```java
package jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "jdbc")
public class JavaConfig {
    @Bean
    public DriverManagerDataSource jds() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/my_db");
        dataSource.setUsername("root");
        dataSource.setPassword("09072005");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(jds());
        return jdbcTemplate;
    }
}
```

### Sample Code

```java
package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");

        System.out.println(jdbcTemplate);
        System.out.println(jdbcTemplate.getDataSource());
        System.out.println(jdbcTemplate.getDataSource().getConnection());
    }
}
```

### Output

  - Displays instances of `JdbcTemplate`, `DataSource`, and `Connection`.
 