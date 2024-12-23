### Enhanced Notes: Spring Security with Role and Privilege Management

---

#### **1. Lazy Fetching in `@ManyToMany`**
- **Problem**: `@ManyToMany` associations are **lazy by default**, meaning data is fetched only when explicitly accessed.
- **Solution**: To avoid manual fetch calls, specify eager fetching:
  ```java
  @ManyToMany(fetch = FetchType.EAGER)
  ```

---

#### **2. Security Configuration**
- **Annotations**:
  - `@EnableWebSecurity`: Enables Spring Security.
  - `@EnableMethodSecurity`: Allows method-level security annotations (e.g., `@PreAuthorize`).

- **Configuration Class**:
  ```java
  @Configuration
  @EnableWebSecurity
  @EnableMethodSecurity
  public class SecurityConfig {
  ```
  - **Password Encoder**: Uses `BCryptPasswordEncoder` for secure password hashing.
    ```java
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    ```

  - **DAO Authentication Provider**:
    Links `UserDetailsService` with the password encoder.
    ```java
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    ```

  - **Authentication Manager**:
    Retrieves the authentication manager from configuration.
    ```java
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    ```

  - **Security Filter Chain**:
    Configures security rules for endpoints.
    ```java
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET).hasAuthority("READ")
                .requestMatchers(HttpMethod.POST).hasAuthority("CREATE")
                .requestMatchers(HttpMethod.PUT).hasAuthority("UPDATE")
                .requestMatchers(HttpMethod.DELETE).hasAuthority("DELETE")
                .requestMatchers("/product/**").hasAnyRole("ADMIN", "SELLER", "USER")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    ```

---

#### **3. `UserDetails` Implementation**
- Custom implementation `UserDetailsImpl` maps your `User` entity to Spring Security's `UserDetails`.
- **Mandatory Methods**: Proper implementation is crucial for correct authorization. Examples:
  ```java
  @Override
  public boolean isAccountNonExpired() {
      return true; // Customize based on your logic
  }

  @Override
  public boolean isAccountNonLocked() {
      return true; // Customize based on your logic
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true; // Customize based on your logic
  }

  @Override
  public boolean isEnabled() {
      return true; // Customize based on your logic
  }
  ```

---

#### **4. `UserDetailsService` Implementation**
- Service class `UserDetailsServiceImpl` loads user details by username.
- **Key Logic**:
  - Fetch user by username.
  - Create `SimpleGrantedAuthority` list for both privileges and roles.
    ```java
    List<SimpleGrantedAuthority> simpleGrantedAuthorityList = Stream.concat(
        user.getRole().getPrivilleges().stream().map(priv -> new SimpleGrantedAuthority(priv.getName())),
        Stream.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()))
    ).toList();
    ```

- **Handle Null Users**:
  ```java
  if (user == null) {
      throw new UsernameNotFoundException("User Not Found");
  }
  ```

---

#### **5. Entity Models**
##### **Privilege Entity**
Represents specific permissions.
```java
@Data
@Entity
public class Privillege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
```

##### **Role Entity**
Holds a collection of privileges.
```java
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_privillege", 
               joinColumns = @JoinColumn(name = "role_id"))
    private List<Privillege> privilleges;
}
```

##### **User Entity**
Contains user details and a reference to a role.
```java
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String password;

    @ManyToOne
    private Role role;
}
```

---

#### **Important Points**
1. **Authorization Methodology**:
   - Roles: Prefixed with `ROLE_`.
   - Privileges: Added directly as authorities.

2. **Security Flow**:
   - User logs in.
   - `UserDetailsServiceImpl` loads user and roles/privileges.
   - Authorities are validated against endpoint access rules.

3. **Boolean Methods in `UserDetails`**:
   Must be properly implemented; otherwise, users may not be authorized.

4. **Key Code Snippet**:
   The concatenation of role and privilege authorities is critical:
   ```java
   Stream.concat(
       user.getRole().getPrivilleges().stream().map(priv -> new SimpleGrantedAuthority(priv.getName())),
       Stream.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()))
   ).toList();
   ```

This setup ensures a robust and flexible security system, allowing granular control over access permissions using roles and privileges.






``` java

@Data
public class UserDetailsImpl implements UserDetails {

    private User user;
    private List<SimpleGrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify based on your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify based on your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify based on your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify based on your logic
    }
}


```
``` java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        System.out.println("User found: " + user.getUsername());
        System.out.println("User roles and privileges: " + user.getRole().getName());
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = Stream.concat(
                user.getRole().getPrivilleges().stream().map(priv -> new SimpleGrantedAuthority(priv.getName())),
                Stream.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()))
        ).toList();
        System.out.println("Assigned Authorities: " + simpleGrantedAuthorityList);
        userDetails.setAuthorities(simpleGrantedAuthorityList);

        return userDetails;
    }
}

```