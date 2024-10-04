# Hibernate 6.0 CRUD Operations for Student Entity

### 1. Configuration
``` java
properties.put(Environment.HBM2DDL_AUTO, "update");
```
- **Purpose:** This will update the table if the schema changes without dropping the existing table or data.
- **Note:** It's useful when you're modifying your entity classes and want the changes reflected in the schema. It can also be created like "create" , it will create new rather

### 2. Basic CRUD Operations in Hibernate
### Package Structure:
``` java
package com.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
```
### Main Class:
``` java
public class App {
    public static void main(String[] args) {
        // Step 1: Initialize SessionFactory and open session
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        // Create, Read, Update, Delete operations go here

        session.close();
        sf.close();
    }
}
```
### 3. Create Operation
Inserting new records into the `Student` table
``` java
Student st = new Student();
st.setName("First");
st.setAddress("India");
st.setCollageName("ABC");
st.setEmail("abc@gmail.com");

Student st2 = new Student();
st2.setName("Second");
st2.setAddress("USA");
st2.setCollageName("DFG");
st2.setEmail("dfg@gmail.com");

Transaction tx = session.beginTransaction();

session.persist(st);  // Persisting student data
session.persist(st2);

tx.commit();  // Committing the transaction
System.out.println("Students saved successfully");
```

### 4. Read Operation
Retrieving data from the `Student` table:
#### Fetching All Records:
``` java
List<Student> list = (List<Student>) session.createQuery("from Student", Student.class).list();
list.forEach(e -> System.out.println(e));
```
#### Fetching by ID:
``` java
Student st = session.get(Student.class, 1);
System.out.println(st);
```
### 5. Update Operation
``` java
Student st = session.get(Student.class, 1);  // Fetch the entity to update
st.setName("Update Name");
st.setAddress("India Updated");
st.setEmail("pranjalkumar@gmail.com");
st.setCollageName("UK University");

Transaction tx = session.beginTransaction();
session.merge(st);  // Using merge() for updates in Hibernate 6.0
tx.commit();
System.out.println("Student updated successfully");
```
**Note:** Use `merge()` instead of `saveOrUpdate()` in Hibernate 6.0.

### 6. Delete Operation
``` java
Student st = session.get(Student.class, 1);  // Fetch the entity to delete

Transaction tx = session.beginTransaction();
session.remove(st);  // Use remove() instead of delete() in Hibernate 6.0
tx.commit();

System.out.println("Data deleted successfully");
```
**Note:** Use `remove()` instead of **delete(**) in Hibernate 6.0.


- Use `merge()` for updating records.
- Use `remove()` for deleting records.
- Use `persist()` for creating new records.


