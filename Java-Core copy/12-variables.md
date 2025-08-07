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
        System.out.println(t.x); // 10
        // System.out.println(t.y); // error
    }
#### **3. Local Variables**

* Declared inside methods, constructors, or blocks.
* Stored in stack memory.
* Must be initialized before use. So no default

---


### **Scope & Access Issues**

- Variables declared in one block (e.g., `try`) are not visible in another (e.g., `catch`).

  ```java
  try {
      int x = 10;
      int ans = 0 / x;
  } catch (Exception e) {
      // ans = 0; // Error: 'ans' is out of scope
  }
  ```

## **Uninitialized Local Variable**

- Local variables must be explicitly initialized before use, or a compile-time error occurs.

  ```java
  public static void main(String[] args) {
      int x; // Uninitialized local variable
      if (args.length > 0) {
          x = 10;
      }
      // System.out.println(x); // Compile-time error: 'x' might not be initialized
  }
  ```

- Another invalid example:

  ```java
  int a;
  // int b = a; // Error: 'a' is not initialized
  ```

- Example with definite initialization:

  ```java
  public static void main(String[] args) {
      int x;
      if (args.length > 0) {
          x = 10;
      } else {
          x = 20;
      }
      System.out.println(x); // Valid: 'x' is definitely initialized
  }
  ```

---

### **Inner Classes**

## **Non-static Inner Class**

- Requires an instance of the outer class to be instantiated.

  ```java
  class Test {
      class Inner {}
  }
  
  Test.Inner i = new Test().new Inner(); // Outer instance required
  ```

## **Static Inner Class**

- Does not require an outer class instance.

  ```java
  class Test {
      static class Inner {}
  }
  
  Test.Inner i = new Test.Inner(); // No outer instance required
  ```

- **Invalid Declarations**:
  - `static class Outer {}` // Error: Top-level class can't be `static`
  - `abstract static class` // Error: Illegal combination

---

### **Main Method Rules**

## **Valid Forms**

- Valid `main` method signatures:
  - `public static void main(String[] args)`
  - Order doesn't matter: `static public`
  - Array or varargs: `String[] args`, `String args[]`, `String... args`
  - Argument name can be anything.
  - Can add modifiers: `final`, `synchronized`, `strictfp`.

## **Invalid Changes**

- Incorrect signature causes a runtime error:

  ```
  Error: Main method not found in class ...
  ```

## **Method Overloading**

- Overloading `main` is allowed, but only the standard signature is used as the entry point.

  ```java
  public static void main(String[] args) {} // Entry point
  public static void main(int[] args) {}    // Valid overload, but not entry point
  ```

- **Note**: Overloading with `String[] args` and `String... args` is **not allowed** (compile-time error).

---

### **Main Method and Inheritance**

- The `main` method can be inherited if not overridden.

  ```java
  class P {
      public static void main(String[] args) {
          System.out.println("Parent main");
      }
  }
  
  class C extends P {}
  ```

- Running `java P` or `java C` invokes `P.main()` unless `C` defines its own `main`.

---

### **Static Blocks vs `main()`**

- **Before Java 1.7**: Static blocks run even if `main` is missing.
- **After Java 1.7**: JVM checks for `main` first; if missing, it shows an error before executing static blocks.

  ```java
  class Test {
      static {
          System.out.println("Static block");
      }
  }
  ```

---

### **Program Termination**

- `System.exit(0)`: Normal termination.
- `System.exit(non-zero)`: Abnormal termination.

  ```java
  System.exit(0); // Normal termination
  System.exit(1); // Abnormal termination
  ```



extra knowledge

can be a variable, a constant or a method.

Local variables remain uninitialized unless and until they are initialized explicitly. It will be a compilation error if a local variable is accessed without getting initialized explicitly. (FYI, a local variable, aka, an automatic variable, is a variable that is defined within a method.)

