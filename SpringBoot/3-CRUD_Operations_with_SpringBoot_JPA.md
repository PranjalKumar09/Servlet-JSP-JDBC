### Dependencies

- **Spring Boot Starter Web**  
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```
  Provides basic web functionality for REST APIs and web applications.

- **Tomcat Jasper**  
  ```xml
  <dependency>
      <groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-jasper</artifactId>
  </dependency>
  ```
  Enables JSP support within embedded Tomcat.

- **Spring Boot Starter Test**  
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
  </dependency>
  ```
  Adds testing support with JUnit, Mockito, and Spring Test.

#### Additional Setup
In IntelliJ, configure `Project Structure > Facets` to add paths and Tomcat settings for JSP support.

---

### Application Properties

Configuration settings for `application.properties`:
```properties
spring.application.name=project02_jpa
spring.datasource.url=jdbc:mysql://localhost:3306/my_db
spring.datasource.username=root
spring.datasource.password=09072005
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```
- Configures database connection and Hibernate settings for the application.

---

### Dependency Injection and Spring Annotations

- **Dependency Injection (DI)**: Uses `@Autowired` for injecting beans without direct object instantiation.
- **Spring Stereotype Annotations**:
  - `@Service`, `@Component`, `@Repository`: Serve similar functions in DI but indicate specific roles (e.g., `@Repository` for data access, `@Service` for business logic).

### Spring Data JPA

- **Overview**: Spring Data JPA abstracts JPA to reduce boilerplate in data access. It provides a higher-level API for ORM and integrates JPA with the Spring framework.
- **Hierarchy**: `Spring Data JPA -> JPA -> Hibernate -> JDBC -> Database`
- **JPA**: Maps Java objects to relational tables using Object-Relational Mapping (ORM) to handle CRUD operations on databases with minimal code.

### Basic CRUD Operations

Using `JpaRepository<Student, Integer>`, CRUD methods are implemented as follows:

1. **Create**: `studentRepo.save(student);`
2. **Read All**: `studentRepo.findAll();`
3. **Read By ID**: `studentRepo.findById(id).orElse(null);`
4. **Update**: Modify fields, then `studentRepo.save(student);`
5. **Delete**: `studentRepo.delete(student);`

### Main Application Code Example

The main class initializes the application context and performs basic CRUD operations on `Student`:
```java
@SpringBootApplication
public class Project02JpaApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Project02JpaApplication.class, args);
        StudentRepo studentRepo = ctx.getBean(StudentRepo.class);

        // CRUD operations
        Student st = new Student("India", "Shyam2");
        studentRepo.save(st);  // Create
        List<Student> students = (List<Student>) studentRepo.findAll();  // Read All
        students.forEach(System.out::println);

        // Read By ID
        Student student = studentRepo.findById(st.getId()).orElse(null);
        if (student != null) {
            student.setName("Updated Name");  // Update
            studentRepo.save(student);
            System.out.println("Updated Student: " + student);
            studentRepo.delete(student);  // Delete
        }
    }
}
```

---

### Repository Interfaces

Define repository interfaces by extending `JpaRepository` for CRUD and pagination:
```java
public interface StudentRepo extends JpaRepository<Student, Integer> {
}
```
Using `JpaRepository` combines the features of `CrudRepository`, `PagingAndSortingRepository`, and `QueryByExampleExecutor`, enhancing flexibility with custom query methods and pagination support.

Pagnination is feature that when we search in Google or Amazon we see there below  like pages given.

### Note on Constructors

Ensure a default constructor exists in entity classes (e.g., `Student`) to avoid runtime errors with JPA.