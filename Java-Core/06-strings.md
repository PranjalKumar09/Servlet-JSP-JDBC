### Enhanced Notes on String Handling in Java

---

### **Classes for String Handling**
Java provides **two main classes** for handling Strings:
1. **String** (Immutable)
2. **StringBuffer** (Mutable)

#### **Immutable Nature of String**
- Strings are immutable in Java. Modifying a String creates a new object in memory.
- Example:
  ```java
  String city = "Bhopal";
  city = "Indore";  // A new object is created for "Indore".
  ```

---

### **String Initialization**
1. **Using `new` Keyword**:
   ```java
   String s1 = new String("Sky");
   String s2 = new String("Sky");
   ```
   Creates **two different objects** in the heap.

2. **Using String Literals**:
   ```java
   String s3 = "Sky";
   String s4 = "Sky";
   ```
   Both `s3` and `s4` point to the **same object** in the string pool.

#### **Comparison of References**
- **Heap Objects**:
  ```java
  String s1 = new String("Sky");
  String s2 = new String("Sky");
  System.out.println(s1 == s2);  // false (different objects)
  ```
- **String Pool Objects**:
  ```java
  String s3 = "Sky";
  String s4 = "Sky";
  System.out.println(s3 == s4);  // true (same object in string pool)
  ```

---

### **Constructors of `String` Class**
1. **Default Constructor**:
   ```java
   String s = new String();
   ```

2. **String Constructor**:
   ```java
   String s = new String("Bhopal");
   ```

3. **From Character Array**:
   ```java
   char[] arr = { 'H', 'e', 'l', 'l', 'o' };
   String s = new String(arr);         // Output: "Hello"
   ```

4. **From Character Array with Substring**:
   ```java
   String s = new String(arr, 0, 4);   // Output: "Hell"
   ```

---

### **Methods of `String` Class**

#### **Comparison Methods**
- **`equals(Object)`**:
  Compares string **contents** for equality.
  ```java
  String s1 = "Sky";
  String s2 = "Sky";
  System.out.println(s1.equals(s2));  // true
  ```

- **`equalsIgnoreCase(String)`**:
  Ignores case while comparing.
  ```java
  String s1 = "sky";
  String s2 = "Sky";
  System.out.println(s1.equalsIgnoreCase(s2));  // true
  ```

- **`compareTo(String)`**:
  Compares two strings lexicographically.
  ```java
  System.out.println("Sky".compareTo("Sky"));  // 0
  System.out.println("Sky".compareTo("Spy"));  // -3
  ```

- **`compareToIgnoreCase(String)`**:
  Ignores case during lexicographical comparison.
  ```java
  System.out.println("sky".compareToIgnoreCase("Sky"));  // 0
  ```

#### **Character and Substring Methods**
- **`charAt(int index)`**:
  Returns character at the specified index.
  ```java
  System.out.println("Bhopal".charAt(3));  // Output: 'p'
  ```

- **`substring(int start, int end)`**:
  Extracts substring from `start` to `end-1`.
  ```java
  System.out.println("Bhopal".substring(0, 3));  // Output: "Bho"
  ```

- **`substring(int start)`**:
  Returns substring from `start` to end of the string.
  ```java
  System.out.println("Bhopal".substring(3));  // Output: "pal"
  ```

#### **Index Methods**
- **`indexOf(char)`**:
  Returns the index of the first occurrence of the character.
  ```java
  System.out.println("Bhopal".indexOf('p'));  // Output: 3
  ```

- **`indexOf(String)`**:
  Finds the starting index of a substring.
  ```java
  System.out.println("Bhopal".indexOf("hop"));  // Output: 1
  ```

- **`lastIndexOf(String)`**:
  Returns the last occurrence of a substring.
  ```java
  System.out.println("Bhopal".lastIndexOf("p"));  // Output: 3
  ```

#### **Case Conversion**
- **`toUpperCase()`**:
  Converts string to uppercase.
  ```java
  System.out.println("Bhopal".toUpperCase());  // Output: "BHOPAL"
  ```

- **`toLowerCase()`**:
  Converts string to lowercase.
  ```java
  System.out.println("Bhopal".toLowerCase());  // Output: "bhopal"
  ```

#### **Testing String Start/End**
- **`startsWith(String)`**:
  Tests if the string starts with a prefix.
  ```java
  System.out.println("Bhopal".startsWith("Bho"));  // true
  ```

- **`endsWith(String)`**:
  Tests if the string ends with a suffix.
  ```java
  System.out.println("Bhopal".endsWith("pal"));  // true
  ```

#### **Utility Methods**
- **`length()`**:
  Returns the length of the string.
  ```java
  System.out.println("Bhopal".length());  // Output: 6
  ```

- **`getChars(int srcBegin, int srcEnd, char[] dest, int destBegin)`**:
  Copies characters from the string into a character array.
  ```java
  char[] arr = new char[4];
  "Hello".getChars(0, 4, arr, 0);
  System.out.println(arr);  // Output: "Hell"
  ```

