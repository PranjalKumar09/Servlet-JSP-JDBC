

### **Create SecurityConfig class**
1) Create BcryptPasswordEncoder Bean
2) Create DaoProvide Bean -> userDeatils obj, Password encoder
3) SecurityFilter chain configuration


### Overview of Key Classes and Configuration Steps
This Spring Security configuration involves creating a custom user implementation, user detail service, authentication provider, and security configuration, following best practices in Spring Boot.

---

### 1. **CustomUser Class** - Implements `UserDetails`

The `CustomUser` class wraps the `Employee` entity and provides required information for Spring Security. This class implements `UserDetails`, which is Spring’s interface for storing user information.

```java
public class CustomUser implements UserDetails {
    private Employee employee;

    public CustomUser(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security requires roles to be in the format "ROLE_XYZ"
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(employee.getRole());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getEmail();
    }

    // Additional methods required by UserDetails can be implemented here.
}
```
Usually here we set all to true

**Explanation of Methods:**
- `getAuthorities()`: Returns the roles associated with the user. Spring expects roles to be prefixed with `"ROLE_"`. This setup assumes roles in the database are already formatted like `ROLE_USER`.
- `getPassword()` and `getUsername()`: Return the `password` and `email` fields from the `Employee` entity.
  
### 2. **CustomUserDetailsService Class** - Implements `UserDetailsService`

The `CustomUserDetailsService` class handles user retrieval from the database by email. It implements `UserDetailsService`, which Spring Security uses to load user-specific data during authentication.

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmpRepo empRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = empRepo.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUser(employee); // Wraps Employee in CustomUser for authentication
    }
}
```

**Explanation of Methods:**
- `loadUserByUsername(String email)`: Looks up the user by email in the database using `empRepo`. If no user is found, it throws `UsernameNotFoundException`.

### 3. **SecurityConfig Class** - Configures Security Beans and Filters

The `SecurityConfig` class configures the overall security for the application. It defines beans for password encoding, DAO-based authentication, and filter chains.

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService customUserDetailsService) {
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Using BCryptPasswordEncoder for hashing passwords
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Require authentication for all requests
                )
                .formLogin(withDefaults()); // Enable default form-based login
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService); // Set CustomUserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Set BCryptPasswordEncoder
        return authProvider;
    }
}
```

**Explanation of Configuration:**
- `userDetailsService()`: Defines a bean for `UserDetailsService` and injects `CustomUserDetailsService`.
- `passwordEncoder()`: Configures a `BCryptPasswordEncoder` bean to hash passwords. BCrypt is a secure algorithm for hashing passwords.
- `filterChain(HttpSecurity http)`: Configures security filters:
  - Disables CSRF protection for simplicity (not recommended for production).
  - Requires authentication for all requests.
  - Enables form-based login using Spring's default login page.
- `authenticationProvider()`: Configures `DaoAuthenticationProvider` with `CustomUserDetailsService` and `BCryptPasswordEncoder` for authentication.

---

### 4. **HomeController Class** - Secures Endpoints Based on Roles

The `HomeController` defines endpoints with method-level security, allowing access based on roles.

```java
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/about")
    @PreAuthorize("hasAuthority('ROLE_USER')") // Accessible only by ROLE_USER
    public String about() {
        return "about";
    }

    @RequestMapping("/home")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Accessible only by ROLE_ADMIN
    public String home() {
        return "home";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "profile"; // Accessible by any authenticated user
    }
}
```

**Explanation of Annotations:**
- `@PreAuthorize("hasAuthority('ROLE_USER')")`: Restricts access to users with `ROLE_USER` authority for the `about` endpoint.
- `@PreAuthorize("hasAuthority('ROLE_ADMIN')")`: Restricts access to users with `ROLE_ADMIN` authority for the `home` endpoint.

---

### 5. **Employee Entity** - Maps Employee Table for User Data

The `Employee` class represents the data structure in the database, used by `EmpRepo` for data retrieval.

```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String department;
    private String role; // Should be in the format "ROLE_USER" or "ROLE_ADMIN"

    // Getters and setters for each field
}
```

### 6. **EmpRepo Interface** - Data Access Layer

The `EmpRepo` interface provides methods for accessing `Employee` records from the database. It includes a method to fetch employees by email, a key function for login.

```java
@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
}
```

### 7. **Project03SpringSecurityApplication Class** - Application Entry Point

```java
@SpringBootApplication
public class Project03SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project03SpringSecurityApplication.class, args);
        // Encrypts and prints "1234" to simulate storing it securely in the database
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }
}
```

In `main()`, the example hashes "1234" with `BCryptPasswordEncoder`. This hash can be stored in the database for the user password and allows verification on login.

### Summary and Security Flow

1. **User Authentication**: When a user logs in, `CustomUserDetailsService` fetches user data from `EmpRepo`.
2. **Password Encoding**: The provided password is hashed and compared using `BCryptPasswordEncoder`.
3. **Security Chain**: `SecurityFilterChain` enforces authentication on all endpoints and uses `DaoAuthenticationProvider` for user verification.
4. **Role-based Authorization**: `@PreAuthorize` annotations in `HomeController` limit access based on user roles.

