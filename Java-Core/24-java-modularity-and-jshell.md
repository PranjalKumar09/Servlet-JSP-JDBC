## 🧱 Java Modularity (Java 9+)

Modularity introduces a **higher level of aggregation above packages** via the **`module`**, enabling scalable, secure, and maintainable applications.

### 📦 Module Overview

A module is:

* A **uniquely named**, **reusable group of packages** and resources.
* Defined via a **`module-info.java`** descriptor.

### 🔑 Module Descriptor Defines:

1. **Module Name**
2. **Dependencies**: Modules this module depends on (`requires`)
3. **Exports**: Packages made available to other modules (`exports`)
4. **Services Offered** (`provides ... with`)
5. **Services Consumed** (`uses`)
6. **Reflection Permissions** (`opens`)
7. **Concealed Packages**: Not exported, internal use only

---

### 🎯 Goals of Java Modularity

| Goal                     | Description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| ✅ Reliable Configuration | Compile- and runtime dependency declaration using `requires` |
| ✅ Strong Encapsulation   | Access only to explicitly `export`ed packages                |
| ✅ Improved Performance   | Faster startup and smaller runtime footprint                 |
| ✅ Enhanced Security      | Reduces attack surface by hiding internal packages           |
| ✅ Scalable Development   | Suitable for large codebases and platform decomposition      |
| ✅ Integrity              | Avoids split packages, version conflicts                     |

> Example core packages: `java.lang`, `java.math`, `java.time`, `java.io`, etc.

---

## 📘 Javadoc Tool (API Documentation Generator)

Used to **generate HTML-based documentation** from Java source files using special comment tags.

### 🔧 Syntax:

```bash
javadoc -d <output-path> -sourcepath <source-path> -subpackages <root-package>
```

> Note: All JDK APIs are documented using the Javadoc tool.

### 🧩 How it Works:

* Parses declarations + `/** */` doc commenHere’s your **enhanced and concise version** of the notes, organized for clarity and aligned with the **Oracle Java 17 certification** focus. All original points are preserved, repetitive parts removed, and formatting improved for quick revision and memorization:

---

* Outputs HTML for:

  * Public and protected classes/interfaces
  * Constructors, methods, fields
  * Nested (but not anonymous inner) classes

### 📂 Usage Options:

* Entire packages (`-subpackages`)
* Individual `.java` files
* Explicit list of packages or files

📖 [Official Javadoc Guide](https://docs.oracle.com/en/java/javase/17/javadoc/javadoc.html)
📖 [Javadoc Reference](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)

---

## 🏷️ Javadoc Tags (with Examples)

| Tag                     | Purpose                                                                        |
| ----------------------- | ------------------------------------------------------------------------------ |
| `@author`               | Lists authors                                                                  |
| `@version`              | Version of the class/interface                                                 |
| `@since`                | Java release in which API was introduced                                       |
| `@deprecated`           | Marks API as outdated                                                          |
| `@see`                  | Adds related references                                                        |
| `@link`                 | Hyperlink to another class/member<br>e.g. `{@link java.util.List#add(Object)}` |
| `@code`                 | Formats text as code<br>e.g. `{@code int x = 5;}`                              |
| `@docRoot`              | Relative path to documentation root                                            |
| `@inheritDoc`           | Inherits documentation from superclass/interface                               |
| `@param`                | Describes method parameter                                                     |
| `@return`               | Describes return value                                                         |
| `@throws`, `@exception` | Describes thrown exceptions                                                    |
| `@serial`               | Describes a `Serializable` field                                               |
| `@serialField`          | Document `ObjectStreamField`                                                   |
| `@serialData`           | Data written by `writeObject()`/`writeExternal()`                              |
| `@value`                | Inserts constant’s value<br>e.g. `{@value java.lang.Math#PI}`                  |

---

## 📌 Class Structure Example

```java
package demos;

public class Whatever extends java.lang.Object {
    /**
     * The Whatever class represents an example of a documentation comment.
     * @author Oracle
     * @version 1.0
     */
}
```


  to access jshell do in cmd just type : jshell

  to exit: /exit


  /help	Displays a list of available jshell commands. or like /?
  /list	Lists the snippets (entered code) in the session.
  /edit	Opens the snippets in an external editor or allows editing a snippet.
  /drop	Deletes a snippet from the session.
  /reset	Resets the session, clearing all user-defined snippets.
  /reload	Reloads from the start or from a saved file.
  /save	Saves the current session to a file.
  /open	Opens and runs code from a file.
  /vars	Lists all declared variables.
  /methods	Lists all declared methods.
  /types	Lists all declared types (classes, interfaces, enums).
  /imports	Lists all imported packages and classes.
  /exit	Exits the jshell session.

  even like this jshell> /help /list


  generally everything allowed in jshell like declaration, statements, exprets 
  even creating class is allwed (even with imports)
  , what not allowed is declearing package, using public, protected, static, final , break, continue, & return  not allowed in gloabl

  liike break have no senese if no  loop is there


file created with name scriptxyz.txt  
not neccssary it should like it can have combination of statement that are valid in shell, means public class or package not valid & it can executed with /open name.txt


amount += 
  switch(period)