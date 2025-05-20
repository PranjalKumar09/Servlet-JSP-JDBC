
## **C Language**
- **Primary Design**: Developed primarily for creating **System Software**, including:
  - Operating Systems
  - Device Drivers

## **C++ Language**
- **Purpose**: Created to address security issues inherent in C.
- **Characteristics**:
  - Object-Oriented Programming (OOP) language, enhancing data security.
  - Suitable for solving real-world problems.
- **Popular Software Developed in C++**:
  - Adobe Acrobat
  - Winamp Media Player
  - Internet Explorer
  - Mozilla Firefox

---

## **Java Overview**
- **Java** is a **technology** (not just a programming language) designed for developing:
  - Object-Oriented applications
  - Platform-independent applications

### **Platform**
- **Definition**: An environment where a program runs; it is a combination of the **Operating System (OS)** and the **Processor (CPU)**.
  - Examples of platforms: Windows, Linux, MacOS, Windows, MacOS
  - Total Platforms: Physical Machines = 5, Platforms = 3

---

JAVA features (12 BuzzWords)

1 Simple
  from C++, no pointerg
2 Secure
  Test.java => test.java (compiler) => Bytecode => machine code (jvm)
  inside jvm: bytecode verifier + secuirty manager
  java.lang.VerifyError
    sandbox checking

3 Portable => Mobile portability

4 Object Oriented
  Encapsulation (Security), Inheritance => Reusablity ,Polymorphism (Flexibility)
5 Robust
  change of failing is very rare
  incompataible lossy conversion
  int i = 10.0;
  error , strong typed checking, 
  java is strong typed programming language

  Garbage collector => memory relate problem less
  Exception handle
  Som program is different device (Independence)

6 Multi thread => Simultaneously, java program -> multiple program
7 Architectural neutral => if today , will run tomorrow

8 Interpreted => Compile & Interpreted -> Both advantage
9 High Performance => Relatively faster, close to C++
10 Distributed => Load balancing, fail over
11 Dynamic => Unlike C, or C++ nothing include like #include .. at starting,, in java nothing will loading when that line come,, import java.util.*
Date d = new.... <- this line when come then only this will imported
12 Platform Indejvmpendence => Write once & run anywhere (WORA)


---

## **Java Editions**
1. **Java Standard Edition (JSE)**:
   - Core Java for developing desktop applications (e.g., calculators, media players).
  
2. **Java Enterprise Edition (JEE)**:
   - Built on JSE for developing large-scale, server-side applications (e.g., e-commerce sites like Amazon, IRCTC).
  
3. **Java Micro Edition (JME)**:
   - A lightweight version of Java for small devices (e.g., mobile phones, smartcards). Its usage has decreased with the rise of Android.

---
---

Java Program -> JavaC -> ByteCode 
  ByteCode
    -- Windows based jvm -> window
    -- Linux based jvm -> Linux
    -- Linux based jvm -> Linux

In terminal 
  $ javac Test.java          
  $ java Test

```bash
$ java Test arg1 arg2 arg3
```
  JVM will start, create & start main thread
    main thread search for Test.class if not found => no class def found error
  main thread local test.class
  & execute main main method
  unload Test.class file  <= here static will destroyed
  Terminate main thread
  shutdown JVM


Java Source file  (Test.java) Developer (Develop + Run)
Java compiler  (javaC Test.java) Client (Run)
Byte Code (Test.class) JVM

Version: JavaC <= jvm
otherwise unsupported exceptional error

## **Java Components**
- **JVM (Java Virtual Machine)**: 
  - An abstract machine that executes Java bytecode.
  - Components:
    - **Interpreter**: Converts bytecode to machine code.
      Run line by line
    - **Garbage Collector**: Manages memory automatically.

- **JRE (Java Runtime Environment)**:**
  - JVM + Java class **libraries** needed to run** Java programs.
  - Essential for executing Java applications.

- **JDK (Java Development Kit)**:
  - A comprehensive package for developing Java applications, including:
    - The JRE
    - Java compiler (`javac`), Debugger
    - Development tools
  - **JDK = JRE + Development Tools**

---

## **Java Program Execution**
- **Main Method**: 
  - Entry point for Java applications; defined as `public static void main(String[] args)`.
- **Static Methods**: 
  - Can be invoked without an object instance.
  


---

## **Java Primitive Data Types and Wrapper Classes**
### **Primitive Data Types**:
- Java supports eight primitive data types:
  - `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`.

### **Wrapper Classes**:
- Classes that provide a way to use primitive data types as objects:
  - `Integer`, `Character`, `Float`, `Boolean`, etc.
  - **Key Functions**:
    - Convert between primitive types and their corresponding wrapper classes.
    - Example: `Integer.parseInt("10")` converts a String to an int.

---


## **Static Methods and Math Operations**

- **Accessing Static Members**:
  - Static methods and variables can be accessed without creating an instance of a class.
  - Example: `double piValue = Math.PI;`

- **Math Class**:
  - It already included so no need to be include that explicitly
  - Provides various mathematical operations.
  - Example: 
    ```java
    double result = Math.pow(2, 3); // Result: 8.0 (2^3)
    ```

---

## **User Input in Java**

1. **Command-Line Arguments**:
   - Example:
     ```java
     class Test {
         public static void main(String[] args) {
             System.out.println("Hello " + args[0]); // Run with 'java Test Sachin'
         }
     }
     ```

2. **Using Scanner Class**:
   - For reading user input from the console.
   - Example:
     ```java
     import java.util.Scanner;
     class InputExample {
         public static void main(String[] args) {
             Scanner scanner = new Scanner(System.in);
             System.out.print("Enter a number: ");
             int number = scanner.nextInt();
             System.out.println("You entered: " + number);
             scanner.close();
         }
     }
     ```

3. **Using GUI Components**: 
   - For creating graphical user interfaces to receive user input.

---

## **Exception Handling**

- **NumberFormatException**: Occurs when trying to convert a non-integer string using `Integer.parseInt()`.
- Example:
  ```java
  String str = "20.5";
  int num = Integer.parseInt(str);  // Throws NumberFormatException
  ```

---