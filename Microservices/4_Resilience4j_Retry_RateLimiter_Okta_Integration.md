Here's your enhanced and structured version of the notes while retaining all information:  

---

# **Retry Mechanism**  

### **Overview**  
- In some cases, it might be beneficial to **retry failed requests** before executing a fallback method.  
- This helps when failures are **temporary** (e.g., network glitches, momentary service unavailability).  
- **Resilience4j Retry** allows retrying a failed request before marking it as failed.  

### **Implementation**  
Instead of **Circuit Breaker**, we can use **Retry** to attempt the request multiple times before falling back:  

```java
@GetMapping("/{userId}")
// @CircuitBreaker(name= "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
@Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
public ResponseEntity<User> getUser(@PathVariable String userId) {
    // Fetch user details logic
}
```

### **Retry Configuration in `application.yml`**  
```yaml
resilience4j:
  retry:
    instances:
      ratingHotelBreaker:
        max-attempts: 3
        wait-duration:
          seconds: 5
```
**Explanation:**  
- **max-attempts:** The maximum number of retry attempts before failure.  
- **wait-duration:** The time interval (in seconds) between retry attempts.  

---

# **Rate Limiter**  

### **Overview**  
- Rate Limiting restricts access to certain services by **limiting the number of requests** per user/IP/service.  
- It prevents overloading and protects the system from abuse.  
- **Resilience4j Rate Limiter** is used to **control request frequency**.  

### **Rate Limiter Configuration in `application.yml`**  
```yaml
resilience4j:
  ratelimiter:
    instances:
      ratingHotelRateLimiter:
        limit-refresh-period:
          seconds: 4
        limit-for-period: 2
        timeout-duration: 0s
```

**Explanation:**  
- **limit-refresh-period:** The time window for counting requests (e.g., 4 seconds).  
- **limit-for-period:** The maximum number of allowed requests within the refresh period (e.g., 2 requests per 4 seconds).  
- **timeout-duration:** The time a request should wait if the limit is exceeded (set to `0s` to reject immediately).  

### **Usage in Controller**  
```java
@GetMapping("/{userId}")
@RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallback")
public ResponseEntity<User> getUser(@PathVariable String userId) {
    // Fetch user details logic
}
```

---

# **Okta Service (Authentication & Authorization)**  

### **Overview**  
- **Okta** ensures secure access by requiring an **Access Token** for every request.  
- The **API Gateway** is responsible for authenticating requests using Okta.  
- **Flow:**  
  1. The **client** sends a request to the **API Gateway**.  
  2. The API Gateway forwards the request to **Okta** for authentication.  
  3. Okta returns an **Access Token** to the API Gateway.  
  4. The API Gateway sends the token to **downstream services** for validation before processing requests.  

### **Okta Setup**  
1. **Create an Okta Developer Account**.  
2. **In Okta, create an App Integration**:
   - Define roles/groups (e.g., `Admin`, `Normal` users).  
   - Add users (e.g., yourself).  
3. **Edit Default API Settings**:  
   - Add **internal scopes**.  
4. **Define Claims**:  
   - **Value Type:** `Groups`.  
   - **Match using regex** to associate user roles.  

---

### **Integrating Okta with API Gateway**  
1. **Add Security Dependencies**  

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>com.okta.spring</groupId>
    <artifactId>okta-spring-boot-starter</artifactId>
</dependency>
```

2. **Implement Interceptor for Token Verification**  
- Services dependent on other services should validate tokens **before allowing access**.  

### **Important Note**  
🚨 **Okta no longer works in new Spring Boot projects by default** – additional configuration may be needed to latest version


### 

---
