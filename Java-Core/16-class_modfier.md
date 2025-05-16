# **Java Class & Member Modifiers**

### **1. Class Modifiers (Allowed - 5)**

* `public`
* **default** (no modifier)
* `abstract`
* `final`
* `strictfp`

> Note: **`private`** is **not allowed** as a top-level class modifier.

### **2. Inner Class Modifiers (Allowed - 8)**

* All class modifiers +
* `private`
* `protected`
* `static`

---

# **Final Modifier**

### **Final Class**

* Cannot be extended.
* Classes are made final when data members or methods are sensitive and should not be altered
* it can itself inherit from other classes
* Example:

  ```java
  final class A {}
  class B extends A {} // Compilation Error
  ```

### **Final Method**

* Cannot be overridden.
* Example:

  ```java
  class P {
      public final void marry() {
          System.out.println("P");
      }
  }

  class C extends P {
      public void marry() {} // Compilation Error
  }
  ```

> Final method can't be abstract (conflicting behavior).

---

# **Abstract Keyword**

### **Abstract Class**

* Can contain:

  * Abstract methods (0 or more)
  * Concrete methods
* Cannot be instantiated however, references can be created 
* Example:

  ```java
  abstract class Vehicle {
      abstract int wheel(); // Declaration only
  }

  class Bus extends Vehicle {
      int wheel() {
          return 6;
      }
  }
  ```

### **Abstract Method**

* Declaration only (no body).
* Must be overridden by concrete subclasses.
* Syntax:

  ```java
  public abstract void run();
  ```

### **Invalid with Abstract:**

* `final`, `static`, `private`, `synchronized`, `strictfp`

> If a class has **at least one abstract method**, the class must be declared `abstract`.



---

## 🔶 **Abstract Class with Partial Implementation**

```java
abstract class P {
    public abstract void m1();
    public abstract void m2();
}

class C extends P {
    public void m1() {} // ❌ Error: m2() not implemented
}

abstract class C1 extends P {
    public void m1(); // ✅ Valid: still abstract due to unimplemented m2()
}

class C3 extends C1 {
    public void m2() {} // ✅ Must implement all remaining abstract methods
}
```
- Any class inheriting an abstract method must override it, or the derived class itself must be declared abstract.
> ✅ **Abstract class** can provide partial implementation.
> ❌ **Concrete subclass** must implement **all** abstract methods.

---

## 🔶 **Accessing `protected` Outside Package**

* `protected` = `default` + accessible in **child class (via inheritance)** even if **outside package**

### ✅ Valid Access:

```java
package pack2;
import pack1.A;

class B extends A {}

class D extends B {
    public static void main(String[] args) {
        D d = new D();
        d.m1(); // ✅ Valid: accessed via subclass reference
    }
}

class E extends D {
    public static void main(String[] args) {
        E e = new E();
        e.m1(); // ✅ Valid
    }
}
```

### ❌ Invalid Access:

```java
// pack2
B b = new B();
b.m1(); // ❌ Error: protected member accessed via parent reference outside package
```

> ✅ **Only accessible in child** and only via **child reference**
> ❌ **Not accessible via parent reference**, even in a subclass

---

# **Interfaces**

* Methods are implicitly: `public abstract`
* A class implements an interface, not extends.
* Must implement **all** methods unless declared `abstract`.

```java
interface I {
    void m1();
    void m2();
}

abstract class P implements I {
    public void m1() {} // m2() still abstract
}

class C extends P {
    public void m2() {}
}
```

---

# **strictfp Modifier**

* Applicable to: **class** and **method**
* Ensures **platform-independent** floating-point calculations (e.g., `10.0 / 3`)
* Example:

  ```java
  strictfp class A {
      strictfp void m1() {
          System.out.println(10.0 / 3);
      }
  }
  ```

---

# **Access Modifiers Summary**

| Modifier    | Class | Package | Subclass | World |
| ----------- | ----- | ------- | -------- | ----- |
| `public`    | ✅     | ✅       | ✅        | ✅     |
| `protected` | ❌     | ✅       | ✅        | ❌     |
| default     | ❌     | ✅       | ❌        | ❌     |
| `private`   | ❌     | ❌       | ❌        | ❌     |

### **Protected Modifier Deep Dive**

* Within **same package**: accessible anywhere (child or non-child).
* In **different package**: accessible **only in subclass** using **subclass reference**.

```java
// pack1
package pack1;
public class A {
    protected void m1() {
        System.out.println("A::m1()");
    }
}

class C extends A {
    public static void main(String[] args) {
        A a = new A();
        a.m1(); // Valid

        C c = new C();
        c.m1(); // Valid

        A a1 = new C();
        a1.m1(); // Valid
    }
}
```

```java
// pack2
package pack2;
import pack1.A;

class B extends A {
    public static void main(String[] args) {
        D d = new D();
        d.m1(); // Valid

        E e = new E();
        e.m1(); // Valid
    }
}

class D extends B {}
class E extends D {}
```

> From **outside package**, `protected` members are accessible only through **subclass reference**, not via parent reference (even in child class).

---

# **Object Class**

* Defined in `java.lang.Object`
* Contains \~12 methods.
* `clone()` is `protected`.
* Can be accessed in a subclass via inheritance or overridden.

---

# **Quick Recap: True or False**

| Statement                                    | Validity |
| -------------------------------------------- | -------- |
| `final` class can contain `abstract` methods | ❌ False  |
| `abstract` class can contain `final` methods | ✅ True   |

---

