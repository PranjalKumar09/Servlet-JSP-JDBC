### Exception Handling in Java

**Exception** in programming refers to errors that occur during the runtime of a program. These errors might be due to user input mistakes or logical flaws in the program itself. In Java, exception handling is a mechanism to deal with runtime errors, ensuring that the program doesn't terminate unexpectedly and can continue running even after an error occurs.

---

### Java's Default Behavior When an Exception Occurs:
1. **Program Termination**: When an exception occurs, Java immediately halts execution at the line where the exception was thrown.
2. **Technical Error Message**: Java generates a technical error message, which is often not user-friendly.

### Why Default Behavior Isn't Ideal:
- If an exception occurs, it’s better if the program can continue executing the unaffected parts.
- The error message should be understandable to the user, helping them correct their mistakes.

---

### Exception Handling in Java

To handle exceptions in Java in a user-friendly and controlled manner, Java provides keywords like `try`, `catch`, `throw`, `throws`, and `finally`.

---

### Key Keywords in Exception Handling:
1. **`try`**: Defines a block of code in which exceptions might occur.
2. **`catch`**: Catches exceptions thrown in the `try` block and allows you to handle them.
3. **`throw`**: Explicitly throws an exception from a method.
4. **`throws`**: Declares that a method can throw exceptions.
5. **`finally`**: Defines a block of code that will execute whether an exception occurs or not (e.g., for cleanup).

---

### `try` and `catch` Syntax:

```java
try {
    // Code that may cause an exception
}
catch (ExceptionType e) {
    // Handle exception
}
```

- **Try Block**: Contains code that might throw an exception.
- **Catch Block**: Catches and handles exceptions thrown from the try block. The `ExceptionType` must match the exception thrown (e.g., `ArithmeticException`, `IOException`).
  
**Rules:**
- There should be no lines of code between `try` and `catch` blocks; they must be contiguous.
- A `try` block can have multiple `catch` blocks for different exception types.
- If no `catch` block matches the exception, Java will display its default behavior.

**Example**:
```java
import java.util.*;

class DivideAndSum {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter two integers");
        int a = kb.nextInt();
        int b = kb.nextInt();

        try {
            int c = a / b;  // Division might throw ArithmeticException
            System.out.println("Division = " + c);
        } catch (ArithmeticException ex) {
            System.out.println("Please input a non-zero denominator.");
        }

        int d = a + b;  // Sum, no exception here
        System.out.println("Sum is = " + d);
    }
}
```

---

### Exception Hierarchy in Java:

Java exceptions are divided into two main categories:
1. **`Error`**: Represents serious issues that a program typically cannot recover from (e.g., JVM crashes). These are usually handled by the JVM or OS, not the programmer.
2. **`Exception`**: Represents recoverable conditions that a programmer can handle in their code.

All exceptions in Java are subclasses of the `Throwable` class, which is the root class for all errors and exceptions.

**Common Exception Types**:
- **`RuntimeException`**: A special subclass of `Exception`, typically used for exceptions that occur during program execution, which can be avoided (e.g., `NullPointerException`, `ArithmeticException`).
- **`IOException`**: For input/output errors (e.g., `FileNotFoundException`, `EOFException`).
- **`ArithmeticException`**: Thrown when an arithmetic operation, like division by zero, occurs.
- **`IndexOutOfBoundsException`**: Thrown when an index is out of bounds for arrays or lists (e.g., `ArrayIndexOutOfBoundsException`).
- **`NullPointerException`**: Thrown when you attempt to use a `null` reference where an object is required.
- **`FileNotFoundException`**: Thrown when a file with the specified pathname does not exist.

### Exception Hierarchy:

- **`Throwable`**  
    - **`Error`**
    - **`Exception`**
        - **`RuntimeException`**
            - `ArithmeticException`
            - `NullPointerException`
            - `IndexOutOfBoundsException`
            - `ArrayIndexOutOfBoundsException`
        - `IOException`
            - `FileNotFoundException`
            - `EOFException`
        - `SQLException`
        - `MalformedURLException`

---

### Example: Handling Multiple Exceptions

You can handle different exceptions with multiple `catch` blocks:

