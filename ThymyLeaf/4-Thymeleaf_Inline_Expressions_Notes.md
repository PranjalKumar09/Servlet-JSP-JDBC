### Thymeleaf `th:inline` and Utility Objects Overview

#### **1. `th:inline` Attribute**
- Enables inlined expressions within the HTML.
- Example:  
  ```html
  <div th:inline="text">
      Current date: [[${#dates.format(date1, 'yyyy-MM-dd HH:mm:ss')}]]
  </div>
  ```

---

#### **2. Utility Objects**
Thymeleaf provides several utility objects for various operations.

##### **a. Dates (`#dates`)**
- Format dates dynamically:
  ```html
  [[${#dates.format(date1, 'yyyy-MM-dd HH:mm:ss')}]]
  ```
- Get day of the month:
  ```html
  [[${#dates.day(date1)}]]
  ```

##### **b. Calendar (`#calendars`)**
- Provides advanced calendar-based operations.

##### **c. Numbers (`#numbers`)**
- For number formatting and operations.

---

#### **3. Inline Request Parameters**
- Dynamically include request parameters:
  ```html
  Getting request: [[${param.key}]]
  ```

---

#### **4. Links**
- Create dynamic links:
  ```html
  <a th:href="@{/path}">Link</a>
  ```

---

#### **Summary**
- **`th:inline`**: For inline expressions.  
- **Utility Objects**:  
  - `#dates` for date handling.  
  - `#calendars` for calendar operations.  
  - `#numbers` for number formatting.  
- Use `[[...]]` for dynamic inline values.
