
## ✅ Exception Handling – Super Concise Notes

### 🔹 What is Exception Handling?

> Handling **unwanted or unexpected events** (exceptions) that **disrupt the normal flow** of a program.

* Goal: **Graceful** or **normal** termination.
* May include **alternative code** to continue execution.

---

### 🔹 Java Runtime Stack Mechanism

* Every Java program has **one main thread by default**.
* **Stack follows LIFO**:

  * `main()` is at bottom.
  * Methods are added (pushed) on call, and removed (popped) after execution.

---

### 🔹 Default Exception Handling

```java
public static void main(String[] args) {
    doStuff();
}
public static void doStuff() {
    doMore();
    System.out.println(10 / 0);  // ArithmeticException
}
public static void doMore() {
    System.out.println("Hello");
}
```

**Output:**

```
Hello
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at Test.doStuff(Test.java:7)
	at Test.main(Test.java:3)
```

* JVM uses **Default Exception Handler**.
* Applies to both **static** and **non-static** methods.

---

### 🔹 Root Cause of Exceptions

* **Programmer mistakes** → exceptions → mostly **recoverable**.
* **System resource issues** → errors → **non-recoverable**.
* **Compiler errors** → must fix → **non-recoverable** at runtime.

---

### 🔹 Exception Class Hierarchy

[](!Java-Core/Exception_class.png)
#### Common Checked Exceptions:

* `IOException`

  * `FileNotFoundException`
  * `EOFException`
  * `InterruptedIOException`
* `SQLException`, `InterruptedException`, **custom exceptions**
  (e.g., `PenNotWorkingException`)

#### Common Unchecked Exceptions:

* `NullPointerException`
* `ArithmeticException` (e.g., divide by zero)
* `ArrayIndexOutOfBoundsException`

---

### 🔹 Types of Exceptions

#### ✅ **Checked Exceptions**

* Must handle with `try-catch` or `throws`.
* Enforced at compile-time (**Handle or Declare Rule**).

#### ✅ **Unchecked Exceptions**

* Subclasses of `RuntimeException` or `Error`.
* Not checked at compile-time.

---

### 🔹 Fully Checked vs Partially Checked Exceptions

| Type                  | Parent                | Child                          | Must Handle? |
| --------------------- | --------------------- | ------------------------------ | ------------ |
| **Fully Checked**     | Checked (`Exception`) | Checked                        | ✅ Yes        |
| **Partially Checked** | Checked (`Exception`) | Unchecked (`RuntimeException`) | ❌ No         |

**Examples:**

```java
// Fully Checked
class ParentException extends Exception {}
class ChildException extends ParentException {}

// Partially Checked
class ParentException extends Exception {}
class ChildException extends RuntimeException {}
```

---

### 🔹 Exception Syntax

```java
try {
    // Risky code (may throw exception)
} catch (Exception e) {
    // Handling code
}
```

* **Risky Code**: Any code that might throw an exception.



---

### 🧠 Final Summary

| Term                    | Example                                       | Checked? | Handled Required? |
| ----------------------- | --------------------------------------------- | -------- | ----------------- |
| **Checked Exception**   | `IOException`, custom exceptions              | ✅ Yes    | ✅ Yes             |
| **Unchecked Exception** | `NullPointerException`, `ArithmeticException` | ❌ No     | ❌ No              |
| **Error**               | `OutOfMemoryError`, `StackOverflowError`      | ❌ No     | ❌ No              |
| **Compiler Error**      | Syntax/type errors                            | ❌ No     | ✅ Must fix        |

    Object is checked ? no its not aplicable for other than exception class
-