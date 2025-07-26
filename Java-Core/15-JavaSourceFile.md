## 📁 Java Source File Structure

---

### 🔹 File & Cla                                                                                                       ss Structure

* A `.java` source file can contain **any number of classes**.
* **File name can be anything**, *unless*:

  * It contains a **public class** → then the file name **must match** the public class name.
* Only **one public class** allowed per file.

  * More than one → **compiler error**.
* Best Practice: Name file after the main class.

#### 🔹 Compilation & Execution

```bash
javac filename.java     ## Compiles file
java ClassName          ## Runs class containing main() method
```

* You can compile one file and run **any class** that has a `main()` method.
* Each class can independently have or not have `main()`.

---

### ❌ Using Unknown Symbols (Compiler Errors)

Examples of common errors:

```java
 // ❌ Error: cannot find symbol error
ArrayList list = new ArrayList(); 
// Symbol: class ArrayList
System.out.println(Arraylist); 
// Symbol: variable ArrayList
System.out.println(Arraylist()); 
// Symbol: method ArrayList
```

🔹 Causes:

* Missing import.
* Wrong capitalization.
* Constructor/method/class/variable not declared.

🔸 **Key Point:**

> Java compiler is **strict and "innocent"** — any unknown symbol leads to a **compile-time error**.

✅ Fix: Use **fully qualified name** or correct import:

```java
java.util.ArrayList list = new java.util.ArrayList();
```

---

### 📦 Import Types

#### 1. **Explicit Import** (✅ Recommended)

```java
import java.util.ArrayList;
```

#### 2. **Implicit Import** (Wildcard)

```java
import java.util.*;
```

* Both forms are **functionally same** in terms of performance and loading.
* However, **IDEs usually generate explicit imports** for clarity and maintainability.
* `import java.util.ArrayList*;` or `import java.util;` → ❌ Invalid.

#### ➕ Typing Shortcut

```java
class MyList extends java.util.ArrayList { }
```

* No import required if fully qualified name is used.

---

### ⚠️ Import Conflict Example

```java
import java.sql.*;
import java.util.*;

class Test {
    Date d = new Date();  // ❌ Ambiguous: Date exists in both packages
}
```

✅ Fix:

```java
import java.sql.*;
import java.util.Date;

class Test {
    Date d = new Date();  // ✅ unambiguous
}
```

---

### 📌 Import Precedence

1. **Explicit Import** (e.g., `import java.util.Date;`)
2. **Classes in the same package/directory**
3. **Implicit Imports** (e.g., `import java.util.*;`)

📎 Note: Importing a package does **not** import subpackages.

```java
import java.util.regex.*;        // ✅ Valid
// import java.util.regex;       ❌ Invalid
```

---

### 🧮 Static Import

Allows access to static members without class reference.

#### Example Without Static Import:

```java
System.out.println(Math.sqrt(25));
```

#### With Static Import:

```java
import static java.lang.Math.sqrt;
import static java.lang.System.out;

class Test {
    public static void main(String[] args) {
        out.println(sqrt(16));  // ✅ Valid
    }
}
```

---

### 📦 Packages

* Mechanism for grouping related classes. (like in single component)
* Provides:

  * **Encapsulation**
  * **Modularity**
  * **Security**
  * **Name conflict resolution**

#### 📦 Declaration Syntax

```java
package pack1;
```

* Should be **first line** of the file.
* Only **one `package` statement** allowed per file → otherwise **compiler error**.

🛑 Wrong Order:

```java
// ❌ Compiler Error
import java.util.*;
package mypack;
```

#### 📦 Naming Convention

* Use **reverse domain name** for uniqueness:

  ```java
  package com.company.project;
  ```

* Any package name is technically valid:

  ```java
  package abc123$;
  ```

---

### 🛠️ Compiling with Package

```bash
javac -d . Test.java
```

* `-d .` means: create package directories in the **current location**.
* If the target directory (e.g., project folder) doesn’t exist → compiler error.
* Packages create **directories**, not `.package` files.

📌 Default package: A class with no `package` declaration is in the **default package**.

---

### ✅ Additional Notes

* Even an **empty Java program** (a file with just class declaration) is **valid**.
* Importing `java.util.*` doesn't bring in `java.util.regex.*` → must import subpackages separately.
* Use of wildcards doesn't reduce performance at **runtime**, only slightly affects **compile-time parsing**.

---

A) import java.util.*; AL a = new AL();
B) java.util.AL a = new java.util.AL();

    Compile-time: A is slightly slower (due to wildcard lookup).

    Run-time: Both are same.



 - If two files are in the same package (e.g., `Num.java` and `UseNum.java`), compiling one will automatically compile the other if it depends on it. (like Num class object in UseNum class)
   - **Wildcard Compilation**: Use `javac myjavacodes\*.java` to compile all `.java` files in the package.