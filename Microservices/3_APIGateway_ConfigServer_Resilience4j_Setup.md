
# **API Gateway**  

### **Overview**  
- The API Gateway itself is a **microservice**.  
- We use **Spring Cloud Gateway**, which runs on **Netty** (provided by Spring Boot and Spring WebFlux).  
- **Spring Cloud Gateway** does **not** work in a traditional servlet container or when built as a WAR file.  

### **Dependencies Used**  
We include the following dependencies in our project:  

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway-mvc</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.projectreactor</groupId>
    <artifactId>reactor-test</artifactId>
    <scope>test</scope>
</dependency>
```

---

### **Eureka Configuration in API Gateway**  
- The API Gateway will also be configured with **Eureka Service Discovery**.  
- The configuration for **Eureka** in `application.yml`:  

```yaml
eureka:
  instance:
    prefer-ip-address: true
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
    fetch-registry: true
    register-with-eureka: true
```

---

### **Spring Cloud Gateway Route Configuration**  
Routes define how API Gateway will forward requests to other services.  

```yaml
spring:
  cloud:
    gateway:
      mvc:
        routes:
          - id: USERSERVICE
            uri: lb://USER-SERVICE
            predicates:
              - Path=/users/**

          - id: HOTEL-SERVICE
            uri: lb://HOTEL-SERVICE
            predicates:
              - Path=/hotels/**

          - id: RATING-SERVICE
            uri: lb://RATING-SERVICE
            predicates:
              - Path=/ratings/**
```

**Handling Multiple Paths for a Service**  
If a service has multiple URLs, such as `/hotels/` and `/staffs/`, we define routes like this:  

```yaml
          - id: HOTEL-SERVICE
            uri: lb://HOTEL-SERVICE
            predicates:
              - Path=/hotels/**,/staffs/**
```

**Wildcard for All Routes (Not Recommended)**  
```yaml
          - Path=/**
```
- This will route **all** requests, but it's **not recommended** as it can lead to security risks.  

---

# **Config Server**  

### **Overview**  
- Implements a **client-server architecture** for managing configuration externally.  
- Allows **distributed** configuration.  
- Config Server will be a **separate microservice**.  

### **Dependencies Required**  
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
- Eureka properties are **not needed** separately as they are automatically managed.  

### **Enable Config Server**  
Add the following annotation to the **main class** of the Config Server microservice:  

```java
@EnableConfigServer
```

---

### **Config Server Properties (`application.yml`)**  
- Configuration is stored in a **Git repository**.  
- The Git repository link is provided in `application.yml`:  

```yaml
server:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/PranjalKumar09/microservice-tutorial-config
          clone-on-start: true
```

- After setting up, we can access the configuration properties using:  
  - **Default application properties** → [http://localhost:8085/application/default](http://localhost:8085/application/default)  
  - **For specific profiles** (e.g., dev) → [http://localhost:8085/application/dev](http://localhost:8085/application/dev)  

---

### **Config Client Setup in Microservices (User, etc.)**  
To allow services to fetch configurations from the Config Server, add the **Config Client dependency**:  

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

Then, add the following properties to the microservice's `application.yml`:  

```yaml
spring:
  config:
    import: configserver:http://localhost:8085
```

**Setting Up Profiles**  
To load configuration for a specific environment (e.g., **dev**), add:  

```yaml
spring:
  profiles:
    active: dev
```
- This will load properties from `application-dev.yml` or `application.yml` based on the active profile.  

---

# **Fault Tolerance**  

### **Overview**  
- Microservices depend on multiple services, and failures in one service should **not** bring down the entire system.  
- After a **threshold of failures**, a service should **close** the circuit to avoid further impact.  
- **Resilience4j** is used to implement **Circuit Breaker** for handling failures.  

### **Dependencies Required**  
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
```

---

### **Using Circuit Breaker in Controller**  

**Implementation Example**:  
```java
@GetMapping("/{userId}")
@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
public ResponseEntity<User> getUser(@PathVariable String userId) {
    // Fetch user details logic
}

public ResponseEntity<User> ratingHotelFallback(String userId, Exception e) {
    log.info("Fallback executed because the service is down: {}", e.getMessage());
    User user = User.builder()
            .email("dummy@gmail.com")
            .name("Dummy")
            .about("This user is created as a fallback because some services are down")
            .userId("-1")
            .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);
}
```
**Important Notes:**  
- **Fallback method** must have the **same response type** and **same request parameters** as the original method.  

---

### **Circuit Breaker Configuration in `application.yml`**  

```yaml
management:
  health:
    circuitbreakers:
      enabled: true
  endpoints:
    web:
      exposure:
        include: health

  endpoint:
    health:
      show-details: always

resilience4j:
  circuitbreaker:
    instances:
      ratingHotelBreaker:
        register-health-indicator: true
        event-consumer-buffer-size: 10
        failure-rate-threshold: 50
        minimum-number-of-calls: 5
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state:
          seconds: 6
        permitted-number-of-calls-in-half-open-state: 3
        sliding-window-size: 10
        sliding-window-type: count_based
```

### **Health Check Endpoint**  
- To check circuit breaker health, visit:  
  ```
  http://localhost:8081/actuator/health
  ```

---