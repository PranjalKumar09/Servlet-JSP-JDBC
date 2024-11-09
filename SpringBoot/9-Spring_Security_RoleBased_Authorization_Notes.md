
### 1. **Autowired Behavior in Service**
   - `@Autowired` works in Spring only when the service is defined and managed by the Spring container.

```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
}
```

---

### 2. **Removing Session Message in Service Implementation**
   - Instead of removing the session attribute in the HTML, handle it in the service layer.
   
   **Before (HTML):**
   ```html
   <!-- <th:block th:text="${#session.removeAttribute('msg')}"></th:block> -->
   ```

   **After (Service):**
   ```html
   <th:block th:text="${@userService.removeSessionMessage()}"></th:block>
   ```
   ```java
    @Override
    public void removeSessionMessage(){
        HttpSession session =         ((ServletRequestAttributes)(Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest().getSession();
        session.removeAttribute("msg");
    }
   ```
---

### 3. **Naming Conventions (CamelCase)**
   - Use camelCase for method names and variables in Java.
   - Example: Class name `UserService` -> method name `userService`

---

### 4. **Role-Based Authorization (Spring Security)**

   - To restrict access to certain URL patterns based on roles:

```java
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/user/**").authenticated()   // Requires authentication for /user/**
        .requestMatchers("/**").permitAll()  // Allows access to all other URLs
    )
```

---

### 5. **Common User Information (ModelAttribute)**
   - Use `@ModelAttribute` to add common attributes to all controllers (e.g., user info):

```java
@ModelAttribute
public void commonUser(Principal p, Model m) {
    if (p != null) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
    }
}
```
---

### 6. **Saving User (Password Encryption & Role Assignment)**

```java
@Override
public User saveUser(User user) {
    String password = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(password);
    user.setRole("ROLE_USER");
    return userRepo.save(user);  // Save and return the user
}
```

---

### 7. **Role-Based Authorization (Redirect After Login)**

   - Handle redirection after login based on the role (admin/user):

```java
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/profile");
        } else if (roles.contains("ROLE_USER")) {
            response.sendRedirect("/user/profile");
        }
    }
}
```

---

### 8. **Configuring Spring Security (Filter Chain)**

   - Define security settings with role-based access and login form:

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF (use with caution in production)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/**").hasRole("USER")  // Requires USER role for /user/**
            .requestMatchers("/admin/**").hasRole("ADMIN")  // Requires ADMIN role for /admin/**
            .requestMatchers("/**").permitAll()  // Allows all other requests
        )
        .formLogin(form -> form
            .loginPage("/login")  // Custom login page
            .loginProcessingUrl("/userLogin")  // Login form submission URL
            .defaultSuccessUrl("/user/profile", true)  // Redirect to profile after successful login
            .successHandler(successHandler)  // Custom success handler
            .permitAll()  // Allow public access to login page
        );

    return http.build();
}
```
