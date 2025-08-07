## ☄️ `throw` vs `throws`

| Keyword  | Purpose                                                              | Usage                                     |
| -------- | -------------------------------------------------------------------- | ----------------------------------------- |
| `throw`  | Manually hand over a created exception object to the JVM             | `throw new ExceptionType();`              |
| `throws` | Declare exceptions that a method may throw (delegate responsibility) | `public void m() throws ExceptionType {}` |

---

## ✅ Example – `throws` for Delegation

```java
public class Test {
    public static void main(String[] args) throws InterruptedException {
        doStuff();
    }
    public static void doStuff() {
        doMoreStuff(); // error propagates here if not handled or declared
    }
    public static void doMoreStuff() throws InterruptedException {
        Thread.sleep(3000); // checked exception must be declared or handled
    }
}
```

🔹 If not declared using `throws`, this results in **"unreported exception"** compile-time error.

---

## ✅ 2 Ways to Handle Checked Exceptions

1. **Handle directly** with `try-catch`
2. **Declare** using `throws` to delegate

---

## ⚙️ Custom Exceptions

```java
class MyCustomCheckedException extends Exception { }     // checked
class MyCustomUncheckedException extends RuntimeException { }  // unchecked (recommended)
```

🧠 Best practice: prefer unchecked (`RuntimeException`) for custom exceptions unless it's part of an API contract.

---

## 🔁 Rethrowing Exceptions

```java
try {
    System.out.println(10 / 0);
} catch (ArithmeticException e) {
    throw new NullPointerException("Converted exception");
}
```

🔸 Used to convert one type of exception into another.

---

## 🔍 catch Block Parameters

* Can catch any subclass of `Throwable`:

```java
catch (Throwable e) { }
```

✅ Valid: `Exception`, `Error`, `RuntimeException`, `Throwable`
❌ Invalid: `catch(String e) {}` – not a subclass of `Throwable`

---

## 🔥 Special Cases

### 1. `main()` Can Declare:

```java
public static void main(String[] args) throws Throwable // ✅
public static void main(String[] args) throws Exception // ✅
public static void main(String[] args) throws Error     // ✅
public static void main(String[] args) throws ArithmeticException // ✅
public static void main(String[] args) throws String    // ❌ Invalid
```

---

### 2. Valid Class Extensions

```java
class A extends Throwable { }        // ✅
class B extends Exception { }        // ✅
class C extends Error { }            // ✅
class D extends RuntimeException { } // ✅
```

---

## 📚 Example – Multiple Catch with RuntimeException

```java
ArrayList<String> list = new ArrayList<>();
try {
    while (true) {
        list.add("MyString"); // eventually throws OutOfMemoryError
    }
} catch (RuntimeException e) {
    System.out.println("Runtime");
} catch (Exception e) {
    System.out.println("Exception");
}
System.out.println("end");
```

🧠 `OutOfMemoryError` is **not caught** by `Exception` or `RuntimeException`. It’s a subclass of `Error`.

---

## 🧪 Miscellaneous

* `String[] s = new String[1]; s[0].concat("text");` → `NullPointerException`
* `"abc".substring(2, 6);` → `StringIndexOutOfBoundsException`
* `throw 1 > 2 ? new Exception() : new RuntimeException();` – valid ternary throw

---

## 📌 Summary

* Use `throw` to manually raise an exception.
* Use `throws` to declare that a method might throw checked exceptions.
* Checked exceptions → must be handled or declared (recoverable).
* Unchecked exceptions (`RuntimeException`) → optional to catch (logic errors).
* All exceptions (even unchecked) can technically be caught.
* Main method can throw any subclass of `Throwable`.
* Custom exceptions can extend `Exception` (checked) or `RuntimeException` (unchecked – preferred).

---
``` java
void readCard() throws Exception{
    S.o.p("Reading");
}

void checkCard() throws RuntimeException{
    S.o.p("Checking");
}
public static void main(String []args){
    Test t = new Test();
    t.checkCard();
    t.readCard(); // CE
}
```