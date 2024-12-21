### **AuthenticationManager Bean**
```java
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
}
```
- This bean is used to provide a configured `AuthenticationManager` instance from Spring Security.  
- It simplifies authentication logic in custom services, ensuring proper integration with `UserDetailsService` or other authentication providers.

---

### **JWT Dependencies**
Add the following dependencies for JWT handling with Jackson-based parsing:
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
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```
- **Why these dependencies?**
  - `jjwt-api`: Core JWT API.
  - `jjwt-impl`: Implements core functionalities (e.g., signing tokens).
  - `jjwt-jackson`: Integrates with Jackson for JSON parsing.

---

### **Authentication with JWT**
#### Static Token Example (Hardcoded Token)
```java
@Override
public String login(UserRequest userRequest) {
    Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
    );

    if (authenticate.isAuthenticated()) {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";
    }

    return null;
}
```
- **Why avoid static tokens?**  
  Static tokens are insecure as they don’t adapt to user/session-specific information and lack expiration, making them unsuitable for production.

#### Dynamic Token Generation Example
```java
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
- **Why JWT for Authentication?**  
  1. **Statelessness**: JWT eliminates the need for server-side sessions, as all relevant information is embedded within the token.  
  2. **Scalability**: Perfect for distributed systems.  
  3. **Security**: Allows expiration, claims, and cryptographic signing.

---

### **JWT Token Generation Service**
```java
@Service
public class JwtService {

    private String secretKey = ""; // Dynamically generated secret key

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour validity
                .signWith(getKey()) // Use HMAC for signing
                .compact();
    }

    private SecretKey getKey() {
        if (secretKey.isEmpty()) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
                SecretKey key = keyGenerator.generateKey();
                secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        byte[] encodedKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(encodedKey);
    }
}
```
- **Dynamic Secret Key Generation**:  
  - The secret key is dynamically generated using HMAC-SHA256.  
  - This ensures strong cryptographic security for signing tokens.  

- **Claims**:  
  - Custom claims (e.g., `username`) can be added to the token for enhanced functionality.  

- **Expiration**:  
  - Tokens expire after 1 hour (`setExpiration`), mitigating the risk of token misuse.

---

### **Login Controller**
```java
@PostMapping("/login")
public ResponseEntity<Object> login(@RequestBody UserRequest userRequest) {
    String token = userService.login(userRequest);
    if (token == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
    return ResponseEntity.ok(token);
}
```
- **Response**:
  - Returns the generated JWT if credentials are valid.
  - Sends `401 Unauthorized` for invalid credentials.

---
