### **Controller Setup**
- **Endpoint Implementation**  
  ```java
  @GetMapping("/")
  public ResponseEntity<Object> index() {
      return new ResponseEntity<>("Application is OK", HttpStatus.OK);
  }
  ```
  - **Behavior**: Navigating to the root endpoint (`/`) in a browser displays the message **"Application is OK"**.

---

### **Spring Security Configuration**

#### **@EnableWebSecurity**
- **When to Use Explicitly?**
  - **Explicit Declaration of Security Context**  
    Ensures Spring Security is enabled explicitly, avoiding reliance on Spring Boot's auto-configuration, particularly useful for non-Boot applications.
  - **Customization of Security Behavior**  
    Grants control for advanced configurations, signaling intentional security setup.
  - **Non-Boot Applications**  
    Mandatory in plain Spring applications to enable Spring Security.
  - **Clarity and Consistency**  
    Makes it clear the class is specifically configuring Spring Security.

#### **Basic Security Setup** -> httpBasic
- **Prefer `httpBasic`** over form login for:
  - **Stateless Authentication**: No server-side state.
  - **No UI for Login**: Suitable for APIs.
  - **Simplicity**: Direct authentication without extra layers.

#### **Example: Security Configuration**
```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for REST APIs
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/public/**").permitAll() // Allow public endpoints
            .anyRequest().authenticated()             // Secure other endpoints
        )
        .httpBasic(withDefaults()); // Use HTTP Basic Authentication
    return http.build();
}
```

---

### **JWT (JSON Web Token)**

- **Overview**:  
  JWT is a simple string-based token with 3 parts:  
  - **Header**: Metadata  
  - **Payload**: Data  
  - **Signature**: Encrypted using a secret key.

- **Mechanism**:
  - Based on **Public & Private Key Cryptography**:
    - **Public Key**: Can be shared/distributed.  
    - **Private Key**: Kept secure and used for decryption.  
  - **Stateless Authentication**:  
    - No server-side state storage (e.g., DB or files).  
    - JWT stores all necessary data.

- **Analogy**:  
  Like a gym subscription pass—allows quick, verified access.

---

### **Stateless vs. Stateful Authentication**

| **Stateless**                    | **Stateful**                  |
|----------------------------------|-------------------------------|
| No server-side state storage.    | State stored in DB/files.     |
| JWT operates on this principle.  | Traditional session-based.    |
