
    Overiding of static method not possible

# **Java Interface & Inheritance - Quick Notes**

---

## **1. Interface Basics**

* An **interface** represents a **100% abstract contract** (till Java 7).
* Used to represent a **service requirement**.
* All methods in an interface are by default:

  * `public` and `abstract` (from Java 7 and below).
  * From Java 8, interfaces can also have:

    * `default` methods (with implementation)
    * `static` methods (with implementation)
    * `private` methods (helper methods, only used within interface)

```java
interface A {
    void m1(); // Implicitly public and abstract
    public void m2();
    abstract void m3();
    public abstract void m4(); // All equivalent
}
```

* `final`, `private`, `protected` are **not allowed** with interface methods.

---

## **2. Implementing Interface**

```java
interface InterfaceA {
    void interface_fn();
}

class A implements InterfaceA {
    void interface_fn() { } // ❌ Error: Must be public
}
```

> **Implementation method must be `public`** or of same/higher visibility than the interface method (`public`).

---

## **3. Interface Variables**

* All interface variables are implicitly:

  * `public static final`
* Invalid modifiers: `private`, `protected`, `transient`, `volatile`

```java
interface Interf {
    int x = 88;
}

class Test implements Interf {
    public static void main(String[] args) {
        int x = 99;
        System.out.println(x);         // 99
        System.out.println(Interf.x);  // 88
    }
}
```

---

## **4. Multiple Inheritance with Interface**

* A class can:

  * `extend` only one class
  * `implement` any number of interfaces
  * `extend` a class and `implement` multiple interfaces

```java
class A extends B implements C, D { }
```

> **Order (`extends`, then `implements`) is fixed.**

* You can implement two interfaces with the **same method signature** — implement once.
* If method signatures **conflict (e.g., return types differ)** → **Compile-time error**.

```java
interface Left { void m1(); }
interface Right { int m1(); }

abstract class Test implements Left, Right { } // ❌ Compile-time error
```

> Exception: If return types are covariant and **non-primitive object types**, it's allowed.

---

## **5. Interface Variable Conflict Example**

```java
interface Left { int x = 777; }
interface Right { int x = 888; }

class Test implements Left, Right {
    public static void main(String[] args) {
        // System.out.println(x);       // ❌ Ambiguous
        System.out.println(Left.x);    // 777
        System.out.println(Right.x);   // 888
    }
}
```

---

## **6. Marker Interfaces**

* **Interfaces with no methods** — used to **mark a class** as having a property/capability.
* Examples:

  * `Serializable`
  * `Cloneable`
  * `RandomAccess`
  * `SingleThreadModel` (deprecated)

```java
class Test implements Cloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        Test t = new Test();
        Test t1 = (Test) t.clone(); // Cloneable gives ability to clone
    }
}
```

> Must override `clone()` method (from `Object`) to use.

---

## **7. Default Methods (Java 8+)**

* Used to add new methods to interfaces without breaking existing implementations.
* Can be overridden in implementing classes.
* Example use: `Collection.stream()`

```java
interface Interf {
    default void show() {
        System.out.println("Default method");
    }
}
```

---

## **8. Static Methods in Interface**

* Can be defined with a body.
* **Not inherited** by implementing classes.
* **Call using interface name only**.
* Overiding of static mehtod not possible

```java
interface Interf {
    static void m1() {
        System.out.println("Interface static method");
    }
}

class Test implements Interf {
    public static void main(String[] args) {
        // m1();         // ❌ Error
        // Test.m1();    // ❌ Error
        Interf.m1();      // ✅ Valid
    }
}
```

* **Static + default** not allowed on the same method.
* Interface **can have static main method**.

---

## **9. Abstract Class vs Interface**

| Feature               | Abstract Class     | Interface                          |
| --------------------- | ------------------ | ---------------------------------- |
| Inheritance           | Single             | Multiple                           |
| Constructor           | Yes                | ❌ No                               |
| Instance/Static Block | Allowed            | ❌ Not allowed                      |
| Method Types          | Abstract, Concrete | Abstract, default, static, private |
| Variable Modifiers    | Any                | `public static final` only         |
| Access Modifiers      | Allowed            | Only `public`                      |

---

## **10. Miscellaneous**

* Modifier for local variables: **only `final`** is allowed.
* `<default>` class (no modifier): accessible **only within the package**.
* Inner class example:

```java
public class X {
    class Abc {
        void a() {
            int a; // valid
        }
    }
}
```

---