---

### **Immutability Example**

```java
public class StringDemo {
    public static void main(String[] args) {
        String city = "Bhopal";
        System.out.println(city);  // Output: Bhopal
        city = "Indore";           // New object is created
        System.out.println(city);  // Output: Indore
    }
}
```

---

### **Key Points**
1. **String Pool**:
   - Strings initialized using literals are stored in the pool and reused.
   - Explicit `new String()` creates separate objects in the heap.

2. **Immutable Nature**:
   - Once created, string content cannot be modified.
   - Modifying a string creates a new object.

3. **Method Behavior**:
   - Methods like `toUpperCase()` and `substring()` return **new String objects**.

---

### **Practice Problems**
1. Write a program to reverse a string using `charAt()` and `length()`.
2. Find all occurrences of a character in a string using `indexOf()`.
3. Compare two strings ignoring case and display the result.








    ### Enhanced Notes on `StringBuffer` in Java

---

### **Key Characteristics of `StringBuffer`**

1. **Mutability**:
   - Unlike `String`, objects of `StringBuffer` are mutable, meaning their content can be modified without creating new objects.
   - Suitable for scenarios where frequent modifications to the string are required, such as updating employee details or dynamic text processing.

2. **Package**:
   - `StringBuffer` is part of the **`java.lang`** package.

3. **Key Difference**:
   - `StringBuffer` provides the same methods as `String` but allows modifications.

---

### **Constructors of `StringBuffer`**

1. **Default Constructor**:
   ```java
   StringBuffer sb = new StringBuffer();
   ```
   - Creates a `StringBuffer` object with a default capacity of **16 characters** initialized with `'\0'`.

2. **With Initial Capacity**:
   ```java
   StringBuffer sb = new StringBuffer(50);
   ```
   - Creates a `StringBuffer` with a specified capacity.

3. **With Initial Content**:
   ```java
   StringBuffer sb = new StringBuffer("Hello");
   ```
   - Initializes the object with the given string and **appends 16 null characters**.

---

### **Methods of `StringBuffer`**

#### **Capacity and Size Management**
1. **`capacity()`**:
   - Returns the current capacity of the `StringBuffer`.
   ```java
   StringBuffer sb = new StringBuffer("Hello");
   System.out.println(sb.capacity());  // Output: 21 (5 for "Hello" + 16 reserved)
   ```

2. **`ensureCapacity(int)`**:
   - Increases the capacity if the current capacity is less than the argument.
   ```java
   StringBuffer sb = new StringBuffer();
   sb.ensureCapacity(50);
   System.out.println(sb.capacity());  // Output: 50
   ```

#### **Appending Strings**
- **`append(String)`**:
   - Adds a string (or any data type) to the end of the `StringBuffer`.
   ```java
   StringBuffer sb = new StringBuffer("India");
   sb.append(" is my country");
   System.out.println(sb);  // Output: "India is my country"
   ```

#### **Reversing Content**
- **`reverse()`**:
   - Reverses the characters in the `StringBuffer`.
   ```java
   StringBuffer sb = new StringBuffer("Hello");
   sb.reverse();
   System.out.println(sb);  // Output: "olleH"
   ```

#### **Replacing Substrings**
- **`replace(int start, int end, String)`**:
   - Replaces the characters from `start` to `end-1` with the specified string.
   ```java
   StringBuffer sb = new StringBuffer("Hello World");
   sb.replace(6, 11, "India");
   System.out.println(sb);  // Output: "Hello India"
   ```

#### **Inserting Content**
- **`insert(int offset, String)`**:
   - Inserts the given string at the specified position.
   ```java
   StringBuffer sb = new StringBuffer("Java");
   sb.insert(4, " Programming");
   System.out.println(sb);  // Output: "Java Programming"
   ```

---

### **Comparison: `String` vs `StringBuffer`**

| Feature               | `String`            | `StringBuffer`        |
|-----------------------|---------------------|-----------------------|
| **Mutability**         | Immutable           | Mutable               |
| **Performance**        | Slower for frequent changes | Faster for frequent changes |
| **Thread Safety**      | Not thread-safe     | Thread-safe           |
| **Usage Scenario**     | Fixed content       | Dynamic/modifiable content |

---

### **Examples**

#### **String vs StringBuffer**
```java
public class StringBufferDemo {
    public static void main(String[] args) {
        // String (Immutable)
        String s = "Hello";
        s = s + " World";
        System.out.println(s);  // Output: "Hello World"

        // StringBuffer (Mutable)
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World");
        System.out.println(sb);  // Output: "Hello World"
    }
}
```

#### **Dynamic Text Updates**
```java
public class EmployeeSalaryUpdate {
    public static void main(String[] args) {
        StringBuffer salaryDetails = new StringBuffer("Current Salary: $5000");
        salaryDetails.replace(16, 20, "$6000");
        System.out.println(salaryDetails);  // Output: "Current Salary: $6000"
    }
}
```
