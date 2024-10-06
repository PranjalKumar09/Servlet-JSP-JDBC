# Hibernate Query Language (HQL)

Hibernate Query Language (HQL) is an object-oriented query language similar to SQL. However, HQL operates on persistent objects and their properties rather than directly on tables and columns. Hibernate translates HQL queries into conventional SQL queries for execution in the database.

## Example Code

### Java Class

```java
package com.pranjal.action;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import com.pranjal.conn.HibernateUtil;
import com.pranjal.entity.Student;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Persisting students
        Student s1 = new Student("Na1", "Ad1");
        Student s2 = new Student("Na2", "Ad2");
        session.persist(s1);
        session.persist(s2);
        tx.commit();

        // Querying students
        tx = session.beginTransaction();
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        students.forEach(System.out::println);

        // Fetch by ID
        Student studentById = session.get(Student.class, 1);
        System.out.println(studentById);

        // Fetch by address
        Query<Student> queryByAddress = session.createQuery("from Student where address = :address", Student.class);
        queryByAddress.setParameter("address", "Ad1");
        System.out.println(queryByAddress.getSingleResult());

        // Fetch using parameters
        Query<Student> paramQuery = session.createQuery("from Student where address = :address and id = :id", Student.class);
        paramQuery.setParameter("id", 1);
        paramQuery.setParameter("address", "Ad1");
        System.out.println(paramQuery.getSingleResult());

        // Persisting a new student
        Student s3 = new Student("Na3", "Ad3");
        session.persist(s3);

        // Update student using HQL
        MutationQuery updateQuery = session.createMutationQuery("update Student set address = :newAddress where id = :id");
        updateQuery.setParameter("newAddress", "Updated Ad2");
        updateQuery.setParameter("id", 2);
        System.out.println(updateQuery.executeUpdate() + " student(s) updated.");

        // Update student by object
        Student studentToUpdate = session.get(Student.class, 3);
        if (studentToUpdate != null) {
            studentToUpdate.setAddress("Updated Ad3 by object");
            session.merge(studentToUpdate);
            System.out.println("Updated student: " + studentToUpdate.getName());
        }

        // Delete student using HQL
        MutationQuery deleteQuery = session.createMutationQuery("delete from Student where id = :id");
        deleteQuery.setParameter("id", 1);
        System.out.println(deleteQuery.executeUpdate() + " student(s) deleted.");

        // Delete student by object
        Student studentToDelete = session.get(Student.class, 3);
        if (studentToDelete != null) {
            session.remove(studentToDelete);
            System.out.println("Deleted student: " + studentToDelete.getName());
        }

        tx.commit();
        session.close();
    }
}
```
## Maven Dependencies
``` xml
<dependencies>
    <!-- Jakarta EE -->
    <dependency>
        <groupId>org.eclipse</groupId>
        <artifactId>yasson</artifactId>
        <version>3.0.4</version>
    </dependency>
    <dependency>
        <groupId>jakarta.platform</groupId>
        <artifactId>jakarta.jakartaee-api</artifactId>
        <version>10.0.0</version>
        <scope>provided</scope>
    </dependency>

    <!-- Hibernate -->
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.6.1.Final</version>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- JUnit for testing -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Hibernate Configuration Properties
``` java
properties.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
properties.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/my_db");
properties.put("jakarta.persistence.jdbc.user", "root");
properties.put("jakarta.persistence.jdbc.password", "09072005");
properties.put(Environment.HBM2DDL_AUTO, "update");
properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
properties.put(Environment.SHOW_SQL, false);
```

