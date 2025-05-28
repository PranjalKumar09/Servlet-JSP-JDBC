### **Global Variable Concepts (C, Python only – not Java)**

* Global variables: Declared outside all functions.
* Java has no true global variables (everything is inside a class).

---

### **Classification by Value Type**

#### **1. Primitive Types**

* Examples:

  ```java
  int x = 10;
  char ch = 'a';
  ```

#### **2. Reference Types**

* Examples:

  ```java
  String s = "abc";
  Student s1 = new Student();
  ```

---

### **Classification by Declaration Context**

#### **1. Instance Variables (Object-Level)**

* Belong to each object separately.
* Declared inside class but outside methods.
* Stored in heap as part of object.

  ```java
  class Student {
      String name;
      int rollNo; // instance variables
  }
  ```

#### **2. Static Variables (Class-Level)**
* Like in static block
* Shared across all instances.
* Declared using `static` keyword.
* Can’t directly access instance variables from static context:


  ```java
  class Test{
    int y = 20;
    static int x = 10;
    public static void main(){
        System.out.println(x); // both valid // 10
        System.out.println(Test.x);// 10

        Test t = new Test(); // this also
        s.o.s(t.x); // valid
    }
}
    Test t= ---;
    System.out.println(t.x); // 10
    // System.out.println(t.y); // error

  ``

#### **3. Local Variables**

* Declared inside methods, constructors, or blocks.
* Stored in stack memory.
* Must be initialized before use.

---

### **Scope & Access Issues**

```java
try {
    int x = 10;
    int ans = 0 / x;
} catch (Exception e) {
    ans = 0; // Error: 'ans' is out of scope
}
```

* `try` and `catch` are separate blocks; variables declared in one block aren't visible in another.

#### **Uninitialized Local Variable**

```java
public static void main(String[] args) {
    int x;
    if (args.length > 0)
        x = 10;
    System.out.println(x); // Compile-time error: 'x' might not be initialized
}
```

``` java
int a;
int b = a; // error
```
```java
if (args.length > 0)
    x = 10;
else
    x = 20;
System.out.println(x); // Valid: 'x' is definitely initialized
```

---

### **Inner Classes**

#### **Non-static Inner Class**

```java
class Test {
    class Inner {}
}
Test.Inner i = new Test().new Inner(); // Outer instance required
```

#### **Static Inner Class**

```java
class Test {
    static class Inner {}
}
Test.Inner i = new Test.Inner(); // No outer instance required
```

* **Invalid Declarations:**

  * `static class Outer {}` // Error: top-level class can't be `static`
  * `abstract static class` // Illegal combination

---

### **`main()` Method Rules**

#### **Valid Forms**

* `public static void main(String[] args)`
* Order doesn't matter: `static public`
* `String[] args`, `String args[]`, `String ... args` — all valid
* Argument name can be anything
* Can add modifiers: `final`, `synchronized`, `strictfp`

#### **Invalid Changes**

* Wrong signature leads to runtime error:

  ```
  Error: Main method not found in class ...
  ```

#### **Method Overloading**

```java
main(String[] args) // Valid
main(int[] args)    // Valid
```

* **Overloading with** `String[] args` **and** `String... args` **is NOT allowed** (compile-time error)

---

### **Main Method and Inheritance**

```java
class P {
    public static void main(String[] args) {}
}
class C extends P {}
```

* Running `java P` or `java C` will invoke `P.main()` unless `C` overrides it.
* If `C` has its own `main()`, that will be executed when running `java C`.

---

### **Static Blocks vs `main()`**

* Before Java 1.7: Static block runs even if `main()` is missing.
* After Java 1.7: JVM first checks for `main()`, otherwise shows error before executing static blocks.

---

### **Program Termination**

```java
System.exit(0); // Normal termination
System.exit(non-zero); // Abnormal termination
```

---