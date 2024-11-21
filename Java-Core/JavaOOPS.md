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

**Definition**:  
- The `this` keyword is a predefined object reference available in every non-static method. It refers to the current instance of the class.

- On calling `this` java compiler returns address of java compiler

**Benefits of Using `this`**:
1. **Avoids Naming Conflicts**: Enables local variables to have the same name as class data members.
2. **Constructor Chaining**: Enables calling one constructor from another within the same class.

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

- **Static Data Members**: Shared across all instances of a class.
- **Static Methods**: Can access static data members directly.
- **Static Blocks**: Initialize static members before any object is created.
- **Static Classes**: Only applicable to inner classes (nested classes), not outer classes.

---

#### Static Data Members
- **Memory Allocation**: Non-static data members are allocated memory only when an object is created. Static data members are allocated once when the program starts, independent of objects.
- Static members are saved in RAM once, i.e. they are independent of the objects.
- **Usage**: A data member is made static when it needs to be shared across all instances of a class (e.g., for consistent values across objects).
- **Features of Static Members**:
  - Allocated at the start of the program in the *Permanent Generation* area of memory.
  - Only a single copy exists, shared by all objects.
  - Accessed using the class name, e.g., `ClassName.variable`.
  - Local variables cannot be static, i.e. not inside a method

**Example**:  
```java
class Employee {
    private int ID;
    private String name;
    private int age;
    private static int nextId = 1; // Static member

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
        this.ID = nextId++;
    }

    public void show() {
        System.out.println("ID= " + ID + "\nName= " + name + "\nAge= " + age);
    }

    public static void showNextId() {
        System.out.println("Next employee ID will be " + nextId);
    }
}
```

#### Static Methods
A method should be made static in the following situations:
1. **Accessing only static data**: The method only works with static variables.
2. **Using only method arguments**: The method does not depend on instance variables.
3. **Factory Methods**: Static methods used to create objects conditionally.

**Example of Static Method**:
```java
class MyMath {
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
```

#### Factory Methods
- Used when object creation depends on conditions.
- The class constructor is usually private, and the static method controls object creation.
- Factory methods usually create and return Object
- Example: If age is less than or equal to zero, the `createPerson` method will return `null`.

**Example**:
```java
class Person {
    private int age;
    private String name;
    
    private Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void show() {
        System.out.println(age + ", " + name);
    }

    public static Person createPerson(int age, String name) {
        if (age <= 0) return null;
        return new Person(age, name);
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

    public Account() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account id, name, and balance:");
        accid = sc.nextInt();
        name = sc.next();
        balance = sc.nextDouble();
    }

    public void show() {
        System.out.println(name + "\n" + accid + "\n" + balance);
    }

    public static void showRate() {
        System.out.println("Rate of interest is " + rate_of_interest);
    }
}
```
===

#### Inheritance in Object-Oriented Programming
- **Definition**: Inheritance allows a class to acquire properties and behaviors (methods) from another class. The class that inherits is called the **derived (or subclass)**, and the class being inherited from is called the **base (or superclass)**.
- **Main Benefit**: **Code Reusability** – It promotes the reuse of existing code in new classes, saving time and reducing errors.
- **When to Use**: Inheritance is best used when there is an **"is-a"** relationship, e.g., a Manager **is a** type of Employee, or a Mango **is a** type of Fruit.

**Syntax**:
```java
class BaseClass {
    // Base class code
}

class DerivedClass extends BaseClass {
    // Derived class code
}
```

---

#### Example: **Single-Level Inheritance**

```java
class Employee {
    private String name;
    private double salary;

    public void setData(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

class Manager extends Employee {
    private double bonus;

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getIncome() {
        return getSalary() + bonus;
    }
}

public class CreateManager {
    public static void main(String[] args) {
        Manager boss = new Manager();
        boss.setData("Ashish", 50000);
        boss.setBonus(20000);
        System.out.println("Name of Boss: " + boss.getName());
        System.out.println("Income of Boss: " + boss.getIncome());
    }
}
```

- **Explanation**: `Manager` inherits from `Employee`. It adds the `bonus` field and calculates total income using the inherited salary.

---

#### Types of Inheritance
1. **Single Inheritance**: One class inherits from another (as shown above).
2. **Multilevel Inheritance**: A class inherits from a derived class, forming a chain.
3. **Hierarchical Inheritance**: Multiple classes inherit from a single base class.
4. **Java does not support Multiple Inheritance**:  
   - Multiple inheritance can cause **ambiguity**. For example, if two classes have a method with the same name, it’s unclear which one should be called in the subclass.

---

#### Using the `super` Keyword
- The `super` keyword refers to the base class (parent) and can be used in the derived class for:
  1. **Calling the base class constructor**.
  2. **Resolving method overriding** (when the subclass overrides a method and calls the superclass method).

**Example: Resolving Method Overriding**:
```java
class Employee {
    public double getSalary() {
        return 50000;
    }
}

class Manager extends Employee {
    private double bonus;

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + bonus; // Resolving method overriding
    }
}
```

---

#### Constructor Behavior in Inheritance
- **Base class constructor** is called first when creating an object of the derived class. If the base class has a parameterized constructor, it must be explicitly called using `super()`.
- **Non-Parameterized Constructor**: Automatically calls the base class constructor.
- **Parameterized Constructor**: The derived class constructor must use `super()` to call the base class constructor with arguments.

**Example**:
```java
class A {
    public A() {
        System.out.println("In Constructor of A");
    }
}

class B extends A {
    public B() {
        super();  // Explicitly calling the base class constructor
        System.out.println("In Constructor of B");
    }
}

public class Test {
    public static void main(String[] args) {
        B obj = new B();  // Output: In Constructor of A
                          //         In Constructor of B
    }
}
```

**Calling Parameterized Constructor**:
```java
class Num {
    private int a;
    private int b;

    public Num(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}

class AddNum extends Num {
    private int c;

    public AddNum(int x, int y) {
        super(x, y);  // Calling base class constructor with arguments
    }

    public void add() {
        c = super.getA() + super.getB();
    }

    public void show() {
        System.out.println("Sum is: " + c);
    }
}
```

---
**Method Overriding**: A subclass can override a superclass method, and `super` can be used to call the original method if necessary.




===

