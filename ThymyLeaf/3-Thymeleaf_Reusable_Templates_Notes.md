
#### **1. Fragment Inclusion in Thymeleaf**
Thymeleaf provides three key attributes for fragment inclusion:

1. **`th:replace`**  
   - Replaces the content of the target with the specified fragment.  
   Example:  
   ```html
   <div th:replace="~{head::fragment1}"></div>
   ```
   Equivalent to
  ``` html
  <div>
       <!-- header page -->
  </div>
  ```

2. **`th:insert`**  
   - Inserts the specified fragment into the target element without replacing it.  
   Example:  
   ```html
   <div th:insert="~{head::fragment1}"></div>
   ```
  Equivalent to
  ``` html
    <div><div>
       <!-- header page -->

    </div></div>
  ```

3. **`th:include`**  
   - Similar to `th:insert`, but includes the fragment's surrounding elements as well.  
   Example:  
   ```html
   <div th:include="~{head::fragment1}"></div>
   ```
   Equivalent to
  ``` html
  <div>
    <!-- header page -->
  </div>
  ```

#### **2. Defining Fragments**
Fragments can be defined in a reusable manner:
```html
<div th:fragment="fragment1">
    <div>
        <!-- Header Content -->
    </div>
</div>
```

---

#### **3. Example of Reusing Content by ID**
You can reuse specific HTML sections by referencing their `id`.  
Example:  
```html
<div id="copy-section">
    &copy; 2011 The Good Thymes Virtual Grocery
</div>
```

---

#### **4. Base Template Structure (`base.html`)**
A **base template** establishes a layout for consistent reuse across pages:
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" th:fragment="Layout(title, body)">
<head>
    <meta charset="UTF-8">
    <title th:text="${title}">Default Title</title>
</head>
<body>
    <header>
        <h1>Navbar</h1>
    </header>
    <main>
        <p th:text="${body}">Default Body Content</p>
    </main>
    <footer>
        <h1>Footer</h1>
    </footer>
</body>
</html>
```

---

#### **5. Extending Base Template (`index.html`)**
Child templates can extend the base template using `th:replace` to dynamically fill in placeholders:
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      th:replace="base :: Layout(title='Index Page', body='This is the body content')">
<head>
    <meta charset="UTF-8">
    <title>Index Page</title>
    <link th:href="@{/css/style.css}" rel="stylesheet" />
</head>
<body>
</body>
</html>
```

---

#### **6. Controller Example (`HomeController`)**
A **Spring Controller** dynamically injects data into the Thymeleaf template:
```java
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        // Add dynamic data to the model
        model.addAttribute("title", "Index Page");
        model.addAttribute("body", "Welcome to the homepage! This content is dynamically provided by the controller.");
        return "index"; // Refers to the "index.html" template in the templates folder
    }
}
```

---

### **Key Benefits of Thymeleaf Reusability**
1. **Separation of Concerns**: HTML structure and data logic are kept separate.  
2. **Modularity**: Fragments and base templates enable easy updates.  
3. **Dynamic Content**: Controllers inject data into templates dynamically.  
4. **Consistency**: Ensures uniform structure across multiple pages.  
5. **Scalability**: Simplifies complex projects by reusing predefined components.