### Thymeleaf: Concise and Enhanced Notes

**Overview**  
Thymeleaf is a modern, server-side Java template engine designed for both **web** and **standalone environments**. It can process **HTML, XML, JavaScript, CSS**, and plain text, offering a highly maintainable approach to creating templates. It is commonly used with **Spring MVC** to generate dynamic HTML views.

---

### Features of Thymeleaf
- **Dynamic Content**: Incorporates Thymeleaf expressions to render dynamic content.
- **Highly Elegant and highly maintainable**
- **Integration**: Access Java variables, objects, and Spring Beans seamlessly.
- **Readable Templates**: Templates can be viewed directly as HTML, simplifying development and debugging.

---

### How Thymeleaf Works
1. Parses templates (HTML with Thymeleaf syntax).
2. Uses a Java data model to replace placeholders.
3. Generates the final text (e.g., an HTML page).
4. For web apps, the result is processed on the server and sent to the browser.

---

### Implementation with Spring Boot
In `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

---

### Thymeleaf Syntax Overview

1. **Expressions**:
   - **Variable Expression**: `${var}`  
   - **Selection Variable Expression**: `*{var}`  
   - **Message Expression**: `#{key}`  
   - **Link URL Expression**: `@{url}`  
   - **Fragment Expression**: `~{template}`  

2. **Operations**:
   - Literals, text, arithmetic, boolean, comparison, and conditional operators.

---

### Example: Dynamic Content

**Message Properties** (`messages.properties`):
```properties
name.full=Pranjal Kumar Shukla
```

**Template Example**:
```html
<h4 th:text="#{name.full}"></h4>
```
- Renders: `Pranjal Kumar Shukla`.

**Unescaped Content** (`messages.properties`):
```properties
name.full=<b>Pranjal</b> Kumar Shukla
```
- Using `th:text` escapes HTML: Displays `<b>Pranjal</b> Kumar Shukla`.  
- Using `th:utext` renders bold text: **Pranjal** Kumar Shukla.



    
```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("today", new Date());
        return "index";
    }
}
```

``` html
    <p>Today is: [[${today}]]</p>
    <p th:text="${today}"></p>
```


---

### Dynamic Binding with Java Model
**Controller**:
```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("u1", new User("John", "123 Street", 1));
        return "index";
    }
}
```

**Template**:
```html

    <h3 th:text="${u1.name}"></h3>
    <h3 th:text="${u1.address}"></h3>
    <h3 th:text="${u1.id}"></h3>

```


```html
<div th:object="${u1}">
    <h4>Name: <span th:text="*{name}"></span></h4>
    <h4>Address: <span th:text="*{address}"></span></h4>
    <h4>ID: <span th:text="*{id}"></span></h4>
</div>
```

---

### Links and Resources

1. **Dynamic Links**:
   ```html
   <a th:href="@{/profile}">Profile</a>
   <a th:href="@{/profile/{id}(id=${u1.id})}">Profile with ID</a>
   ```
   Generates URLs like `/profile` and `/profile/1`.

2. **Static Resources**:
   Link a CSS file located at `resources/static/css/style.css`:
   ```html
   <link th:href="@{/css/style.css}" rel="stylesheet" />
   ```

---

### Error Notes
- A **White Page Error** can indicate various issues, such as typos or misconfigured attributes.
