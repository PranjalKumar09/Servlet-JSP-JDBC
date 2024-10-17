# Difference Between `session.getReference` and `get`

In Hibernate, both `session.getReference()` and `session.get()` are used to retrieve an entity, but they differ in how and when they load the data.

## `session.getReference()`

- **Lazy Loading:** When using `session.getReference()`, Hibernate doesn’t immediately hit the database to fetch the entity. It returns a proxy (a reference to the entity) without initializing it. The actual database call is made only when you access the entity's properties.
- **Use Case:** Ideal for scenarios where you just need a reference to the entity but don't immediately need to access its fields or properties.
- **Example:**

    ```java
    Employee emp = session.getReference(Employee.class, 1);
    ```
  
  In this case, Hibernate will not fetch the `Employee` entity until its attributes are accessed.

- **Note:** If the entity is not found in the database, an exception (`EntityNotFoundException`) will be thrown when the proxy is accessed.

## `session.get()`

- **Eager Loading:** When using `session.get()`, Hibernate immediately queries the database to retrieve the full entity, including all its properties.
- **Use Case:** Suitable when you need the complete entity and its data right away.
- **Efficiency:** If the object is requested multiple times, `session.get()` ensures that it returns the same entity, as it caches the entity in the session.
- **Example:**

    ```java
    Employee emp = session.get(Employee.class, 1);
    ```

  Here, Hibernate will immediately query the database to retrieve the `Employee` object with ID `1`.

## Summary of Differences

| Feature                      | `getReference()`                           | `get()`                              |
|------------------------------|--------------------------------------------|--------------------------------------|
| Loading                       | Lazy (Proxy returned)                     | Eager (Immediate DB hit)             |
| Database Access               | Delayed until entity properties are accessed | Immediate                            |
| Use Case                      | When only a reference is needed initially | When complete entity data is required |
| Exception if entity not found | At access (throws `EntityNotFoundException`) | Returns `null`                       |



### Handling Binary Data
Binary data such as images, we can annotate the field with `@Lob`

```java
@Lob
private byte[] image;
```
_