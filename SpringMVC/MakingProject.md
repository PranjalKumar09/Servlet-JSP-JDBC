### MVC Layered Architecture in Spring (Controller, Service, DAO, DB)

#### Why Avoid Putting Everything in the Controller?
In a **scalable application**, it is essential to separate concerns into different layers:
- **Controller**: Handles web requests, prepares the model, and returns the appropriate view.
- **Service**: Contains business logic.
- **DAO (Data Access Object)**: Responsible for interacting with the database.
- **Database**: Stores the actual data.

Putting everything in a single controller leads to tightly coupled code, making it harder to maintain, test, and scale as the project grows. Instead, we split the responsibilities into the following layers for modularity and reusability.

### MVC Layer Breakdown

#### 1. **Controller**: 
Handles HTTP requests and returns views. It coordinates between the service and view layers.

```java
@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home() {
        return "home";  // Returns home.jsp
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(path="/createUser")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.insertUser(user);  // Calls Service layer to handle user registration
        model.addAttribute("user", user);
        return "success";  // Forward to success.jsp with the registered user data
    }
}
```

#### 2. **Service**: 
Contains business logic. It acts as a mediator between the controller and DAO layers.

```java
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;

    @Override
    public int insertUser(User user) {
        return userDao.insert(user);  // Calls the DAO layer to insert data into the DB
    }
}
```

#### 3. **DAO (Data Access Object)**:
Responsible for interacting with the database. It contains methods to perform CRUD operations.

```java
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(User user) {
        String sql = "INSERT INTO User(fullName, age, password, email) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getFullName(), user.getAge(), user.getPassword(), user.getEmail());
    }
}
```

#### 4. **Database Configuration**:
The database is configured in the `applicationContext.xml` using `JdbcTemplate` for managing database operations and a `DriverManagerDataSource` for the connection.

```xml
<bean name="jds" class="org.springframework.jdbc.datasource.DriverManagerDataSource"
      p:driverClassName="com.mysql.cj.jdbc.Driver"
      p:username="root"
      p:password="09072005"
      p:url="jdbc:mysql://localhost:3306/my_db" />

<bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate"
      p:dataSource-ref="jds" />
```

### Explanation of Annotations


#### 1. **@Service**:
Indicates that a class is a service, containing business logic. Spring creates a singleton bean for it and injects it wherever required.

```java
@Service
public class UserServiceImpl implements UserService {
    // Contains business logic
}
```

#### 2. **@Repository**:
Indicates that the class interacts with the database. It’s a specialization of `@Component` that provides additional database-specific exceptions translation (e.g., converting SQLExceptions into Spring’s DataAccessException).

```java
@Repository
public class UserDao {
    // Database interaction
}
```

### Key Concepts

#### Forward vs Redirect:
- **Forward (`return "register"`)**: Sends the request back to the same page or controller without changing the URL. Any data submitted in the form remains.
  
- **Redirect (`return "redirect:/register"`)**: Resends the request to a different endpoint, clearing the previous form data. This solves issues like form re-submission on page reload.

#### User Model Example:
The user model represents the entity for which data is processed. It is used across layers (Controller, Service, and DAO).

```java
public class User {
    private int id;
    private String fullName;
    private int age;
    private String password;
    private String email;

    // Getters and Setters
}
```

### XML Configuration (Spring_servlet.xml)

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
            http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- Scanning the components in specified packages -->
    <context:component-scan base-package="com.controller, com.service, com.dao" />

    <!-- View Resolver for JSP pages -->
    <bean name="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver" 
          p:prefix="/WEB-INF/view/" p:suffix=".jsp" />

    <!-- DataSource and JdbcTemplate -->
    <bean name="jds" class="org.springframework.jdbc.datasource.DriverManagerDataSource"
          p:driverClassName="com.mysql.cj.jdbc.Driver"
          p:username="root"
          p:password="your_password"
          p:url="jdbc:mysql://localhost:3306/my_db" />

    <bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate"
          p:dataSource-ref="jds" />

</beans>
```

### Dispatcher Servlet (web.xml)
```xml
<servlet>
    <servlet-name>spring</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>spring</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```


### URL Routing in the Application

1. **Editing an Employee**:
   - If you navigate to:  
     `http://localhost:8080/EmployeeManagement_Project5_war/edit_emp/152`,  
     this will allow you to edit the employee with ID `152`.
   
2. **Updating an Employee**:
   - To perform an update, you need to go to:  
     `http://localhost:8080/EmployeeManagement_Project5_war/updateEmp`.
   - Use the following form action to correctly point to the update endpoint:  
     ```html
     <form action="${pageContext.request.contextPath}/updateEmp">
     ```

### Notes on Redirection and Form Submission
- Avoid unnecessary redirects after updates to improve user experience. If you can show a success message on the same page, do so instead of redirecting.

### Database Query for Notes
To fetch notes for a specific user, use the following query, When in hibernate we have User user as foreign key:
```java
Query<Notes> query = session.createQuery("from Notes where user.id = :userId", Notes.class);
query.setParameter("userId", userId);
```

### Action Links for Notes
When displaying action buttons for each note, use:
```html
<div>
    <a href="edit_notes?id=${note.id}" >Edit</a>
    <a href="deleteNotes?id=${note.id}">Delete</a>
</div>
```

### Employee Edit and Update Controller Methods
1. **Edit Employee**:
   ```java
   @RequestMapping("/edit_emp/{id}")
   public String edit_emp(@PathVariable("id") int id, Model model) {
        // code
       return "edit_emp";
   }
   ```

2. **Update Employee**:
   ```java
   @RequestMapping(path = "/updateEmp", method = RequestMethod.POST)
   public String updateEmp(HttpSession session, @ModelAttribute Emp emp) {
        // code
       return "redirect:/home"; // Consider using a message display instead of a redirect
   }
   ```

3. **Delete Employee**:
   ```java
   @RequestMapping("/deleteEmp/{id}")
   public String deleteEmp(HttpSession session, @PathVariable("id") int id) {
       // code
       return "redirect:/home"; // Consider showing a message instead of a redirect
   }
   ```
