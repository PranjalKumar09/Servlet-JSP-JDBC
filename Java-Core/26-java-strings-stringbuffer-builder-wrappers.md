## **Java String, StringBuffer, StringBuilder & Wrapper Classes**

### **1. `java.lang` Package**

* Only includes:

  * `String`, `StringBuilder`, `StringBuffer`
  * Wrapper classes (`Integer`, `Float`, etc.)

--- 

## **2. Object Representation & `toString()`**

```java
Test t = new Test();
System.out.println(t);              // <ClassName>@<Hexadecimal_HashCode>
System.out.println(t.toString());   // Same as above
System.out.println(Integer.toHexString(t.hashCode()));
```

---

## **3. `==` vs `.equals()`**

* `==` → Reference comparison
* `.equals()` → Content comparison (can be overridden)

### **Example:**

```java
Student s1 = new Student("Durga", 101);
Student s2 = new Student("Ravi", 102);
Student s3 = new Student("Durga", 101);

System.out.println(s1 == s2);         // false
System.out.println(s1 == s3);         // false
System.out.println(s1.equals(s2));    // false
System.out.println(s1.equals(s3));    // false
```

### **Overriding `.equals()`**

```java
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Student other = (Student) obj;
    return this.id == other.id && this.name.equals(other.name);
}
```

---

## **4. String vs StringBuffer vs StringBuilder**

### **String**

* Stored in **String Constant Pool (SCP)** if not created with `new`
* Example:

  ```java
  String s = "durga";          // SCP
  String s2 = new String("durga"); // Heap
  ```

### **StringBuffer**

* Default capacity: **16**
* Capacity formula: `(currentCapacity + 1) * 2`

  * Ex: 16 → 34 → 70...

### **Example:**

```java
StringBuffer sb = new StringBuffer("abcd");
sb.setLength(2);                   // "ab"
System.out.println(sb);           // "ab"

StringBuffer sb2 = new StringBuffer(1000);
sb2.append("abc");
sb2.trimToSize();
System.out.println(sb2.capacity()); // 3
```

### **StringBuilder**

* Used to overcome synchronization overhead of `StringBuffer`

```java
StringBuilder sb = new StringBuilder();
System.out.println(sb.capacity()); // 16
```

### String Handling Classes
Java provides three main classes for string manipulation:
- `String`: Immutable, stored in the String Constant Pool (SCP) or heap.
- `StringBuffer`: Mutable, thread-safe with synchronized methods.
- `StringBuilder`: Mutable, non-synchronized for better performance.
---

## **5. `StringBuffer` vs String Comparison**

```java
StringBuffer s1 = new StringBuffer("Durga");
StringBuffer s2 = new StringBuffer("Durga");
System.out.println(s1 == s2);           // false
System.out.println(s1.equals(s2));      // false (doesn't override equals)

String s3 = new String("Durga");
System.out.println(s1.equals(s3));      // false
System.out.println(s1.toString().equals(s3)); // true
```

---

## **6. Wrapper Classes**

```java
Integer i1 = new Integer(10);
Integer i2 = new Integer(10);

System.out.println(i1 == i2);        // false (different objects)
System.out.println(i1.equals(i2));   // true (content comparison)
```

---

## **7. String Creation Techniques**

```java
String s1 = new String();                          // Empty string, default
String s2 = new String(s1);                        // From another String
String s3 = new String("durga");                   // From literal
String s4 = new String(new StringBuffer("durga")); // From StringBuffer
char[] ch = {'d','u','r','g','a'};
String s5 = new String(ch);                        // From char[]
String s5 = new String(ch, 1, 3);                  // String(char[] value, int offset, int count) -> urg
byte[] b = {100,117,114,103,97};
String s6 = new String(b);                         // From byte[] (ASCII)
```

  ---

## **9. String Pool (SCP) vs Heap**

```java
String s1 = "durga";          // Stored in SCP
String s2 = "durga";          // Reused from SCP → s1 == s2 → true
String s3 = new String("durga"); // New object in Heap → s1 == s3 → false
```

* `intern()` returns the SCP reference of a String. If the value already exists in the String Constant Pool, it returns the existing reference; otherwise, it adds it to the pool. 

```java
String a = "hello";
String b = a;
String c = new String("hello");
String d = c.intern(); // Refers to SCP object

System.out.println(d == a); // true
System.out.println(d == c); // false
System.out.println(a == c); // false
```

### **Example:**

```java
String s = "durga";
String s1 = s.concat("Software");
String s2 = s.concat("Software");

System.out.println(s1 == s2);       // false (new objects)

String s3 = s.toLowerCase();
String s4 = s.toLowerCase();
System.out.println(s3 == s4);       // true (reused from SCP)
```

---

## **10. Key Differences Summary**

| Feature          | String           | StringBuffer  | StringBuilder    |
| ---------------- | ---------------- | ------------- | ---------------- |
| Mutability       | Immutable        | Mutable       | Mutable          |
| Thread-safe      | Yes (internally) | Yes           | No               |
| Performance      | Fast (read)      | Slower (sync) | Faster (no sync) |
| Default Capacity | N/A              | 16            | 16               |

