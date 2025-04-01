
# **Understanding Microservices and Implementation with Spring Boot**

## **Monolithic Architecture vs. Microservices**

### **Before Microservices: Monolithic Architecture**
- A monolithic architecture integrates multiple components into a single large application.  
- Uses a **single codebase** and is **deployed as a single bundle**.  
- Any change in one service **requires redeploying the entire application**.  
- **Development bottlenecks** arise as developers must coordinate extensively.  
- **Scaling is difficult** and becomes **cumbersome over time** as the application grows.  

### **Microservices Architecture**
- **Breaks down large applications** into smaller, independent services.  
- Each microservice **communicates via REST APIs**.  
- Allows for **different codebases** and **different technology stacks** for each module.  
- **Each service is managed independently**, improving flexibility.  
- **Disadvantage**: Managing multiple microservices is complex.

---

## **Project Overview: Building a Microservices-Based System**
This project consists of three microservices, each with its **own database**:  
1. **User Service**  
2. **Hotel Service**  
3. **Rating Service**  

### **Additional Components**
- **Configuration Service**:  
- Configured using **Git** for centralized configuration management.  
- **API Gateway**:  
- All microservices are accessed through an API Gateway.  
- **Authentication is handled via Okta OAuth**.  
- **Service Registry**:  
- All services register themselves in a **service registry** (Eureka).  
- Keeps track of **service names, IP addresses, and ports** for discovery.  
- Example: A service might be available at `http://service-name/`.  

---

## **Microservices Implementation in Spring Boot**

### **Creating the `Rating` Class**
The `Rating` class represents the ratings users provide for hotels. This is **not an entity**, just a model for API responses.  

```java
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
```

### **MongoDB Considerations**
- In **MongoDB**, there is **no need to manually generate unique string IDs**—Mongo handles it automatically.  
- **Repositories** use `MongoRepository` instead of `JpaRepository`.  

---

## **Service Registry: Eureka Server Setup**
A **service registry** is required to manage service discovery. **Spring Cloud Netflix Eureka** is used for this purpose.  

### **Step 1: Create a Spring Boot Application for Eureka Server**
- Add the necessary dependencies in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter</artifactId>
</dependency>
```

- Add the `@EnableEurekaServer` annotation in the main class:

```java
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }
}
```

- Configure `application.yml` for the Eureka server:

```yaml
eureka:
instance:
    hostname: localhost
client:
    register-with-eureka: false
    fetch-registry: false

server:
port: 8761
```

Eureka will now be available at **port 8761**.

---

## **Registering Microservices as Eureka Clients**
Each microservice (User, Hotel, Rating) needs to be registered with Eureka.

### **Step 1: Add Dependencies in `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>


<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter</artifactId>
</dependency>
```

### **Step 2: Configure `application.yml` for Each Service**
Each microservice should have a unique name in Eureka:

```yaml
spring:
application:
    name: UserService
```

### **Step 3: Enable Eureka Client**
Add the `@EnableEurekaClient` annotation in the main application class.

```java
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

---

## **Inter-Service Communication**
### **Using `RestTemplate` for API Calls**
To fetch data from **Hotel Service** and **Rating Service**, use `RestTemplate`.

#### **Creating a `RestTemplate` Bean**
```java
@Configuration
public class MyConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

#### **Fetching Data from Other Microservices**
Example: Getting user ratings from **Rating Service** and hotel details from **Hotel Service**.

```java
@Override
public User getUserById(String id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

    // Fetch ratings from Rating Service
    Rating[] ratingsArray = restTemplate.getForObject(
        "http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class);
    
    List<Rating> ratings = Arrays.stream(ratingsArray).toList();

    // Fetch hotel details for each rating
    ratings.forEach(rating -> {
        Hotel hotel = restTemplate.getForObject(
            "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
        rating.setHotel(hotel);
    });

    user.setRatings(ratings);
    return user;
}
```

#### **Avoiding Hardcoded URLs**
Using `@LoadBalanced` annotation in `RestTemplate` allows **service discovery through Eureka**.

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

Now, services can be accessed via their **Eureka service names**:

- `"http://RATING-SERVICE/rating/user/{userId}"`
- `"http://HOTEL-SERVICE/hotels/{hotelId}"`

---

## **Using Feign Client for Simpler API Calls**
Feign simplifies HTTP requests compared to `RestTemplate`.

### **Step 1: Add Feign Dependency**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### **Step 2: Create a Feign Client for `HotelService`**
```java
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
```

### **Step 3: Use Feign in `UserService`**
```java
@Autowired
private HotelService hotelService;

@Override
public User getUserById(String id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

    Rating[] ratingsArray = restTemplate.getForObject(
        "http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class);

    List<Rating> ratings = Arrays.stream(ratingsArray).toList();

    // Fetch hotels using Feign instead of RestTemplate
    ratings.forEach(rating -> {
        Hotel hotel = hotelService.getHotel(rating.getHotelId());
        rating.setHotel(hotel);
    });

    user.setRatings(ratings);
    return user;
}
```

### **Step 4: Enable Feign Clients**
Add the `@EnableFeignClients` annotation in the main application class:

```java
@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

---

## **Conclusion**
This guide outlines a **complete microservices architecture** using **Spring Boot, Eureka, API Gateway, and Feign Clients**.  
- Services communicate **without hardcoded URLs** using Eureka.  
- Feign **simplifies API calls**, replacing `RestTemplate`.  
- `@LoadBalanced` **resolves service names dynamically**.  
- API Gateway provides **centralized authentication and routing**.  

This setup ensures **scalability, maintainability, and flexibility** for a robust microservices-based application. 🚀








### **`@Transient` in JPA**  
The `@Transient` annotation **excludes fields from being persisted** in the database but allows them in API responses. This is useful for **fetching related data dynamically** without storing redundant information.  

#### **Example Usage**  
```java
public class User {
    @Id
    private String userId;
    private String name;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
```
Here, `ratings` **won’t be stored in the database** but can be populated dynamically when retrieving a user.  

#### **Fetching Data Dynamically**  
```java
user.setRatings(restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class));
```
This keeps the **database lightweight** while enabling **rich API responses**. 🚀