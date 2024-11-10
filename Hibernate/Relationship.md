# One-to-One Mapping in Hibernate

## Overview
In a **One-to-One** relationship, one record in a table is associated with one and only one record in another table. This mapping is common when you have two entities where one entity logically depends on the other.

### Example:


- **Table 1 (Employee Details)**  
  | emp_id | Emp_name | add_id (FK) |
  |--------|----------|-------------|
  | 1      | John Doe | 101         |

- **Table 2 (Address)**  
  | add_id | address  |
  |--------|----------|
  | 101    | Odisha   |

Here, `add_id` in the **Employee Details** table is a **foreign key** (FK) referencing the `add_id` in the **Address** table.

---

## Code Implementation

### Hibernate Configuration
To map these two tables in Hibernate, we will use the `@OneToOne` annotation to define the relationship.

### Code for One-to-One Mapping

#### Employee Details Entity (`EmpDtls.java`):
```java
package com.relationship;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class EmpDtls {
    @Id
    private int id;
    private String name;

    @OneToOne
    private Address address;

    // Getters and Setters
}
```
#### Address Entity (`Address.java`):
``` java
package com.relationship;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    private int id;
    private String address;

    // Getters and Setters
}
```
#### Main Class (`AppMain.java`):
``` java
package com.relationship;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.project.HibernateUtil;

public class AppMain {
    public static void main(String[] args) {
        // Get the SessionFactory object
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Create Address object
        Address ad = new Address();
        ad.setId(101);
        ad.setAddress("Odisha");

        // Create Employee object
        EmpDtls e = new EmpDtls();
        e.setId(1);
        e.setName("John Doe");
        e.setAddress(ad); // Set the address for this employee

        // Open session and begin transaction
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Persist the objects
        session.persist(ad);
        session.persist(e);

        // Commit the transaction
        tx.commit();

        // Close the session factory
        factory.close();

        System.out.println("Insert Success");
    }
}
```

#### Fetching Data:
``` java
EmpDtls emp = (EmpDtls) session.get(EmpDtls.class, 1);
System.out.println(emp.getName());
System.out.println(emp.getAddress().getAddress());
```

### Making the Relationship Invisible in the Database,
#### By using mapped
``` java
package com.relationship;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
    @Id
    private int id;
    private String address;

    @OneToOne(mappedBy = "address")
    private EmpDtls emp;
    ----
}
```
# One-to-Many Relationship

## Address Entity
```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
    @Id
    private int id;
    private String addressType;
    private String address;

    @ManyToOne
    private EmpDtls empDtls;

    public Address(int id, String addressType, String address) {
        this.id = id;
        this.addressType = addressType;
        this.address = address;
    }

    // Getters and Setters...
}
```

# One to Many relationship

``` java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address {
    public Address(int id, String addressType, String address) {
        this.id = id;
        this.addressType = addressType;
        this.address = address;
    }
    @Id
    private int id;
    private String addressType;
    private String address;


@Entity
public class EmpDtls {
    @Id
    private int id;
    private String name;
    @OneToMany
    private  List<Address> address;
```
 ---
``` java
public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        
        Address add1 = new Address(103, "Permanent", "Delhi2");
        Address add2 = new Address(101, "Permanent", "Delhi2");


        List<Address> list  = new ArrayList<>();
        list.add(add1);
        list.add(add2);



        EmpDtls emp = new EmpDtls();
        emp.setId(1);
        emp.setName("John Doe");
        emp.setAddress(list);

        Session session =  factory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(add1);
        session.persist(add2);
        session.persist(emp);

        System.out.println("Done");
        tx.commit();

        factory.close();
    }
}
```

### Main Application `(App.java)`
```java
public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        
        Address add1 = new Address(103, "Permanent", "Delhi2");
        Address add2 = new Address(101, "Permanent", "Delhi2");

        List<Address> list  = new ArrayList<>();
        list.add(add1);
        list.add(add2);

        EmpDtls emp = new EmpDtls();
        emp.setId(1);
        emp.setName("John Doe");
        emp.setAddress(list);

        Session session =  factory.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(add1);
        session.persist(add2);
        session.persist(emp);

        System.out.println("Done");
        tx.commit();

        factory.close();
    }
}
```
## Generated Tables

When you run this code, Hibernate generates **3 tables**:

- `Address`
- `EmpDtls`
- `EmpDtls_Address` (with columns `EmpDtls_id`, `address_id`)

### Issue: Null Output on Fetching Address

When fetching an `Address` entity and trying to access its associated `EmpDtls`, a **null value** is encountered. This is likely because of the persistence of the older table structure.

### Solution: Drop the Previous Tables

Before running the updated code, ensure to **drop** the previously created tables. Once the previous tables are removed, Hibernate will generate the new table structure correctly.

### Final Note

After adding the `@OneToMany(mappedBy = "empDtls")` annotation to the `EmpDtls` class, only **2 tables** are created:

- `Address`
- `EmpDtls`

The join table `EmpDtls_Address` will no longer be generated.

With a **bidirectional relationship** between two classes:

- **Without `mappedBy`**: An additional join table is created to manage the relationship, as neither side is designated as the owning side.
  
- **With `mappedBy`**: Only a single foreign key column is created in the table of the owning entity (the one without `mappedBy`), avoiding the need for an extra join table.

- **`mappedBy` means** -> It which column will manage the foregin key
___
___
# Many to Many
In a Many-to-Many relationship, an employee can have multiple addresses, and each address can be associated with multiple employees. This requires the use of a join table in the database.

## **Example** Generated Tables

Initially, Hibernate generates **4 tables**:
1. `Emp`
2. `Address`
3. `Emp_Address` (join table)
4. `Address_Emp` (join table)

However, after adding the `@ManyToMany(mappedBy="emp")` annotation in the `Emp` entity, Hibernate reduces the number of tables to **3 tables**:
1. `Emp`
2. `Address`
3. `Emp_Address` (join table with columns `Emp_id`, `Address_id`)

## Emp Entity
```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;

@Entity
public class Emp {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy="emp")
    private List<Address> addresses;

    // Getters and Setters...
}
```
## Address Entity
``` java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;

@Entity
public class Address {
    @Id
    private int id;
    private String addressName;

    @ManyToMany
    private List<Emp> emp;

    // Getters and Setters...
}
```
| **Entity**           | **Details**                                                                                                                                           |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Emp Entity**       | Each employee can have multiple addresses, and the relationship is managed by the `@ManyToMany(mappedBy="emp")` annotation, which points to the `emp` field in the `Address` class. |
| **Address Entity**   | Each address can be associated with multiple employees using the `@ManyToMany` annotation, which creates a join table for the association.              |

By using the `@ManyToMany(mappedBy="emp")` annotation, only one join table is created (`Emp_Address`), avoiding the creation of an extra table, thus reducing the total number of tables to **3**.

The use of `@ManyToMany(mappedBy="emp")` makes the relationship bidirectional. One side (the `Emp` class) is responsible for managing the relationship, and the `Address` class simply references it.

