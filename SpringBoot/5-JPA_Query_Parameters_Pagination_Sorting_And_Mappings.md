### 1. **Named Parameters**
- **Syntax**: `:parameterName` in the query; use `@Param("parameterName")` in the method.
- **Example**:
  ```java
  @Query("SELECT u FROM Student u WHERE u.name = :name")
  List<Student> findByName(@Param("name") String name);
  ```
- **Benefits**:
  - **Readable**: Labels each parameter clearly.
  - **Flexible**: Allows parameter reordering without affecting the query.
- **Use Case**: Ideal for queries with multiple parameters, improving readability and reducing errors.

---

### 2. **Positional Parameters**
- **Syntax**: `?1`, `?2`, etc., where `?1` refers to the first parameter, `?2` to the second, and so on.
- **Example**:
  ```java
  @Query("SELECT u FROM Student u WHERE u.name = ?1")
  List<Student> findByName(String name);
  ```
- **Benefits**:
  - **Concise**: Efficient for single-parameter queries.
  - **Performance**: Slight performance advantage in some cases.
- **Use Case**: Best for single-parameter or simple queries, but can become error-prone with more parameters due to positional dependency.

---

### Pagination & Sorting
- **Pagination**:
  ```java
  Pageable pageable = PageRequest.of(0, 5); // Page 0, size 5
  Page<Student> students = studentRepo.findAll(pageable);
  ```
  - **Page Elements**:
    - `students.getTotalElements()` — Returns the total number of elements.
    - `students.getNumber()` — Returns the current page number.
- **Sorting**:
  ```java
  Sort sort = Sort.by("name").ascending();
  Pageable pageable = PageRequest.of(0, 5, sort);
  ```

---

### JPA Mappings
- **Relationships**:
  - `@OneToOne`
  - `@OneToMany`
  - `@ManyToOne`
  - `@ManyToMany`