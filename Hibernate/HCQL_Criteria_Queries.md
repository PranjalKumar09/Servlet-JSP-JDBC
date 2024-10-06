# Hibernate Criteria Queries

Hibernate provides alternative methods for manipulating objects and data in relational databases (RDBMS). One of these methods is the **Criteria API**, which allows you to build criteria query objects programmatically. This enables you to apply filtration rules and logical conditions.

## Creating Criteria Queries

The `Session` interface in Hibernate provides the `createCriteria()` method, which can be used to create a **Criteria** object that returns instances of the persistence object class when executing a criteria query.

### Student Entity Class

```java
package com.pranjal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private Integer Salary; // Use Integer to allow null values

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", address=" + address + ", Salary=" + Salary + "]";
    }

    // Getters and Setters
    // ...
}
```

### Criteria Queries Example
``` java
package com.pranjal.action;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.pranjal.conn.HibernateUtil;
import com.pranjal.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CriteriaObject {

    private static void print(List<Student> list) {
        for (Student student : list) 
            System.out.println(student);
        System.out.println("==".repeat(20));
    }

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        // Fetch all students using HQL
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        print(students);

        // Using Criteria API
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root);

        // Fetching students with various criteria
        // 1. Salary > 31,000
        Predicate salaryPredicate = criteriaBuilder.gt(root.get("Salary"), 31000);
        criteriaQuery.select(root).where(salaryPredicate);
        print(session.createQuery(criteriaQuery).getResultList());

        // 2. Salary < 28,000
        criteriaQuery = criteriaBuilder.createQuery(Student.class);
        root = criteriaQuery.from(Student.class);
        Predicate salPredicate2 = criteriaBuilder.lt(root.get("Salary"), 28000);
        criteriaQuery.select(root).where(salPredicate2);
        print(session.createQuery(criteriaQuery).getResultList());

        // 3. Names starting with "E"
        criteriaQuery = criteriaBuilder.createQuery(Student.class);
        root = criteriaQuery.from(Student.class);
        Predicate nameStartsWithE = criteriaBuilder.like(root.get("name"), "E%");
        criteriaQuery.select(root).where(nameStartsWithE);
        print(session.createQuery(criteriaQuery).getResultList());

        // 4. Salary between 25,000 and 30,000
        criteriaQuery = criteriaBuilder.createQuery(Student.class);
        root = criteriaQuery.from(Student.class);
        Predicate salaryBetween = criteriaBuilder.between(root.get("Salary"), 25000, 30000);
        criteriaQuery.select(root).where(salaryBetween);
        print(session.createQuery(criteriaQuery).getResultList());

        // 5. Salary is null
        criteriaQuery = criteriaBuilder.createQuery(Student.class);
        root = criteriaQuery.from(Student.class);
        Predicate salaryIsNull = criteriaBuilder.isNull(root.get("Salary"));
        criteriaQuery.select(root).where(salaryIsNull);
        print(session.createQuery(criteriaQuery).getResultList());

        // 6. Salary is not null
        criteriaQuery = criteriaBuilder.createQuery(Student.class);
        root = criteriaQuery.from(Student.class);
        Predicate salaryIsNotNull = criteriaBuilder.isNotNull(root.get("Salary"));
        criteriaQuery.select(root).where(salaryIsNotNull);
        print(session.createQuery(criteriaQuery).getResultList());

        // 7. Address equals "135 Pine Ln"
        criteriaQuery = criteriaBuilder.createQuery(Student.class);
        root = criteriaQuery.from(Student.class);
        Predicate addressEquals = criteriaBuilder.equal(root.get("address"), "135 Pine Ln");
        criteriaQuery.select(root).where(addressEquals);
        print(session.createQuery(criteriaQuery).getResultList());

        session.close(); // Close the session
        factory.close(); // Close the factory
    }
}
```