### **Thymeleaf Code Reusability, Literals, and Conditional Logic**

---

#### **1. Code Reusability with Fragments**
Fragments allow reusable HTML snippets across templates.

**Example:**
- **head.html**  
  ```html
  <div th:fragment="footer">
      <h1>Footer</h1>
  </div>
  ```

- **footer.html**  
  ```html
  <div th:fragment="footer">
      <h1>Footer</h1>
  </div>
  ```

- **Controller**  
  ```java
  @GetMapping("/header")
  public String header(Model model) {
      return "head";
  }

  @GetMapping("/footer")
  public String footer(Model model) {
      return "footer";
  }
  ```

- **index.html** (Main Template)  
  ```html
  <div th:replace="~{head::fragment1}"></div>
  <div th:replace="~{footer::footer}"></div>
  ```

---

#### **2. Literals**
Embed dynamic values directly in HTML.

**Example:**
```html
<p>In two years, it will be <span th:text="2024+2"></span></p>
```

---

#### **3. Conditional Statements**
Use `th:if` and `th:unless` for conditional rendering.

**Examples:**
- Simple condition:  
  ```html
  <div th:if="${2 < 3}">True block</div>
  <div th:unless="${2 > 3}">False block</div>
  ```

- Object check:  
  ```html
  <div th:if="${obj} == null">Object is null</div>
  ```

---

#### **4. Text and String Concatenation**
Dynamically render text content with variables.

**Examples:**
```html
<h4 th:text="'Hello'"></h4>
<h4 th:text="'Hello guys, I am ' + ${u1.name}"></h4>
```

---

#### **5. Variables with `th:with`**
Define and use local variables in a block.

**Example:**
```html
<div th:with="num=${10 + 10}">
    num=[[${num}]]
    <th:block th:with="even=${num % 2 == 0}">
        Even block
    </th:block>
</div>
```

---

#### **6. Elvish Operator (`?`)**
Checks for null-safe evaluations.

**Example:**
```html
<p>[[${u1?.name}]]</p>
```

---

#### **7. Dynamic Attribute Values**
Dynamic form value using properties file.

**Example:**
```html
<input type="submit" value="SUBMIT FORM" th:attr="value=#{subscribe.submit}"/>
```
- `subscribe.submit` value is fetched from `application.properties`. If not defined, defaults to `SUBMIT FORM`.

---

#### **8. Iteration Over Collections**
Loop through a list and display values.

**Example:**
```html
<tr th:each="user : ${list}">
    <td>[[${user.id}]]</td>
    <td>[[${user.name}]]</td>
    <td>[[${user.address}]]</td>
</tr>
```

---

#### **9. Conditional Styling**
Use `th:classappend` for dynamic CSS classes.

**Example:**
```html
<td th:classappend="${user.id % 2 == 0} ? 'bg-success' : 'bg-warning'">[[${user.id}]]</td>
```
- Even IDs get `bg-success` class.
- Odd IDs get `bg-warning` class.

---
