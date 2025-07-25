
## 🛠️ **C vs C++ Overview**

### **C Language**

* **Purpose**: Designed for building **System Software** like:

  * Operating Systems
  * Device Drivers

### **C++ Language**

* **Improvements over C**:

  * Introduced **Object-Oriented Programming** (OOP) for better **data security** and **real-world modeling**
* **Popular Software in C++**:

  * Adobe Acrobat, Winamp, Internet Explorer, Mozilla Firefox

---

## ☕ **Java Overview**

### **Definition**

* Java is a **technology** (not just a language) used for developing:


### **Platform**

* A combination of **Operating System** and **Processor** where programs run.
* Examples: Windows, Linux, MacOS, MacOS, Windows
* Total: 5 physical machines = 3 logical platforms

---

## 🌟 **12 Java Features (Buzzwords)**

1. **Simple**

   * Easier than C++; no pointers.
2. **Secure**

   * `Test.java` → `test.class` → Bytecode
   * JVM verifies bytecode (e.g., `VerifyError`)
   * Security Manager & Sandbox enforcement
3. **Portable**

   * Platform-independent bytecode enables cross-device execution.
4. **Object-Oriented**

5. **Robust**

   * Strong type-checking: `int i = 10.0;` → Compile Error
   * **Garbage Collection** reduces memory issues
   * **Exception Handling** increases reliability
6. **Multithreaded**

   * Executes multiple threads simultaneously for better performance
7. **Architectural Neutral**

   * Bytecode runs consistently across all environments
8. **Interpreted**

   * Compiled & interpreted for performance + flexibility
9. **High Performance**

   * Faster than other interpreted languages, close to C++
10. **Distributed**

    * Built-in support for networking, load balancing, and failover
11. **Dynamic**

    * Loads classes at runtime (no `#include`)
      Example:
         import java.util.*; // used when demand     
       `Date d = new Date();` → `java.util.*` loaded on demand
12. **Platform Independent**

    * WORA: **Write Once, Run Anywhere**

---

## 🔥 **Java Editions**

1. **JSE (Java Standard Edition)**: Core Java – desktop apps (e.g., calculator, media player)
2. **JEE (Java Enterprise Edition)**: For large-scale web/server apps (e.g., Amazon, IRCTC)
3. **JME (Java Micro Edition)**: Lightweight Java for devices (e.g., mobile, smartcards – now largely replaced by Android)

---

## 🔄 **Java Program Lifecycle**

```java
Test.java   -->   javac Test.java   -->   Test.class (Bytecode)   -->   java Test
```

* JVM loads `Test.class`, creates main thread
* Main thread invokes `main()`
* After execution:

  * Static data is unloaded
  * Thread terminates
  * JVM shuts down

### **Command-Line Execution**

```bash
$ javac Test.java
$ java Test arg1 arg2 arg3
```

* If `Test.class` not found → `NoClassDefFoundError`
* Command-line arguments accessed via `args[]`

---
## Java Compilation & Platform-Specific Execution

Java source code (`Test.java`) goes through the following execution flow:

1. **Development**: Source file written by the developer.
   ```bash
   javac Test.java
    ```
2. **Bytecode**:
  - Intermediate, platform-independent representation.
  - Stored in Test.class.

## 🔧 **Java Components**

| Component                          | Description                                                   |
| ---------------------------------- | ------------------------------------------------------------- |
| **JVM (Java Virtual Machine)**     | Executes bytecode; includes interpreter and garbage collector |
| **JRE (Java Runtime Environment)** | JVM + Java class libraries; needed to run Java programs       |
| **JDK (Java Development Kit)**     | JRE + compiler (`javac`), debugger, dev tools                 |

**Formula**: `JDK = JRE + Development Tools`

---

## 🔁 **Compiling with Packages**

```bash
javac -d . Test.java        # Compiles and creates appropriate package directory
```


### **Running with Classpath**

```bash
java -cp /project/classes demos.Whatever John
# Output: Hello John
java -cp <compiled_class_path_or_jar> <MainClassName>
```
  
``` bash
javac -cp <dependency_path> -d <output_directory> <your_source_file> # Use -cp if external dependencies exist
```
> **Note**: Classpath (default = current directory) specifies where to look for classes. (its  not recommended practice)

> Use **fully qualified class name** (`package.ClassName`) without `.class` extension.

---

## ⚡ **Java 11+ Feature: Single File Execution**

```bash
java /project/sources/Whatever.java
```

* Direct execution without compiling
* Good for scripting or quick tests

---

## 📥 **User Input in Java**

1. **Command-Line Arguments**

  ```java
  public static void main(String[] args) {
        System.out.println("Hello " + args[0]);
  }
  ```

2. **Scanner Class**

   ```java
   import java.util.Scanner;
   Scanner sc = new Scanner(System.in);
   int num = sc.nextInt();
   sc.close();
   ```

3. **GUI (Swing / JavaFX)**: For graphical input (not covered in this summary)



## 🧠 **Static Methods & Math Operations**

* **Static**: Belongs to class, accessed without object. (Even without importing)
* **Math Class**:

  ```java
  double pi = Math.PI;
  double power = Math.pow(2, 3); // 8.0
  ```

---

## ❗ **Exception Handling**

### Common Runtime Error

* **NumberFormatException**

  ```java
  int n = Integer.parseInt("20.5");  // Error: invalid integer format
  ```

---

## ✅ **Main Method & Static Context**

* **Main method**:

  ```java
  public static void main(String[] args) { }
  ```
* **Why static?**

  * JVM doesn't need to instantiate the class
* **Static block**:

  * Executes before `main()` (used for initialization)

---
### Compatibility Note:

  - **Version**: JavaC <= jvm

-   If not: ❌ UnsupportedClassVersionError or Unsupported major.minor version error may occur.



----