```java
try {
    int result = 10 / 0;  // ArithmeticException
    String str = null;
    str.length();         // NullPointerException
} 
catch (ArithmeticException e) {
    System.out.println("Error: Division by zero");
}
catch (NullPointerException e) {
    System.out.println("Error: Null reference");
}
catch (Exception e) {
    System.out.println("General error");
}
```

---

### `finally` Block:
- **Definition**: A block of code that is always executed, regardless of whether an exception occurs or not. It's typically used for cleanup operations, such as closing files or releasing resources.

**Example**:
```java
try {
    int result = 10 / 2;
    System.out.println("Result: " + result);
} 
catch (Exception e) {
    System.out.println("An error occurred");
} 
finally {
    System.out.println("This will always run, regardless of an exception.");
}
```

---

By using proper exception handling, Java programs can provide more robust error recovery, improving user experience and allowing programs to run smoothly even when unexpected situations arise. 

===
===

### Handling Multiple Exceptions in Java

When multiple types of exceptions may occur in a `try` block, you can handle them with multiple `catch` blocks.

#### Key Rules for Multiple `catch` Blocks:
- **Order of Exception Classes**: A parent exception class must always come **after** its child classes in a series of `catch` blocks. Placing the parent class first would prevent the child-specific `catch` blocks from executing, as the parent class reference can catch instances of its child classes.

#### Example of Correct and Incorrect Order:

```java
// Incorrect: Parent IOException before child FileNotFoundException
try {
    // code that may throw exceptions
} 
catch (IOException e) {       // Parent
    // Handle IOException
} 
catch (FileNotFoundException f) { // Child
    // Handle FileNotFoundException
}

// Correct: Child FileNotFoundException before parent IOException
try {
    // code that may throw exceptions
} 
catch (FileNotFoundException f) { // Child
    // Handle FileNotFoundException
} 
catch (IOException e) {       // Parent
    // Handle IOException
}
```

**Hierarchy Examples**:
- `FileNotFoundException` is a subclass of `IOException`.
- `ArithmeticException` is a subclass of `InputMismatchException`.

---

### Obtaining Exception Details

When an exception occurs, Java creates an instance of the relevant exception class, storing details about the error. You can use this object in the `catch` block to retrieve information using the following methods:

#### Common Exception Methods

1. **`getMessage()`**: Returns a detailed error message generated by Java for the specific exception. It is from `Throwable` class

    ```java
    catch (Exception e) {
        System.out.println(e.getMessage()); // Displays specific error message
    }
    ```

2. **`toString()`**: Returns the exception name along with the error message. `toString()` is defined in `Object` and is overridden in exception classes.

    ```java
    catch (Exception e) {
        System.out.println(e.toString()); // Displays exception type and message
    }
    ```

---

### Understanding and Overriding `toString()` Method

The `toString()` method provides a string representation of an object. By default, it returns the class name and hashcode, which is an internal identifier, but this information is rarely helpful to users.

#### Example of Default `toString()` Behavior:

```java
class Person {
    private int age;
    private String name;
    
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}

public class UsePerson {
    public static void main(String[] args) {
        Person p = new Person(21, "Amit");
        System.out.println(p); // Outputs hashcode since toString() is not overridden
    }
}
```

#### Overriding `toString()` to Display Meaningful Information

By overriding `toString()` in `Person`, we can provide details about `Person` objects directly.

```java
class Person {
    private int age;
    private String name;
    
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class UsePerson {
    public static void main(String[] args) {
        Person p = new Person(21, "Amit");
        System.out.println(p); // Outputs "Person{name='Amit', age=21}"
    }
}
```

In exception classes, `toString()` has already been overridden to display the exception type and message, which helps identify the exact nature of the error.

---
===
===
### Using the `throw` Keyword in Java

In Java, the `throw` keyword allows programmers to manually generate exceptions when specific conditions arise, rather than relying solely on Java’s automatic exception handling (like `ArithmeticException` for division by zero). This is useful for enforcing custom rules and validations.

**Syntax**:
```java
throw <ExceptionObject>;
```

**Example**:
```java
if (age < 18) {
    throw new IllegalArgumentException("Age must be 18 or older.");
}
```

### Classification of Exceptions

Java exceptions are divided into two main categories based on whether the programmer must handle them:

