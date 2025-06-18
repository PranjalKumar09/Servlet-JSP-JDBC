## 🔹 Constructor Rules and Behavior in Java

### 1. **Use of `this()` and `super()`**

* `this()` and `super()` are **constructor calls**.
* They must be used **only inside *constructors***.
* If used, they must be the **first statement** in the constructor.
* **Only one** of them can appear in a constructor.

```java
public Test() {
    super(); // ✅ valid
    // this(); ❌ Error: Already calling super()
}
```

---

### 2. **Default Superclass Constructor Call**

* If a class does **not** extend any class explicitly, it **implicitly extends `Object`**.
* Even without writing `super()`, the compiler adds it implicitly to call the `Object` class constructor.

```java
public class Test {
    public Test() {
        // Implicit: super(); -> Object class
        System.out.println("Constructor");
    }
}
```

---

### 3. **Invalid Constructor Examples**

```java
public Test() {
    super(); 
    this(); // ❌ Error: Can't call both
}
```

---

### 4. **`this` and `super` Keywords**

* Used to access:

  * `this.x`, `this.y()` → current class members.
  * `super.x`, `super.y()` → parent class members.
* Can be used **multiple times** in:

  * **Methods**
  * **Instance blocks**
  * **Constructors**
* ❌ Not allowed in **static context**.

```java
System.out.println(super.hashCode()); // ❌ Error in static area
```

---

## 🔹 Overloaded Constructors (Constructor Chaining)

```java
Test() {
    this(10);
    System.out.println("No-arg");
}

Test(int i) {
    this(10.5);
    System.out.println("int arg");
}

Test(double d) {
    System.out.println("double arg");
}
```

### Execution:

```java
new Test();     // Output: double arg → int arg → No-arg
new Test(10);   // Output: double arg → int arg
new Test('a');  // char promoted to int → Output: double arg → int arg
```

### Invalid Recursive Constructor Chaining:

```java
Test() { this(10); }
Test(int i) { this(); }
// ❌ Error: Recursive constructor invocation
```

---

## 🔹 Constructors and Inheritance

* **Constructors are not inherited.**
* Therefore, they **cannot be overridden**.

### Example:

```java
class P {
    P() {}
}
class C {
    C(int i) {}
}

C c = new C(); // ❌ Error: No default constructor in class C
```

``` java
class P{
    P(int i){}
}
class C extends P{
    C(){

    }
}
```
``` java
class C extends P{
    C(){
super();
    }
}
```

---
``` java
class P {
    public P(int i) {}  // Only constructor in P takes an int
}

class C extends P {
    // No constructor defined here
    // Compiler tries to insert: super(); ← ERROR!
}
```
**ERROR** gives here


### Proper Constructor Chaining in Inheritance

```java
class P {
    P(int i) {
        super(); // ✅ Valid, even though Object's constructor
    }
}

class C extends P {
    C() {
        super(10); // ✅ Must call a matching superclass constructor
    }
}
```

### OR

```java
class P {
    P() {}
    P(int i) {}
}

class C extends P {
    C() {
        // Implicit call to super()
    }
}
```

---

## 🔹 Constructor and Exceptions

* If a **superclass constructor throws a checked exception**, the subclass constructor:

  * Must either **declare the same or a parent exception**, OR
  * **Handle it inside the constructor.**
* `super()` must be the **first statement**, so `try-catch` before it is **not allowed**.

```java
class P {
    P() throws IOException {}
}

class C extends P {
    C() throws IOException {
        super(); // ✅ Correct
    }
}
```

```java
class C extends P {
    C() {
        try {
            super(); // ❌ Error: Not allowed, not the first statement
        } catch (IOException e) {}
    }
}
```

---

## 🔹 Static Context Restriction

* `super` and `this` **cannot** be used in a static context.

```java
public static void main(String[] args) {
    System.out.println(super.hashCode()); // ❌ Error
}
```

---

## 🔹 Recursive Method Calls vs Constructor Calls

```java
class Test {
    public static void m1() { m2(); }
    public static void m2() { m1(); }

    static { System.out.println("hello"); }
}
```

* Just defining them doesn't raise an error.
* This causes a **StackOverflowError** only if the methods are actually called.

```java
class Test {
    Test() { this(10); }
    Test(int i) { this(); }
    static { System.out.println("hello"); }
}
// ❌ Compile-time error: Recursive constructor invocation
```

---

## ✅ Summary for Oracle Java Exam:

| Concept                    | Rule                                                    |
| -------------------------- | ------------------------------------------------------- |
| `super()` / `this()`       | Only one allowed, must be first line in constructor     |
| `super` / `this` (members) | Can be used multiple times, not in static               |
| Constructor Inheritance    | Not inherited, not overridden                           |
| Constructor Chaining       | Use `this()` / `super()` carefully                      |
| Recursive Constructor      | Causes compile-time error                               |
| Exception in Constructor   | Must handle or declare if superclass constructor throws |
| Static Area                | `super` / `this` not allowed                            |

---

Let me know if you’d like this as a PDF or formatted as flashcards.
