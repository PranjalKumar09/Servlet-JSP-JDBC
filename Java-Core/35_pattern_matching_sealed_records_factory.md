## 🧠 **Core Java Concepts – Oracle Exam Prep**

### 🔹 **Object Creation & Memory**

* When an object is created using `new`, **memory is allocated** on the **heap**.
* `this` is a **recursive reference**

---

### 🔹 **Local Variable Type Inference (`var`)**

* `var` allows the compiler to infer the type from the context.
* Example:

  ```java
  var str = "hello";      // inferred as String
  var no = param;         // inferred from param type (e.g., int)
  ```
* Downsides: **reduces readability**, especially with complex types.

---
* **Limitations of `var`**:
  * Type is fixed at compile-time and **cannot be changed at runtime**
  * Cannot be used for **instance variables** (only local variables)
  * Example:
    ```java
    var x = 10;   // Compiler infers 'int'
    x = "hello";  // ❌ Compile error (cannot change from int to String)
    
    class Test {
        var y = 20;  // ❌ Compile error (cannot use 'var' for instance variables)
    }
    ```

### 🔹 **Restricting Reassignment with `final`**

To avoid accidental reassignment of method parameters:

```java
public void setId(final int id) {
    this.id = id;
}
```

* `final` ensures `id` cannot be reassigned inside the method.

---

## 📦 **Java Varargs vs Arrays**

### ✅ **Similarities**

* `Student... s` is **syntactic sugar** for `Student[] s`.
* At runtime, both are treated as `Student[]`.

### ⚠️ **Differences**

| Feature     | Varargs (`...`)             | Arrays (`[]`)                        |
| ----------- | --------------------------- | ------------------------------------ |
| Declaration | `Student... s`              | `Student[] s`                        |
| Method Call | `print(s1, s2, s3)`         | `print(new Student[]{s1, s2, s3})`   |
| Flexibility | Accepts 0 or more arguments | Fixed or explicitly defined elements |

### ❌ **Overloading Limitation**

You **cannot overload** a method by switching between varargs and array:

```java
void test(Student[] s) { }
void test(Student... s) { } // ❌ Compile-time error
```

* Reason: Both compile to `test(Student[])`.

### ✅ **Use Cases**

* Use **varargs** for flexible number of parameters.
* Use **arrays** when working with a collection explicitly.

---

### 🔸 **Example of Varargs Use**

```java
MessageFormat.format("From {0} to {1}", "A", "B");
```

* `format()` method accepts varargs.

---

## 🚦 **Immutability & Thread Safety**

* **Immutable objects** are **thread-safe** because their state cannot change after construction.

---

## 📘 **Enums in Java**

* Enums define a **fixed set of constants**.
* Enum instances are **implicitly `public static final`**.

```java
enum Temperature {
    HOT("THIS IS HOT");

    private final String message;
    Temperature(String msg) { this.message = msg; }

    public String serve() { return message; }
}
```

* Enum features:

  * Can include fields, methods, and constructors.
  * Each enum has:

    * `name()` – returns the name of the enum constant.
    * `ordinal()` – returns the position (starting from 0).

---

## 💾 **Java Memory Allocation**

| Memory Area | Description                                                                                            |
| ----------- | ------------------------------------------------------------------------------------------------------ |
| **Stack**   | Per-thread memory. Stores **primitive values** and **object references** (not the objects themselves). |
| **Heap**    | Shared memory area. Stores **actual objects** and **class instances**. Accessible by all threads.      |

### ➕ Additional Note:

* **Parameter passing** copies values:

  * **Primitive types** → value is copied.
  * **Object references** → reference is copied, not the object itself.

---

## 🔧 **Common Methods in `Object` Class**

| Method                                            | Description                                                      |
| ------------------------------------------------- | ---------------------------------------------------------------- |
| `protected Object clone()`                        | Creates and returns a copy. Throws `CloneNotSupportedException`. |
| `public boolean equals(Object obj)`               | Compares object equality.                                        |
| `public final Class<?> getClass()`                | Gets runtime class of the object.                                |
| `public int hashCode()`                           | Returns object’s hash code.                                      |
| `public String toString()`                        | Returns string representation.                                   |
| `public final void wait()`                        | Waits for notification.                                          |
| `public final void wait(long timeout)`            | Waits for specified time.                                        |
| `public final void wait(long timeout, int nanos)` | Waits for specified time and nanos.                              |
| `public final void notify()`                      | Wakes up a single thread waiting on this object's monitor.       |
| `public final void notifyAll()`                   | Wakes up all threads waiting on this object's monitor.           |

---