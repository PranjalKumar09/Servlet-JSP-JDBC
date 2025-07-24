### Are Humans Objects?
- **Yes**, humans can be considered as objects:

---

### Key Pillars of Object-Oriented Programming (OOP)
1. **Abstraction**
2. **Encapsulation**
3. **Polymorphism**
4. **Inheritance**

---

### Object Initialization in Java
Java provides three primary ways to initialize an object:

1. **Explicit Initialization**
2. **Constructors**
3. **Initializer Blocks**

---

### 1. Explicit Initialization
- **Definition**: Setting class member values directly at the point of declaration.
- **Benefits**: Fastest form of initialization; ideal when all instances should start with the same values.
- **Example**:
  ```java
  class Account {
      private int id = 101;
      private String name = "AMIT";
      private double balance = 50000.0;

      public void show() {
          System.out.println(id + "\n" + name + "\n" + balance);
      }
  }
  ```

### 2. Constructors

#### Initializing Multiple Objects with Constructor
- To initialize multiple objects efficiently, use an *array of references*.
  ```java
  Account[] accounts = new Account[2];
  for (int i = 0; i < accounts.length; i++) {
      accounts[i] = new Account(id, name, balance);
  }
  ```

### 3. Initializer Blocks
- **Definition**: Blocks of code used to initialize instance variables.
- **Characteristics**:
  - Executed **before** the constructor.
  - Runs each time an object is created.
  - Useful for as classes without constructors.
- **Syntax**:
  ```java
  class Test {
      int x, y;

      // Initializer block
      {
          x = 10;
          y = 20;
          System.out.println("In initializer block with x= " + x);
      }

      public Test() {
          System.out.println("In constructor with y= " + y);
      }
  }

  // Creating objects
  Test t1 = new Test();
  Test t2 = new Test();
  ```

- **Observation**: The initializer block runs before the constructor and executes every time an object is created.

---

### Benefits and Drawbacks of Initializer Blocks
- **Benefits**:
  - Useful for classes without explicit constructors, such as anonymous classes.
- **Drawback**:
  - Cannot accept arguments or return values.


---



### Argument Passing in Java
- **Pass by Value Only**: Java passes arguments by value, meaning a copy of the value is passed to methods.
  - **Passing Variables**: When variables are passed, only a copy is used within the method, so the original variable remains unchanged.
  - **Passing References**: While objects can be passed, only the reference copy is used, preserving the original reference.

#### Example: Pass-by-Value with Variables
```java
class Demo {
    public void increment(int x, int y) {
        x++;
        y++;
    }
}

class Test {
    public static void main(String[] args) {
        Demo demo = new Demo();
        int x = 10, y = 20;
        System.out.println("Before increment: x = " + x + ", y = " + y);
        demo.increment(x, y);
        System.out.println("After increment: x = " + x + ", y = " + y); // x and y remain unchanged
    }
}
```

#### Output
```plaintext
Before increment: x = 10, y = 20
After increment: x = 10, y = 20
```
---

#### Passing Object Reference

```java
class Demo {
    private int x;
    private int y;

    public Demo(int i, int j) {
        x = i;
        y = j;
    }

    public void increment(Demo d) {
        d.x++;
        d.y++;
    }

    public void show() {
        System.out.println("x= " + x);
        System.out.println("y= " + y);
    }
}
```

**Explanation**:  
- **Pass-by-Reference**: When an object is passed to a method, it’s the reference (address) that is passed, not a copy of the object. Changes made to the object in the method affect the original object.
- Here, the `increment` method modifies the values of `x` and `y` directly in the passed object `d`.

#### Passing Array Reference

```java
class Demo {
    public void doubler(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= 2;
        }
    }
}

class Test {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        Demo demo = new Demo();
        demo.doubler(arr);

        for (int value : arr) {
            System.out.println(value);
        }
    }
}
```

**Explanation**:  
- **Array as Reference**: Arrays in Java are also passed by reference, allowing direct modifications within methods.
- The `doubler` method doubles each element in the `arr` array, which reflects in the original array outside the method.

#### Returning Array Reference

```java
class Demo {
    public int[] createArray(int size) {
        return new int[size];
    }
}

class Test {
    public static void main(String[] args) {
        Demo demo = new Demo();
        int[] arr = demo.createArray(5);
        System.out.println("Length of array is " + arr.length);
    }
}
```

