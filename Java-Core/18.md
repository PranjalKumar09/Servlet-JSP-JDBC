
- Compiler always checks syntax jvm will always runs code
- Based on runtime object 
- jvm responsible late binding

## **Core OOP Concepts**

### **Abstraction**

* Hides implementation details, shows only functionality.
* Promotes **data hiding** + **security**.

### **Encapsulation**

* Combines **data** and **behavior** in a single unit.
* Achieved via classes.
* Ensures data hiding, abstraction, security, and better performance (especially for large codebases).

### **Inheritance**

* Enables **code reusability**.
* Implements **IS-A** relationship via `extends`.
* Child inherits all accessible methods from the parent.

### **IS-A (Inheritance)**

* `Child extends Parent`
* Child object can access methods of both parent and child.

  ```java
  Parent p = new Child(); // Valid
  p.parentMethod(); // Allowed, here child version will run
  p.childMethod(); // Compile-time error

  ```
* **Compiler checks reference type**, not object type.
* **JVM uses runtime object**, not reference type.

---

## **Object Hierarchy**

```text
Object
 ├─ String
 ├─ Number
 │   ├─ Byte
 │   └─ Integer
 └─ Throwable
     ├─ Exception
     └─ Error
```

---

## **Interfaces & Default Methods (Diamond Problem)**

```java
interface Left {
    default void m1() { System.out.println("Left default"); }
}

interface Right {
    default void m1() { System.out.println("Right default"); }
}

class Test implements Left, Right {
    public void m1() {
        Left.super.m1(); // Required to resolve ambiguity
    }
}
```

* **Cyclic inheritance** is not allowed in Java.

---

## **Has-A (Composition / Aggregation)**

* No keyword; implemented via object creation (`new`).
* Promotes **code reusability**.

**Example:**

```text
Person IS-A Employee
Employee HAS-A Car

- IS-A: Person can exist without being an Employee.
- HAS-A: Car can't exist without Employee.
```

---

## **Method Signature**

* Includes method **name** + **parameter types** (not return type).

  ```java
  void m1(); // Valid
  int m1();  // Invalid - duplicate signature
  ```

---

## **Method Overloading (Compile-Time Polymorphism)**

* Same method name, different parameter types/order or in number.

  ```java
  void m1(int);
  void m1(double);
  ```
* Return type doesn’t matter.
* Resolved at **compile time** (early binding) or static binding
* **Not possible in C language**.

### **Type Promotion Order** (for arguments):

`char → int → long → float → double`

**Example:**

```java
void m1(int i) {}
m1('a'); // Valid due to type promotion
```

---

## **Ambiguity in Overloading**

```java
void m1(int i, float f) {}
void m1(float f, int i) {}

m1(10, 10); // Compile-time error: ambiguous
```

* Java does **not resolve left-to-right or right-to-left** — ambiguity leads to error.

---

## **null Argument Handling**

```java
object -> String
       -> StringBuffer

// Passing null:
method(String)
method(StringBuffer)
call with null => Ambiguous (Error)
```

---

## **Method Overriding (Runtime Polymorphism / Dynamic Binding)**

* Method in subclass overrides superclass method with **same signature**.
* **JVM chooses method** at runtime based on the actual object.
* If child not satisfied with parent method it can redefine it
* Late binding

```java
Parent p = new Child();
p.m1(); // Executes Child's m1() if overridden
```

### **Overriding Rules**

1. **Same method signature**

2. **Return type** must be:

   * Same (Java 1.4 and earlier)

   * **Covariant** (Java 1.5+): Subclass return type allowed

     ```java
     class P { Object m1(); }
     class C extends P { String m1(); } // Valid
     ```

   * Not allowed for **primitive type changes**

     ```java
     class P { double m1(); }
     class C extends P { int m1(); } // Invalid
     ```

3. **Access modifiers**:

   * Same or more permissive
   * E.g., `protected` → `public` is valid

4. **Private methods**:

   * Not inherited, hence not overridden.

   ```java
   class P { private void m1(); }
   class C extends P { private void m1(); } // New method, not override
   ```

5. **Other Modifiers**:

   * `final` → `non-final`: ❌ Invalid
   * `non-final` → `final`: ✅ Valid
   * `abstract` ↔ `non-abstract`: ✅ Valid both ways
   * `synchronized`, `native`, `strictfp`: Optional in override

---


 -reference- = new --object--
       