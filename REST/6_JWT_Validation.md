###  JWT Validation with Spring Security

#### Overview

To implement **JWT (JSON Web Token)** validation in a Spring Boot application, several components and configurations are required. This ensures secure stateless authentication using tokens. Below is a comprehensive explanation of all components, their roles, and the necessary configurations.


Firstly we need to import dependencies

```xml
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.6</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>

```
---

### Components and Classes

#### **1. JwtFilter**
A custom filter for processing incoming requests and validating JWTs.

- **Purpose**: Ensures each request contains a valid JWT and, if valid, authenticates the user in the `SecurityContext`.

**Code Explanation**:

```java
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsSercviceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Remove "Bearer " prefix
            username = jwtService.extractUsername(token); // Extract username from the token
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Load user details
            Boolean validateToken = jwtService.validateToken(token, userDetails); // Validate the token

            if (validateToken) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Set authenticated user
            }
        }
        filterChain.doFilter(request, response); // Continue the request chain
    }
}
```

---

#### **2. MyUserDetails**

Implements `UserDetails` to bridge between your application's `User` entity and Spring Security.

- **Purpose**: Maps `UserDtls` (custom user entity) to Spring Security's `UserDetails`.

**Key Methods**:
- `getAuthorities()`: Assigns roles or authorities to the user.
- `getPassword()`: Retrieves the user password for authentication.
- `getUsername()`: Retrieves the username for authentication.

**Code Explanation**:

```java
public class MyUserDetails implements UserDetails {
    private UserDtls userDtls; // Custom user entity

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER")); // Default role
    }

    @Override
    public String getPassword() {
        return userDtls.getPassword();
    }

    @Override
    public String getUsername() {
        return userDtls.getUsername();
    }

    public MyUserDetails(UserDtls userDtls) {
        this.userDtls = userDtls;
    }

    public UserDtls getUserDtls() {
        return userDtls;
    }

    public void setUserDtls(UserDtls userDtls) {
        this.userDtls = userDtls;
    }
}
```

---

#### **3. MyUserDetailsServiceImpl**

A service to fetch user details by username.

- **Purpose**: Fetches user data from the database and wraps it in `MyUserDetails`.

**Code Explanation**:

```java
@Service
public class MyUserDetailsSercviceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDtls userDtls = userRepository.findByUsername(username); // Fetch user by username
        if (userDtls == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(userDtls); // Wrap user in MyUserDetails
    }
}
```

---

#### **4. JwtService**

Handles JWT creation and validation.

- **Purpose**: Generates, validates, and extracts claims from JWTs.

**Key Methods**:
- `generateToken()`: Creates a new JWT for a given username.
- `extractUsername()`: Retrieves the username from a token.
- `validateToken()`: Validates the token's signature and checks expiration.
- `extractClaims()`: Generic method to extract claims.

**Code Explanation**:

```java
@Service
public class JwtService {
    private String secretKey;

    public JwtService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(key.getEncoded()); // Generate base64-encoded key
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username); // Add claims
        return Jwts.builder()
            
                .setClaims(claims)
                .setSubject(username) // Set subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // Issue time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expiry time
                .signWith(getKey()) // Sign with secret key
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject); // Extract subject (username)
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return (extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody(); // Parse and retrieve claims
    }

    private SecretKey getKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decodedKey); // Generate secret key
    }
}
```
Or 
``` java
    @Override
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());
        claims.put("status", user.getStatus().getIsActive());

        String token = Jwts.builder()
                .claim("id", user.getId())
                .claims(claims)
                .subject(user.getEmail())
                .issuedAt(new Date((System.currentTimeMillis())))
                .expiration(new Date((System.currentTimeMillis() + 60*60*1000)))
                .signWith(getKey())
                .compact();
        return token;
    }

```

---

### Security Configuration

Configures Spring Security to use JWT authentication.

- **Key Additions**:
  - Disables `CSRF`.
  - Uses `JwtFilter` before `UsernamePasswordAuthenticationFilter`.
  - Configures stateless session management.

**Code Explanation**:

```java
@Configuration
@EnableWebSecurity
public class SecuirtyConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless auth
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // Permit login endpoint
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .httpBasic(Customizer.withDefaults()) // Basic authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encoder for passwords
    }

    @Bean
    public DaoAuthenticationProvider authProvider(MyUserDetailsSercviceImpl userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
```

---

### Login Endpoint

Handles authentication and token generation.

**Key Steps**:
1. Authenticate user credentials.
2. Generate a JWT if authentication is successful.

**Code Explanation**:

```java
@PostMapping("/login")
public ResponseEntity<Object> login(@RequestBody UserRequest userRequest) {
    String token = userService.login(userRequest);

    if (token == null) {
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(token, HttpStatus.OK);
}

@Override
public String login(UserRequest userRequest) {
    Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
    );

    if (authenticate.isAuthenticated()) {
        return jwtService.generateToken(userRequest.getUsername());
    }
    return null;
}
```

Or 

``` java
@Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication =   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            if (authentication.isAuthenticated()) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(customUserDetails.getUser());
            UserDto userDto = modelMapper.map(customUserDetails.getUser(), UserDto.class);

            return LoginResponse.builder()
                    .token(token)
                    .user(userDto)
                    .build();
        }
        return null;

    }
```

---

### Summary

1. **JwtFilter**: Intercepts requests, validates JWT, and sets the security context.
2. **JwtService**: Generates and validates tokens.
3. **MyUserDetails**: Maps custom user entity to Spring Security's user structure.
4. **MyUserDetailsServiceImpl**: Fetches user details from the database.
5. **SecurityConfig**: Configures Spring Security to use JWT for stateless authentication.

