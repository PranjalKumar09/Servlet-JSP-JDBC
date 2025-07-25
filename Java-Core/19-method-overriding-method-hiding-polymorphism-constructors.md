## ✅ Method Overriding and Exception Handling

### 🔹 Valid/Invalid Overriding with Exceptions

| Parent Method Declaration             | Child Method Declaration                                              | Validity  | Reason                                                                   |
| ------------------------------------- | --------------------------------------------------------------------- | --------- | ------------------------------------------------------------------------ |
| `public void m1() throws Exception`   | `public void m1()`                                                    | ✅ Valid   | Child method can throw fewer or no checked exceptions.                   |
| `public void m1()`                    | `public void m1() throws Exception`                                   | ❌ Invalid | Parent doesn't throw any checked exception, so child can't add new ones. |
| `public void m1() throws Exception`   | `public void m1() throws IOException`                                 | ✅ Valid   | IOException is a subclass of Exception.                                  |
| `public void m1() throws IOException` | `public void m1() throws Exception`                                   | ❌ Invalid | Exception is a superclass of IOException (wider scope).                  |
| `public void m1() throws IOException` | `public void m1() throws FileNotFoundException, EOFException`         | ✅ Valid   | Both are subclasses of IOException.                                      |
| `public void m1() throws IOException` | `public void m1() throws FileNotFoundException, InterruptedException` | ❌ Invalid | InterruptedException is unrelated to IOException.                        |
| `public void m1() throws IOException` | `public void m1() throws ArithmeticException, NullPointerException`   | ✅ Valid   | Unchecked exceptions — no overriding restriction.                        |

> **Rule**: If the overridden method in the parent throws a **checked exception**, the child can only throw the **same or a subclass** of that exception. Unchecked exceptions (like `RuntimeException`) are always allowed.

---

## ✅ Method Overriding vs. Method Hiding

### 🔹 Static vs. Instance Methods

```java
class P {
    public void m1() {
        System.out.println("Parent");
    }
}

class C extends P {
    public static void m1() {
        System.out.println("Child");
    }
}
```

* ❌ Compilation Error
  You **cannot override an instance method as static**, or a **static method as non-static**. This is not allowed in Java.

---

## ✅ Variables and Method Hiding

```java
class P {
    public static void m1() { System.out.println("Parent"); }
    int x = 777;
    static int y = 777;
    static int z = 777;
    int z1 = 777;
}

class C extends P {
    public static void m1() { System.out.println("Child"); }
    int x = 888;
    static int y = 888;
    int z = 888;
    static int z1 = 888;
}
```

```java
public class Test {
    public static void main(String[] args) {
        P p = new P();
        p.m1(); // Parent
        System.out.println(p.x);  // 777
        System.out.println(p.y);  // 777
        System.out.println(p.z);  // 777
        System.out.println(p.z1); // 777

        C c = new C();
        c.m1(); // Child
        System.out.println(c.x);  // 888
        System.out.println(c.y);  // 888
        System.out.println(c.z);  // 888
        System.out.println(c.z1); // 888

        P p1 = new C();
        p1.m1(); // Parent (method hiding)
        System.out.println(p1.x);  // 777
        System.out.println(p1.y);  // 777
        System.out.println(p1.z);  // 777
        System.out.println(p1.z1); // 777
    }
}
```

### 🔹 Key Notes:

* Method hiding (static methods) is resolved at **compile time** using **reference type**.
* Variables are also **not polymorphic** — resolved by reference type, not object type. On any type/
* **Method Overriding applies to instance methods only**, resolved at **runtime** based on the object.

---

## ✅ Polymorphism in Java

### 🔹 Definition:

* **Polymorphism = Many forms**
* Allows a **parent class reference** to refer to a **child class object**.

### 🔹 Examples:

```java
List list = new ArrayList();
List list = new Vector();
```

```java
C c = new C();      // Access to both P and C methods (knows exact type)
P p = new C();      // Access to only P's methods (calls C's version at runtime) biggest object it that it can hold child obejcts
```

### 🔹 Advantage:

* Enables **flexibility** and **decoupling**.
* Common use case in method return types:

```java
List m1() {
    return new ArrayList(); // or LinkedList, Vector, etc.
}
```

### 🔹 Types of Polymorphism:

| Type        | Mechanism    | Example                           |
| ----------- | ------------ | --------------------------------- |
| **Static**  | Compile-time | Method Overloading, Method Hiding |
| **Dynamic** | Runtime      | Method Overriding                 |

---

## ✅ Constructors in Java

### 🔹 Rules:

1. Constructor name = class name.
2. No return type (not even `void`). If return type is present, it's a method, not a constructor.
3. A class can have:

   * **User-defined constructor**
     * Or a **default constructor**, auto-generated by the **compiler** only if no other constructor is defined. It has `default` access (package-private) & typically contains a single statement: `super();` or `this();`, depending on context.
    
     Example:
    
     ```java
     ClassName() {
         super();
     }
     ```

4. All 4 access modifiers are allowed.
5. No other modifiers allowed (e.g., `static`, `final` – ❌ invalid).
6. **Private constructor** → used in Singleton pattern (restricts object creation from outside).

### 🔹 Constructor Execution:

```java
// code
    Student(String name , Int rollNo){
        this.name = name;
        this.rollno = rollno;
    }
// code
Student s1 = new Student("Ram", 1);
// Steps:
// 1. Object is created with `new`
// 2. Constructor is executed to initialize fields
```

### 🔹 Common Mistake:

```java
class Test {
    void Test() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.Test(); // This calls a method, not a constructor
    }
}
```

* `void Test()` is a method, **not a constructor** because it has a return type (`void`).

---

## ✅ Final Recap:

* **Method Overriding** requires matching signatures and compatible exceptions.
* **Static methods** and variables are **not overridden**, they are **hidden**.
* **Variables and static methods** use **reference type**, not object type.
* **Polymorphism** enables flexibility via parent references pointing to child objects — especially useful in APIs and collections.


====


EXTRA KNOWLEDGE

we can override with certain conditions , so we wrap it with try can catch