
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

1. **Adding or Removing Fields:**
   - **Effect:** When adding, a new column is created (if `hbm2ddl.auto` is set to `update`). When removing, the column remains in the database but is no longer mapped.
   - **Error Possibility:** No errors if `update` is set, but unused columns stay unless manually handled.

2. **Changing the Table Name:**
   - **Effect:** A new table is created, but the old one is not automatically deleted.
   - **Error Possibility:** No errors, but data migration must be handled manually.

3. **Changing Field Types:**
   - **Effect:** Hibernate tries to alter the column type.
   - **Error Possibility:** Errors may occur if existing data can't be cast to the new type.

## Hibernate’s `hbm2ddl.auto` Settings:

- **create/create-drop:** Recreates tables on startup (and drops on close with `create-drop`).
- **update:** Alters schema without dropping data (useful for development).
- **validate:** Only checks schema but doesn’t modify the database.


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

``` java
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
Double price = Double.parseDouble(req.getParameter("price")); // here  parameter is not present or not a valid double, it will throw a NumberFormatException.
Double price2 = (Double) req.getSession().getAttribute("price"); //  if the session does not contain an attribute with that name, or it will hold the Double value if it exists.
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