**Explanation**:  
- **Returning Arrays**: Methods can return array references. Here, `createArray` returns a reference to a new array of specified size, accessible in the calling method.

#### The `this` Keyword in Java

**Benefits**:
1. **Avoids Naming Conflicts**
2. **Constructor Chaining**

```java
class Box {
    private int l, b, h;

    public Box(int l, int b, int h) {
        this.l = l;
        this.b = b;
        this.h = h;
    }
}
```

#### Static Members and Nested Classes

- **Static Data Members**
- **Static Methods**: Can access static data members directly.
- **Static Blocks**: Initialize static members before any object is created.
- **Static Classes**: Only applicable to inner classes (nested classes), not outer classes.

---

#### Static Data Members

#### Static Methods -> Class Variable
A method should be made static in the following situations:
1. **Accessing only static data**: The method only works with static variables.
2. **Using only method arguments**: The method does not depend on instance variables.
3. **Factory Methods**

**Example of Static Method**:
```java
class MyMath {
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
```

#### Properties of Static Methods:
- **Access Restrictions**:
  - Only static data of the class can be accessed implicitly.
  - No `this` reference.
  - Cannot use the `super` keyword.
- **Usage**: Called using the class name, not through instances.
- **Common Usage**: All methods in the `Math` class are static.

**Example with Static Block**:
```java
import java.util.Scanner;

class Account {
    private int accid;
    private String name;
    private double balance;
    private static double rate_of_interest;

    static {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter rate of interest:");
        rate_of_interest = sc.nextDouble();
    }
}
```
===
#### Types of Inheritance
1. **Single Inheritance**
2. **Multilevel Inheritance**: A class inherits from a derived class, forming a chain.
3. **Hierarchical Inheritance**: Multiple classes inherit from a single base class.
4. **Java does not support Multiple Inheritance**:  
   - Multiple inheritance can cause **ambiguity**. For example, if two classes have a method with the same name, it’s unclear which one should be called in the subclass.

---

#### Using the `super` Keyword
  1. **Calling the base class constructor**.
  2. **Resolving method overriding** (when the subclass overrides a method and calls the superclass method).



---


### Polymorphism
- **Method Overloading**
- **Method Overriding**

---

### Binding
- **Binding**: Process by which the compiler decides which method or function to call.
- **Types of Binding**:
  1. Early Binding
  2. Late Binding\\
---

### Example of Late Binding with `A` and `B`

```java
class A {
    public static void show() { System.out.println("In show of A"); }
    public void display() { System.out.println("In display of A"); }
}

class B extends A {
    public static void show() { System.out.println("In show of B"); }
    public void display() { System.out.println("In display of B"); }
}

class Test {
    public static void main(String[] args) {
        A ref;
        ref = new A();
        ref.show();       // Outputs: In show of A
        ref.display();    // Outputs: In display of A

        ref = new B();
        ref.show();       // Outputs: In show of A (Static method)
        ref.display();    // Outputs: In display of B (Dynamic method dispatch)
    }
}
```

---

====


 
- **Inheritance**: Although a `final` class cannot be inherited, it can itself inherit from other classes. For example, all Java classes, including final ones, inherit from the `Object` class.

---

#### Using Interfaces as Global Constants

  **Example**:
  ```java
  interface Conversions {
      double KG_TO_G = 1000;
      double kgToGrams(double kg);
  }

  class TryConversions implements Conversions {
      public double kgToGrams(double kg) {
          return kg * KG_TO_G;
      }
  }
  ```

- **Example Usage**:
  ```java
  class TestConversions {
      public static void main(String[] args) {
          Conversions co = new TryConversions();
          System.out.println("Weight in grams: " + co.kgToGrams(5));
          System.out.println("Height in mm: " + co.inchesToMm(10));
      }
  }
  ```


### Singleton Class in Java

- **Definition**: A Singleton class ensures that only one instance of the class exists throughout the program's lifecycle.
- **Use Case**: Useful for centralized control objects like configuration managers, logging systems, or database connections.

**Implementation Steps**:
1. **Private Constructor**: Prevents direct instantiation.
2. **Private Static Instance**: Holds the single instance.
3. **Public Static Method**: Provides access to the instance.

**Example**:
```java
public class Singleton {
    private static final Singleton instance = new Singleton();
    
    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        if (singleton1 == singleton2) {
            System.out.println("Both objects are the same instance.");
        }
    }
}
```
**Output**:
```
Both objects are the same instance.
```

===
===
1200