---

** `==` vs `.equals()`**

#### ✅ Additional Points:

* `.equals()` **never gives an error**, even across unrelated types (e.g., `StringBuffer` and `String`).
* Comparison between `StringBuffer` and `String` using `==` gives **compile-time error** (incompatible types).

```java
StringBuffer s1 = new StringBuffer("Durga");
String s2 = new String("Durga");

// System.out.println(s1 == s2); // ❌ Compile-time error
System.out.println(s1.equals(s2));                    // false
System.out.println((s1.toString()).equals(s2));       // true
```


* ✅ Return types
* ✅ Method behavior
* ✅ Whether **overloading** is possible
* ✅ Notes on mutability (modifies or not)

---

## ✅ **Important String / StringBuffer / StringBuilder Methods (With Return Types & Overloading)**

---

### ### 🔹 **String Methods (Immutable)**

| Method                          | Return Type | Description                         | Overloaded |
| ------------------------------- | ----------- | ----------------------------------- | ---------- |
| `charAt(int index)`             | `char`      | Returns char at given index         | ❌          |
| `concat(String s)`              | `String`    | Concatenates and returns new String | ❌          |
| `equals(Object obj)`            | `boolean`   | Content comparison                  | ❌          |
| `equalsIgnoreCase(String s)`    | `boolean`   | Case-insensitive comparison         | ❌          |
| `substring(int start)`          | `String`    | Substring from start to end         | ✅          |
| `substring(int start, int end)` | `String`    | Substring from start to end-1       | ✅          |
| `length()`                      | `int`       | Number of characters                | ❌          |
| `replace(char old, char new)`   | `String`    | Replaces all `old` with `new`       | ❌          |
| `toLowerCase()`                 | `String`    | Lowercase version                   | ❌          |
| `toUpperCase()`                 | `String`    | Uppercase version                   | ❌          |
| `trim()`                        | `String`    | Removes leading/trailing spaces     | ❌          |
| `indexOf(char c)`               | `int`       | First index of character            | ✅          |
| `indexOf(char c, int startIdex)`               | `int`       | First index of character            | ✅          |
| `lastIndexOf(char c)`           | `int`       | Last index of character             | ✅          |
| `contains(CharSequence s)` | `boolean`   | Checks if substring exists in string | ❌         |
---

### 🔹 **StringBuffer / StringBuilder Methods (Mutable)**

✅ All **modify the original object** and
✅ Most return `StringBuffer` / `StringBuilder` (supports **method chaining**)
✅ Most are **overloaded** (multiple input types)

| Method                            | Return Type                      | Description                       | Overloaded                                          |
| --------------------------------- | -------------------------------- | --------------------------------- | --------------------------------------------------- |
| `append(...)`                     | `StringBuffer` / `StringBuilder` | Adds data at end, modifies buffer | ✅ (String, int, float, boolean, char\[], Object...) |
| `insert(int index, ...)`          | `StringBuffer` / `StringBuilder` | Inserts at given index            | ✅ (String, int, etc.)                               |
| `delete(int start, int end)`      | `StringBuffer` / `StringBuilder` | Deletes from start to end-1       | ❌                                                   |
| `deleteCharAt(int index)`         | `StringBuffer` / `StringBuilder` | Deletes char at index             | ❌                                                   |
| `reverse()`                       | `StringBuffer` / `StringBuilder` | Reverses contents                 | ❌                                                   |
| `setCharAt(int index, char ch)`   | `void`                           | Changes char at index             | ❌                                                   |
| `setLength(int newLength)`        | `void`                           | Truncates or extends length       | ❌                                                   |
| `trimToSize()`                    | `void`                           | Removes unused capacity           | ❌                                                   |
| `ensureCapacity(int minCapacity)` | `void`                           | Ensures minimum buffer size       | ❌                                                   |
| `capacity()`                      | `int`                            | Returns current capacity          | ❌                                                   |
| `length()`                        | `int`                            | Returns current length            | ❌                                                   |
| `toString()`                      | `String`                         | Converts to String                | ❌                                                   |

---

> ⚠️ Methods like `insert`, `delete`, `setCharAt`, and others that use indexes will throw `StringIndexOutOfBoundsException` if the provided index is invalid.
---

## 🔹 Multiline Strings & Escape Sequences

Java supports **text blocks** using triple double quotes (`"""`), which preserve formatting and line breaks.

```java
String a = """
    hello
    "world"
    """;
System.out.println(a);
```
**Output**
```
hello
"world"
```


**Common Escape Sequences:**
  - `\t` → tab
  - `\n` → newline
  - `\"` → double quote
  - `\\` → backslash
  - `\s` → space (only in text blocks)
  - `\` (end of line) → prevent automatic line break
  - `\"""` → escape triple quotes in text blocks


NEW KNOWLEDGE
  even toString new method

  String str = " ";
  str.trim();
  S.o.p(str.equals("")+" " +str.empty()); /// false false

