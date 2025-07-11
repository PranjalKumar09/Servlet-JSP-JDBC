
## ✅ Java Exception Handling 

### ⚠️ Try-Catch Flow

> **Only risky code should go in `try` block**. Once an exception occurs and control goes to `catch`, the remaining `try` code is skipped.

#### Execution Flow Example:

```java
try {
  stmt-1;
  stmt-2;  // may throw
  stmt-3;
} catch (X e) {
  stmt-4;
}
stmt-5;
```

**Case 1**: No exception → `1, 2, 3, 5` (✔ Normal termination)
**Case 2**: Exception at `stmt-2`, catch matches → `1, 4, 5` (✔ Normal)
**Case 3**: Exception at `stmt-2`, catch not matched → `1`, ❌ *Abnormal termination*

---

### 🧾 Exception Object Methods (3 ways to print):

```java
e.printStackTrace();   // Full info: name + description + stack trace  
System.out.println(e); // e.toString(): name + description  
e.getMessage();        // Only description
```

---

### 🔁 Multiple Catch Blocks

```java
try {
  // risky code
} 
catch (FileNotFoundException e) {
  // fallback to local file
} 
catch (SQLException e) {
  // database fallback
} 
catch (Exception e) {
  // catch-all fallback (good practice)
}
```

* ✅ *Order matters*: More specific → general.
* ❌ `catch(Exception e)` **before** `catch(ArithmeticException e)` → *Compile-time error*.
* ❌ Same exception in multiple `catch` blocks → *Compile-time error*.

---

### 🔒 `finally` Block

* Always executes (even if exception is not caught).
* Used for **cleanup**: closing files, DB connections, etc.

#### Example 1

```java
try {
  System.out.println("try");
  int x = 10 / 0;
} catch (NullPointerException e) {
  System.out.println("catch");
} finally {
  System.out.println("finally");
}
```

🧾 Output:

```
try
finally
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

#### Example 2 – `System.exit(0)`

```java
try {
  System.out.println("try");
  System.exit(0);
} finally {
  System.out.println("finally"); // ❌ Won't execute
}
```

---

### 🔁 try-catch-finally with `return`

```java
public static int m1() {
  try {
    return 777;
  } catch (NullPointerException e) {
    return 888;
  } finally {
    return 999;
  }
}
System.out.println(m1()); // ✅ Output: 999
```

> `finally` **overrides** all previous returns.

---

### ⚖️ final vs finally vs finalize

| Keyword      | Use                      | Purpose                                   |
| ------------ | ------------------------ | ----------------------------------------- |
| `final`      | Variable, Method, Class  | Constant, cannot override/inherit         |
| `finally`    | with `try-catch-finally` | Always executes (cleanup)                 |
| `finalize()` | Method (Object class)    | Cleanup before GC (deprecated in Java 9+) |

---

### 🔁 Nested try-catch-finally

```java
try {
  stmt-1;
  stmt-2;
  try {
    stmt-3;
  } catch (X e) {
    stmt-4;
  } finally {
    stmt-5;
  }
} catch (Y e) {
  stmt-6;
} finally {
  stmt-7;
}
```

---

### ❌ Invalid Syntax

```java
catch() { }  // ❌ Not allowed (must specify exception type)
```

---



NEW KNOWLEDGE
If a catch block contains a return statement, the finally block will still execute before the method actually returns. However, after the finally block finishes, the method will return as directed by the catch, and the rest of the code after the try-catch-finally block will not execute 