# FetchType in Hibernate

In Hibernate, `FetchType` is used to define the fetching strategy for child entities in a relationship. The two primary fetch types are `LAZY` and `EAGER`.

## FetchType.LAZY

- **Definition**: Fetches child entities lazily, meaning it fetches only a proxy (created by CGLIB or any other utility) of the child entities at the time of fetching the parent entity. The actual fetching of the child entities occurs only when one of the child entity's properties is accessed.

- **Behavior**: The child entities are not loaded until explicitly accessed in the code.

- **Advantages**:
  - **Improved Performance**: By delaying the loading of child entities until they are actually needed, `LAZY` initialization avoids unnecessary database calls.
  - **Memory Efficiency**: It reduces memory consumption since child entities are loaded only when required.
  
- **Disadvantages**:
  - **LazyInitializationException**: If a lazy entity is accessed outside of the session (e.g., in a detached state), Hibernate throws this exception because the entity is no longer connected to the session.
  
- **Usage Example**:
```java
  Emp emp = (Emp) session.get(Emp.class, 101);
  System.out.println("Employee Name: " + emp.getName());
  System.out.println(emp.getAddresses().size());  // Child entities loaded here
```

- **Annotation**:
``` java
@ManyToMany(mappedBy="emp", fetch=FetchType.LAZY)
```

- **Output Hibernate SQL Queries:**:
``` sql
Hibernate: select e1_0.id, e1_0.Name from Emp e1_0 where e1_0.id=?
Employee Name: Rakesh
Hibernate: select a1_0.emp_id, a1_1.id, a1_1.addressName from Address_Emp a1_0 join Address a1_1 on a1_1.id=a1_0.addresses_id where a1_0.emp_id=?
```

## FetchType.EAGER

- **Definition**: Fetches child entities eagerly, meaning the child entities are loaded along with the parent entity in a single database call.

- **Behavior**: The child entities are loaded immediately when the parent entity is loaded, even if they are never accessed.

- **Advantages**:
  - **Convenience**: All the required data is loaded at once, so there’s no need to re-open a session or manage detached entities.
  - **No LazyInitializationException**: Since the entities are loaded eagerly, you don't face the common lazy initialization issues.

- **Disadvantages**:
  - **Performance Overhead**: If child entities are not needed, eager loading results in extra and unnecessary database calls, increasing the time and memory consumption.
  
- **Usage Example**:
```java
  Emp emp = (Emp) session.get(Emp.class, 101);
  System.out.println("Employee Name: " + emp.getName());
  System.out.println(emp.getAddresses().size());  // Child entities already loaded
```
- **Annotation**:
``` java
@ManyToMany(mappedBy="emp", fetch=FetchType.EAGER)
```
- **Output Hibernate SQL Queries:**:
``` sql
Hibernate: select e1_0.id, e1_0.Name, a1_0.emp_id, a1_1.id, a1_1.addressName 
           from Emp e1_0 
           left join Address_Emp a1_0 on e1_0.id=a1_0.emp_id 
           left join Address a1_1 on a1_1.id=a1_0.addresses_id where e1_0.id=?
Employee Name: Rakesh
```
## When to Use Each

### Use `FetchType.LAZY`:

- **When**:
  - The child entities may not always be needed.
  - You want to optimize performance by loading child entities only when required.

- **Scenarios**:
  - In scenarios where accessing child entities is rare.
  - For large datasets where you only need parent data in most cases.

### Use `FetchType.EAGER`:

- **When**:
  - Child entities are frequently accessed alongside the parent.
  - You want all related data loaded at once to avoid multiple database calls.

- **Scenarios**:
  - Small datasets where performance overhead is negligible.
  - In CRUD operations where both parent and child data are always required.

## Key Differences

| **Aspect**              | **FetchType.LAZY**                               | **FetchType.EAGER**                          |
|-------------------------|--------------------------------------------------|----------------------------------------------|
| **Entity Loading**       | Child entities loaded on access (lazily)         | Child entities loaded immediately with parent|
| **Performance**          | Better performance if child data is not needed   | Potential performance overhead               |
| **Memory Usage**         | Lower memory usage, as fewer entities are loaded | Higher memory usage, as all entities are loaded|
| **Initialization Error** | Can throw `LazyInitializationException` if accessed outside session | No such issue |


- `Lazy` is default type