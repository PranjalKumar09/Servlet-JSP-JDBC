## Hibernate Framework

- **Invented by**: Gavin King in 2001
- **Type**: Java framework that simplifies the development of Java applications to interact with databases
- **Characteristics**:
  - Open source
  - Lightweight
  - Object-Relational Mapping (ORM)
  - Specification of JPA (Java Persistence API) for data persistence
  - Non-intrusive framework (does not require the programmer to extend or implement any specific class or interface)
  - Suitable for building any type of application

## JDBC API

The traditional approach to interact with databases in Java:

```java
class Program {
    public static void main(String[] args) {
        Class.forName("DRIVER_NAME");
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = conn.prepareStatement(sqlQuery);
        ps.executeQuery();
        conn.close();
    }
}
```
**Note**: JDBC is highly dependent on manual coding and handling database interactions.

                      ORM
                   ___________
    Java Class -> | Hibernate | -> Database
                  -------------

### Maven Dependencies

To use Hibernate with a MySQL database, add the following dependencies in your pom.xml:

```xml
<!-- Hibernate Dependency -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.6.1.Final</version>
</dependency>

<!-- MySQL Connector Dependency -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

Also make sure to specify the correct Java version in your pom.xml