#### 1. **Unchecked Exceptions**
   - These are exceptions that Java does not require you to handle.
   - They include `RuntimeException` and all of its subclasses, such as `ArithmeticException` and `NullPointerException`.
   - The program will compile and run even if they are not explicitly handled.

#### 2. **Checked Exceptions**
   - These are exceptions that Java requires the programmer to handle, either by using `try-catch` or by specifying `throws` in the method signature.
   - This is called the **Handle or Declare Rule**.
   - Examples include `IOException`, `SQLException`, and `InterruptedException`.




| Feature               | **Checked Exception**                    | **Unchecked Exception**                       |
|-----------------------|------------------------------------------|-----------------------------------------------|
| **Definition**        | Exceptions checked at **compile time**   | Exceptions checked at **runtime**             |
| **Examples**          | `IOException`, `SQLException`, `FileNotFoundException` | `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException` |
| **Handling Requirement** | **Mandatory** to handle with `try-catch` or `throws` | **Not required** to be handled explicitly    |
| **Error Type**        | Typically **external conditions** beyond the program's control | Typically **logic errors** or **programming mistakes** |
| **Compiler Action**   | **Prevents compilation** if not handled or declared | Allows compilation without handling          |
| **Purpose**           | Ensures external resource access issues are managed | Highlights logical errors the programmer should avoid |
| **Risk of Program Crash** | Lower, as handling is enforced       | Higher, as handling is optional               |
| **Inheritance**       | Extends `Exception` (not `RuntimeException`) | Extends `RuntimeException` or `Error`        |
| **Common Use Cases**  | File handling, database access, network issues | Null references, invalid array indexing, arithmetic errors |



**Example of Checked Exception**:
```java
public static void accept() throws IOException {
    System.out.println("Enter a character:");
    char c = (char) System.in.read();
    System.out.println("You entered: " + c);
}
```

In the example above, `accept()` throws an `IOException`, requiring the calling code to either catch it or declare it using `throws`.


### Programmer-Defined (Custom) Exceptions

Sometimes, no existing Java exception class matches a specific application requirement. In these cases, you can define a custom exception class. For instance, if a banking application enforces a minimum withdrawal amount, a custom exception can help enforce this rule.

#### Steps to Create a Custom Exception:
1. Extend an existing exception class (usually `Exception` or `RuntimeException`).
2. Provide a parameterized constructor to set a custom message.

#### Custom Exception Example

```java
class InvalidNumeratorException extends Exception {
    public InvalidNumeratorException(String msg) {
        super(msg);
    }
}

class Test {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter two numbers:");

        try {
            int a = kb.nextInt();
            int b = kb.nextInt();
            
            if (a <= 0) {
                throw new InvalidNumeratorException("Numerator should be positive");
            }

            int c = a / b;
            System.out.println("Division is: " + c);
        } 
        catch (ArithmeticException ex) {
            System.out.println("Cannot divide by zero.");
        } 
        catch (InvalidNumeratorException ex) {
            System.out.println(ex.getMessage());
        } 
        catch (InputMismatchException ex) {
            System.out.println("Please input digits only.");
        }
    }
}
```

In this example:
- If the numerator `a` is non-positive, `InvalidNumeratorException` is thrown with a custom message.
- Multiple `catch` blocks handle different types of exceptions, allowing specific messages for different error conditions.


![img](Exception_class.png)





| Feature               | **`throw`**                                    | **`throws`**                                      |
|-----------------------|-----------------------------------------------|---------------------------------------------------|
| **Purpose**           | Used to **explicitly throw** an exception     | Declares that a method **can throw** specified exceptions |
| **Usage**             | Inside a method body                          | In the method signature                           |
| **Syntax**            | `throw new ExceptionType("message");`         | `public void methodName() throws ExceptionType`   |
| **Number of Exceptions** | Throws **one specific exception** instance | Can declare **multiple exceptions** separated by commas |
| **Flow Control**      | Terminates execution within the method at that point | Signals to callers that they need to handle or declare the exception |
| **Type Requirement**  | Requires an instance of `Throwable`           | Lists checked exceptions that the method might throw |
| **Checked Exception Requirement** | Not required to handle checked exceptions | Mandatory for checked exceptions if method may throw them |
| **Common Use**        | To throw custom or pre-defined exceptions under specific conditions | Used in method declarations to indicate potential exceptions to caller |
