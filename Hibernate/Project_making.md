# Hibernate Configuration and Annotations

## Step 1: Annotate Entity Class with Hibernate Annotations
Hibernate requires us to annotate the Java class with specific annotations to map it to a database table. For example:

```java

@Entity // Annotation
public class Student {
    @Id
    private int id;
    private String name;
    private String email;
    private String address;

    // Getters and Setters
}
```
- `@Entity:` Marks this class as a Hibernate entity.
- `@Id:` Marks the id field as the primary key of the table.


## Step 2: Add the Annotated Class to Configuration
``` java
configuration.addAnnotatedClass(Student.class);
```
## Step 3: Insert a Record into the Table
``` java
SessionFactory factory = HibernateUtil.getSessionFactory();
Session session = factory.openSession();

Student st = new Student();
st.setId(1);
st.setName("John Doe");
st.setEmail("johndoe@example.com");
st.setAddress("123 Main St");

Transaction tx = session.beginTransaction();
session.persist(st);  // Persisting the entity

tx.commit();
session.close();
```
- `session.persist()`: This is used to save the Student object into the database. It is preferred over save() because it adheres to the JPA specification.
- `tx.commit()`: This commits the transaction, making the changes permanent in the database.

# Commonly Used Hibernate Annotations

- `@Entity:` Marks the class as a Hibernate entity.
- `@Table:` Specifies the table name in the database.
- `@Id:` Defines the primary key.
- `@GeneratedValue:` Specifies how the primary key is generated.
- `@Column:` Maps the class fields to columns in the database.
- `@Transient:` Marks a field to be ignored by Hibernate.
- `@Temporal:` Specifies the date/time precision of a field.
- `@Lob:` Maps large objects (e.g., BLOB or CLOB).
- `@OneToOne,` @ManyToMany: Used to define relationships between entities.
- `@JoinColumn:` Specifies the column used for joining entities.


#### Example of an Employee Entity Class with Annotations
``` java
package com.pranjal;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "emp_dtls")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String empName;

    private Double salary;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joiningDate;

    private String email;

    private boolean status;

    @Transient
    private String token;

    // Getters and Setters

    public Employee() {
        super();
    }

    public Employee(int id, String empName, Double salary, Date joiningDate, String email, boolean status, String token) {
        super();
        this.id = id;
        this.empName = empName;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.email = email;
        this.status = status;
        this.token = token;
    }

    // Getters and Setters
}
```

## What Happens When You Change the Class File?

### Adding New Fields
- **Scenario:** Adding a new field like `String department`.
- **Effect:** If `hbm2ddl.auto` is set to `update`, Hibernate adds the new column. If set to `create` or `create-drop`, it recreates the table.
- **Error Possibility:** No error if `update` is set correctly.

### Removing Fields
- **Scenario:** Removing a field like `String token`.
- **Effect:** The column stays in the database but isn’t mapped.
- **Error Possibility:** No error, but the column remains unless manually removed.

### Changing the Table Name
- **Scenario:** Using `@Table(name = "new_table_name")` to change the table name.
- **Effect:** A new table is created, but the old one remains unless deleted manually.
- **Error Possibility:** No error, but you’ll need to migrate data manually.

### Changing the Field Type
- **Scenario:** Changing `Double salary` to `Integer salary`.
- **Effect:** Hibernate attempts to alter the column. 
- **Error Possibility:** A database error may occur if existing data can't be cast to the new type.

## Hibernate’s `hbm2ddl.auto` Settings

- **create:** Drops and recreates the table on each start.
- **create-drop:** Same as `create`, but also drops the table on close.
- **update:** Updates the schema without dropping data (recommended for development).
- **validate:** Validates schema matches entities, but doesn’t alter the database.

## Note

- Use `persist()` for better JPA compliance.
- Always add annotated classes using `configuration.addAnnotatedClass(ClassName.class)`.
- Be cautious with class changes as they can affect the database.
- Set `hbm2ddl.auto` appropriately (use `update` for development).







### Dao Example
``` java

public boolean saveEmp(Emp emp) {
    boolean f = false;

    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    try {
        session.persist(emp); // Persist the employee object
        
        tx.commit(); // Commit the transaction
        f = true;
    } catch (Exception e) {
        if (tx != null) 
            tx.rollback(); // Rollback in case of an error
        
    } finally {
        session.close(); // Close the session in the finally block
    }

    return f; // Return the success status
}
```


### Checking if DB is connected in JSP

``` jsp
SessionFactory factory = HibernateUtil.getSessionFactory();
out.print(factory);  // Print factory to verify connection
```

### Directory Structure

- **com.dao** - For Data Access Objects (DAO)
- **com.db** - For database configuration (e.g., HibernateUtil)
- **com.entity** - For entity classes (e.g., User, Expense)
- **com.servlet** - For servlets that handle requests

### Converting Data Types in JSP to Servlet
#### Converting String to Double:
``` java
Double price = Double.parseDouble(req.getParameter("price"));
```
#### Fetching User from Session:
``` java
User user = (User) req.getSession().getAttribute("user");
```
### URL Rewriting for Editing Data
- #### Fetching ID from URL parameter:
``` java
int id = Integer.parseInt(request.getParameter("id"));
```
### Passing Data to JSP using EL (Expression Language)
To use `${expense.title}`, set the attribute in the request:
``` java
request.setAttribute("expense", expense);
```

### Querying with Hibernate and Managing Sessions
- **Querying Expenses for a User:**
``` java
session = sessionFactory.openSession();
Query<Expense> query = session.createQuery("from Expense where user = :user");
query.setParameter("user", user);
expenses = query.getResultList();
session.close();  // Always close the session to avoid errors
```
- **Important**: Always remember to close the session after the query to avoid memory leaks and other errors.