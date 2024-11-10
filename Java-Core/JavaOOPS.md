### Are Humans Objects?
- **Yes**, humans can be considered as objects:
  - We have *attributes* like name, height, and age.
  - We also *exhibit behaviors* such as walking, talking, running, and eating.

---

### Key Pillars of Object-Oriented Programming (OOP)
1. **Abstraction**: Focus on essential characteristics, hiding unnecessary implementation details.
2. **Encapsulation**: Combines data and methods that operate on that data into a single unit or *class*.
3. **Polymorphism**: Means "many forms"; allows entities to take multiple forms, a concept derived from biology.
4. **Inheritance**: Enables code *reusability*, *extensibility*, and organizes information in a hierarchical structure.

---

### Creating a Class and Its Object in Java

- **Class Syntax**:
  ```java
  class <ClassName> {
      <access modifier> <data type> <variable name> = value;

      <access modifier> <return type> <method name>(parameters) {
          // Method body
      }
  }
  ```
- **Encapsulation in Java**: 
  - Encapsulation is implemented by defining methods within a class to access or modify its data.

- **Example**: `Student` class with encapsulated data and methods to set and display data.
  ```java
  import java.util.*;

  class Student {
      private int roll;
      private char grade;
      private float percentage;

      public void setData() {
          Scanner kb = new Scanner(System.in);
          System.out.println("Enter roll, grade, and percentage:");
          roll = kb.nextInt();
          grade = kb.next().charAt(0);  // Fixed the char input
          percentage = kb.nextFloat();
      }

      public void showData() {
          System.out.println("Roll: " + roll);
          System.out.println("Grade: " + grade);
          System.out.println("Percentage: " + percentage);
      }
  }

  class UseStudent {
      public static void main(String[] args) {
          Student s = new Student();
          s.setData();
          s.showData();
      }
  }
  ```
- **Explanation**:
  - The `Student` class encapsulates data (`roll`, `grade`, `percentage`) and methods (`setData()`, `showData()`).
  - The `UseStudent` class creates a `Student` object, sets data using `setData()`, and displays it with `showData()`. 

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
- **Definition**: Special methods that initialize an object when created.
- **Characteristics**:
  - Same name as the class
  - It returns object address intenally when called
  - No return type
  - Automatically called with the `new` keyword
  - If not defined by the programmer, Java provides a default constructor.
- **Example**:
  ```java
  class Account {
      private int accid;
      private String name;
      private double balance;

      public Account(int id, String name, double balance) {
          accid = id;
          this.name = name;
          this.balance = balance;
      }

      public void show() {
          System.out.println("ID: " + accid + "\nName: " + name + "\nBalance: " + balance);
      }
  }
  ```

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
  - Executed before the constructor.
  - Runs each time an object is created.
  - Useful for anonymous classes without constructors.
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

### Method Overloading in Java
- **Definition**: Method overloading allows multiple methods with the same name but different parameters within a class.
- **Example**: Java’s `println()` method can print various data types (e.g., integers, strings, booleans, doubles) using a single method name.

#### Rules for Method Overloading
1. Methods must differ in their *arguments* (either in *number*, *data type*, or *order* of parameters).
2. Overloading is not based on the method’s return type.

#### Example Code
```java
class OverloadDemo {
    public void show(int a) {
        System.out.println("In show with int argument: " + a);
    }
    public void show(double b) {
        System.out.println("In show with double argument: " + b);
    }
    public void show(String s) {
        System.out.println("In show with String argument: " + s);
    }
}

class Test {
    public static void main(String[] args) {
        OverloadDemo obj = new OverloadDemo();
        obj.show(10);       // Calls show(int)
        obj.show(3.14);     // Calls show(double)
        obj.show("Amit");   // Calls show(String)
    }
}
```

#### Selection of Overloaded Methods
- The compiler matches the method based on argument type.
- If an exact match is not found, it selects the next *higher data type*.
- If no suitable method is found, a syntax error occurs.

---

### Constructor Overloading
- **Definition**: Similar to method overloading, constructors can be overloaded to offer various ways to initialize an object.
- **Benefits**: Allows flexibility in object creation by providing multiple initialization options.

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
