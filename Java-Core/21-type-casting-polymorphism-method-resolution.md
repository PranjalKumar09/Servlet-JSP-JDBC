## ✅ Type Casting in Java

### 📌 General Rules

#### Rule 1 – **Compile Time (Type Compatibility)**

```java
A b = (C) d;
```

* **C** and **d** must be compatible:
  ➤ Either same type,
  ➤ Parent ↔ Child relationship
* If incompatible → **Compile-Time Error: inconvertible types**

#### Rule 2 – **Assignment Without Cast**

```java
A b = c;
```

* **c** must be same as **A** or a subtype
* Else → **Compile-Time Error: inconvertible types**

#### Rule 3 – **Runtime (JVM Type Check)**

```java
A b = (C) d;
```

* If actual object is not compatible with **C** →
  ➤ **Runtime Exception: ClassCastException (CCE)**

---

### 🚫 Type Casting Error Example

```java
Object o = new String("durga");
StringBuffer sb = (StringBuffer) o;
```

✅ Compiles (Object ↔ Any type)
❌ Fails at runtime: **CCE** → `String` cannot be cast to `StringBuffer`

---

### ✅ Valid Type Casting Example

```java
Integer i = new Integer(10);
Number n = (Number) i;
Object o = (Object) i;
```

* One object, three reference types: `Integer`, `Number`, and `Object`
* Type casting **does not create new object**
* All references point to the **same object**
* Internal object type remains `Integer`

---

## ✅ Inheritance & Polymorphism

### 🔁 Method Invocation (Overriding)

```java
class P {
    void m1() {
        System.out.println("parent");
    }
}

class C extends P {
    void m2() {
        System.out.println("child");
    }
}

class Test {
    public static void main(String[] args) {
        P p = new C();  // Upcasting
        p.m1();         // "parent"
        // p.m2();      // Compile error
        ((C)p).m1();    // "parent"
        ((C)p).m2();    // "child"
    }
}
```

📌 Even after downcasting, `m1()` is still inherited from parent
📌 Downcast required to access child-specific methods

**Output:**

```
parent
parent
child
```

---

## ✅ Method Overriding with Variable Hiding

```java
class A {
    int x = 1;
    public void m1() {
        System.out.println("A");
    }
}

class B extends A {
    int x = 2;
    public void m1() {
        System.out.println("B");
    }
}

class C extends B {
    int x = 3;
    public void m1() {
        System.out.println("C");
    }
}

class Test {
    public static void main(String[] args) {
        C c = new C();
        c.m1();                  // "C"
        ((B)c).m1();             // "C"
        ((A)((B)c)).m1();        // "C"

        System.out.println(c.x);               // 3
        System.out.println(((B)c).x);          // 2
        System.out.println(((A)((B)c)).x);     // 1
    }
}
```

### 📌 Key Concepts:

* **Method Resolution** → Based on **runtime object type**
* **Variable Resolution** → Based on **reference type (compile time)**

**Output:**

```
C
C
C
3
2
1
```

---

### ✅ Static Methods: No Runtime Polymorphism

If methods are static:

```java
class A {
    static void m1() { System.out.println("A"); }
}

class B extends A {
    static void m1() { System.out.println("B"); }
}

class C extends B {
    static void m1() { System.out.println("C"); }
}
```

```java
C.m1();         // C
((B)c).m1();    // B
((A)((B)c)).m1(); // A
```

📌 **Static methods** are resolved at **compile-time**
📌 Output depends on **reference type**

---
