Here are enhanced, well-structured notes summarizing the content provided:

---

## **API (Application Programming Interface)**

### **Definition**
- An API facilitates communication between two applications.
- APIs are written using a programming language and deployed on a server.

---

## **REST (Representational State Transfer)**

### **Key Features**
1. **Platform Independence**:
   - RESTful web services can be written in any language and run on any platform.
2. **Flexible Data Formats**:
   - Supports JSON, XML, HTML, and plain text.
3. **Reusable**:
   - REST APIs can be used across different applications and environments.
4. **Language Neutrality**:
   - Language-agnostic implementation.

### **HTTP Methods**
| **Method** | **Description**                   |
|------------|-----------------------------------|
| `GET`      | Reads a resource.                |
| `POST`     | Creates a new resource.          |
| `PUT`      | Updates an existing resource.    |
| `DELETE`   | Deletes a resource.              |

### **HTTP Status Codes**
| **Code** | **Meaning**                  |
|----------|------------------------------|
| `200`    | Success                      |
| `201`    | Resource Created             |
| `401`    | Unauthorized Access          |
| `404`    | Resource Not Found           |
| `500`    | Internal Server Error        |

---

## **Separation of Concerns**
- Use **DTOs (Data Transfer Objects)** to decouple the presentation layer from the data layer.
- DTOs act as a bridge between the frontend and backend.

### **Why DTOs?**
- Prevent direct exposure of database models.
- Enable transformation of data as needed for the frontend.

---

## **Improving Boilerplate Code**

### **Mapping DTOs to Entities**
**Before: Manual Mapping**
```java
public Boolean saveProduct(ProductDto productDto) {
    Product product = new Product();
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());
    product.setDescription(productDto.getDescription());
    product.setId(productDto.getId());
    product.setQuantity(productDto.getQuantity());
    return productRepository.save(product) != null;
}
```

**After: Using `ModelMapper`**
```java
public Boolean saveProduct(ProductDto productDto) {
    Product product = mapper.map(productDto, Product.class);
    return productRepository.save(product) != null;
}
```

### **Fetching Products**
```java
public List<ProductDto> getProducts() {
    return productRepository.findAll().stream()
        .map(product -> mapper.map(product, ProductDto.class))
        .toList();
}
```

### **Pagination Example**
```java
public ProductResponse getProductWithPagination(int pageNo, int pageSize, String sortBy, Boolean asc) {
    Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    Page<Product> page = productRepository.findAll(pageable);

    List<ProductDto> productDtoList = page.getContent().stream()
        .map(product -> mapper.map(product, ProductDto.class))
        .toList();

    return ProductResponse.builder()
        .productDtoList(productDtoList)
        .isFirst(page.isFirst())
        .isLast(page.isLast())
        .totalPages(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .pageSize(pageSize)
        .pageNo(pageNo)
        .build();
}
```

---

## **Testing REST APIs**
- Use `.http` files with the HTTP request extension.
- Example command:
    ```http
    GET http://localhost:8080/getUsers
    Content-Type: application/json
    ```

Payload example:
```json
{
    "name": "Laptop",
    "description": "A high-end laptop",
    "price": 1200.50
}
```

---

## **Global Exception Handling**

### **Purpose**
- Ensure consistent and user-friendly error messages.
- Separate exception handling logic from controllers.

### **Implementation Using `@ControllerAdvice`**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message("Argument type mismatch: " + ex.getMessage())
            .timestamp(LocalDateTime.now())
            .details("Expected: " + ex.getRequiredType() + ", but got: " + ex.getValue())
            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
```

###  `@RestControllerAdvice`
- Specialization of `@ControllerAdvice` that targets `@RestController` (JSON APIs).
- Automatically adds `@ResponseBody` to all methods, ensuring **JSON/XML responses** instead of views.

``` java
@RestControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```


---

## **Project Setup**

### **Maven `pom.xml`**
- Essential dependencies for a Spring Boot project:
    ```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.2.2</version>
        </dependency>
    </dependencies>
    ```

---

## **Configuration**

### **ModelMapper Bean Configuration**
```java
@Configuration
public class ProjectConfig {
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
```

---

## **Builder Pattern with Lombok**
- Simplifies object creation:
```java
@Data
@Builder
public class ProductResponse {
    private List<ProductDto> productDtoList;
    private boolean isFirst;
    private boolean isLast;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private int pageNo;
}
```

---

## **Key Takeaways**
1. Use `DTO` for clean separation between layers.
2. Leverage tools like `ModelMapper` for efficient entity-DTO conversions.
3. Implement `@ControllerAdvice` for centralized exception handling.
4. Write clean and reusable pagination, sorting, and CRUD methods.
5. Keep configurations modular and adhere to best practices for maintainability.

---
## **Packages like**
- config
- controller
- dto
- exception
- model
- repository
- service
    - impl