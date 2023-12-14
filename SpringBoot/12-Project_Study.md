### Enhanced and Concise Spring Boot Notes

#### 1. **Dependency Injection**  
- **Preferred Approach:** Use constructor injection instead of `@Autowired`:
    ```java
    private final EmpService empService;

    public HomeController(EmpService empService) {
        this.empService = empService;
    }
    ```

#### 2. **Model and Session Usage**
- **Model**: Add objects like `List`, `Entities`.  
- **Session**: Store temporary data, such as messages.

#### 3. **Pagination Example**
- **Controller**:
    ```java
    @GetMapping("/view_notes")
    public String viewNotes(Model model, Principal principal, @RequestParam(defaultValue = "0") Integer pageNo) {
        User user = getUser(principal, model);
        Page<Notes> notesList = notesService.getNotesByUser(user, pageNo);
        model.addAttribute("notesList", notesList);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalElements", notesList.getTotalElements());
        model.addAttribute("totalPages", notesList.getTotalPages());
        return "view_notes";
    }
    ```
- **Service Implementation**:
    ```java
    @Override
    public Page<Notes> getNotesByUser(User user, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return notesReposistory.findByUser(user, pageable);
    }
    ```
- **View Template**:
    ```html
    <ul class="pagination">
        <li th:if="${currentPage != 0}" class="page-item">
            <a class="page-link" th:href="@{'/user/view_notes?pageNo=' + ${currentPage - 1}}">Previous</a>
        </li>
        <li class="page-item" th:each="i : ${#numbers.sequence(1, totalPages)}" th:classappend="${currentPage == i - 1 ? 'active' : ''}">
            <a class="page-link" th:href="@{'/user/view_notes?pageNo=' + ${i - 1}}">[[${i}]]</a>
        </li>
        <li th:if="${currentPage + 1 != totalPages}" class="page-item">
            <a class="page-link" th:href="@{'/user/view_notes?pageNo=' + ${currentPage + 1}}">Next</a>
        </li>
    </ul>
    ```

#### 4. **Lombok**
- Add Dependency:
    ```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.36</version>
        <scope>provided</scope>
    </dependency>
    ```
- Removes boilerplate:
    ```java
    @Entity
    @Getter @Setter @AllArgsConstructor @NoArgsConstructor
    public class Category {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String title;
        private String image;
    }
    ```

#### 5. **File Upload Configuration**
- **application.properties**:
    ```properties
    spring.servlet.multipart.max-file-size=10MB
    spring.servlet.multipart.max-request-size=10MB
    ```
- **Controller Example**:
    ```java
    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute Category category, @RequestParam("image_name") MultipartFile image, HttpSession session) {
        String uploadDir = "src/main/resources/static/img/category_img/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        if (image != null && !image.isEmpty()) {
            String imageName = image.getOriginalFilename();
            Files.copy(image.getInputStream(), uploadPath.resolve(imageName), StandardCopyOption.REPLACE_EXISTING);
            category.setImage(imageName);
        } else {
            category.setImage("default.jpg");
        }

        if (categoryService.existCategory(category.getTitle())) {
            session.setAttribute("errorMsg", "Category already exists.");
        } else {
            categoryService.saveCategory(category);
            session.setAttribute("succMsg", "Category saved successfully.");
        }
        return "redirect:/admin/category";
    }
    ```

#### 6. **Pagination for Search**
```java
@GetMapping("/search-orders")
public String searchOrders(Model model, @RequestParam String keyword, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize) {
    Page<ProductOrder> orderList = orderService.searchOrders(keyword.trim(), pageNo, pageSize);
    model.addAttribute("orderList", orderList.getContent());
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("totalPages", orderList.getTotalPages());
    return "/admin/orders";
}
```

#### 7. **Enums**
- **Example**:
    ```java
    public enum OrderStatus {
        IN_PROGRESS(1, "In Progress"),
        ORDER_RECEIVED(2, "Order Received");

        private final Integer id;
        private final String name;

        OrderStatus(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
        public Integer getId() { return id; }
        public String getName() { return name; }
    }
    ```
    Usage:
    ```java
    productOrder.setStatus(OrderStatus.IN_PROGRESS.getName());
    ```

#### 8. **Best Practices**
- **Avoid modifying user info directly**; use flags like `isActive` or `isLocked`.
- **Constants**: Use `AppConstants` in a utility package for all constants.
- **Pagination Tips**: No `size` attribute in the `Page` object; extract via `page.getContent()`.
- **Transient Fields**: Use `@Transient` to exclude fields from DB:
    ```java
    @Transient
    private Integer quantity;
    ```

#### 9. **Frontend Notes**
- HTML Entities:
    - `&#8377;` for Rupee Symbol
    - `&nbsp;` for a non-breaking space
- Latest Products Example:
    ```java
    model.addAttribute("productListLimited", productService.getAllActiveProducts().stream()
        .sorted(Comparator.comparing(Product::getId).reversed())
        .limit(8)
        .toList());
    ```

#### 10. **AWS Deployment Steps**
1. Create AWS account & login.
2. Set up **IAM User** and **Role** with access keys.
3. Create an **S3 bucket** and configure.
4. Create **RDS database**, update Spring Boot DB properties.
5. Build JAR file: `mvn clean package`.
6. Deploy using **Elastic Beanstalk**:
   - Create environment.
   - Upload JAR to AWS Beanstalk.

---
