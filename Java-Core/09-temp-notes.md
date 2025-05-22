
### 2. **Operator Precedence and Associativity**
   - Know the order in which operations are evaluated, especially when multiple operators are in a single expression.
   - **Examples**:
     - Arithmetic operators (`*`, `/`, `%` have higher precedence than `+` and `-`)
     - Logical operators (`&&` has higher precedence than `||`)
     - Assignment has the lowest precedence.
   - **Associativity**:
     - Most operators are left-associative (evaluated left to right).
     - Assignment operators (like `=`, `+=`) are right-associative.


---

### 4. **String Manipulations and String Pool**
   - **String Pool**: `String` literals are stored in a special pool; identical literals point to the same memory location.
   - **Common Methods**: `.length()`, `.charAt()`, `.substring()`, `.indexOf()`, `.toLowerCase()`, `.toUpperCase()`, `.equals()` vs. `==`

   .equals -> check value
   == -> by reference

---

### 5. **Wrapper Classes and Autoboxing/Unboxing**
   - **Wrapper Classes**: `Integer`, `Double`, `Character`, etc., allow primitives to be used as objects.
   - **Autoboxing/Unboxing**: Automatic conversion between primitives and their wrapper classes (e.g., `int` to `Integer` and vice versa).

---



### 7. **Method Overloading and Overriding**
   - **Overriding**
   - **`@Override` Annotation**: Ensures the method is correctly overridden.

---

---

### 11. **Polymorphism and Type Casting**
   - **Upcasting**: Implicit casting from a subclass type to a superclass type.
   - **Downcasting**: Explicit casting from a superclass type to a subclass type (requires `instanceof` check).
   - **Method Binding**: Method calls are resolved at runtime based on the actual object, not the reference type.

---
---

### 14. **Java Collections Framework Basics**
   - Know commonly used classes like `ArrayList`, `HashMap`, `HashSet`, `LinkedList`.
   - **Common Methods**:
     - **List**: `.add()`, `.remove()`, `.get()`, `.size()`
     - **Map**: `.put()`, `.get()`, `.keySet()`, `.values()`
   - **Iteration Techniques**: `for-each`, `Iterator`, `for` loop.

---

### 15. **File I/O Basics**
   - **Classes**: `File`, `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`
   - **Streams**: Basic knowledge of `InputStream` and `OutputStream` classes.
   - **Exception Handling**: Most file I/O operations throw checked exceptions (`IOException`).

---

### 16. **Basic Recursion**
   - Understand how recursive calls work and how to write a base case to prevent infinite loops.
   - Simple examples: factorial calculation, Fibonacci sequence, sum of natural numbers.

---

### 17. **`final`, `finally`, `finalize` Differences**
   - **`final`**: Can be used with classes, methods, or variables to prevent modification.
   - **`finally`**: A block in exception handling to ensure code executes regardless of exceptions.
   - **`finalize`**: A method in `Object` class called by garbage collector before destroying an object.

---

### 18. **Garbage Collection Basics**
   - **Purpose**: Automatic memory management.
   - **Reference Types**: Strong, weak, soft, phantom references.
   - **`System.gc()`**: Suggests garbage collection but does not guarantee it.

---

### 19. **Java’s `this` and `super` Keywords**
   - **`this`**: Refers to the current object; useful for distinguishing between instance variables and parameters.
   - **`super`**: Refers to the superclass’s constructor or method; useful for accessing superclass fields or methods.

---

### 20. **Math Library and Common Utility Methods**
   - Familiarize yourself with common `Math` methods like `Math.abs()`, `Math.sqrt()`, `Math.pow()`, `Math.random()`.
   - Useful in programs requiring calculations or randomization.

---

### 1. **Constants in Math Class**
   - **`Math.PI`**: Represents the value of π (3.14159...), useful for trigonometric calculations.
   - **`Math.E`**: Represents the base of the natural logarithm (approximately 2.71828).

### 2. **Basic Math Functions**
   - **Absolute Value**:
     - `Math.abs(int a)`, `Math.abs(double a)`, etc. 
     - Returns the absolute (non-negative) value of the specified number.
     - Example: `Math.abs(-5)` returns `5`.
   - **Minimum and Maximum**:
     - `Math.min(a, b)` and `Math.max(a, b)`
     - Returns the smaller or larger of two numbers.
     - Example: `Math.min(10, 20)` returns `10`.
   - **Signum**:
     - `Math.signum(double a)`
     - Returns `-1.0` if the value is negative, `1.0` if positive, and `0.0` if zero.

### 3. **Exponential and Logarithmic Functions**
   - **Power**:
     - `Math.pow(double base, double exponent)`
     - Raises the base to the power of the exponent.
     - Example: `Math.pow(2, 3)` returns `8.0`.
   - **Square Root**:
     - `Math.sqrt(double a)`
     - Returns the square root of a number.
     - Example: `Math.sqrt(9)` returns `3.0`.
   - **Cube Root**:
     - `Math.cbrt(double a)`
     - Returns the cube root of a number.
     - Example: `Math.cbrt(8)` returns `2.0`.
   - **Exponential Function**:
     - `Math.exp(double a)`
     - Returns Euler’s number `e` raised to the power of the argument.
     - Example: `Math.exp(1)` returns approximately `2.718`.
   - **Logarithmic Functions**:
     - `Math.log(double a)`: Returns the natural logarithm (base `e`).
     - `Math.log10(double a)`: Returns the base-10 logarithm.
     - Example: `Math.log(2.718)` returns approximately `1.0`.

### 4. **Trigonometric Functions**
   - **Standard Trigonometric Functions**:
     - `Math.sin(double angle)`, `Math.cos(double angle)`, `Math.tan(double angle)`
     - Accepts an angle in radians and returns the sine, cosine, or tangent, respectively.
     - Example: `Math.sin(Math.PI / 2)` returns `1.0`.
   - **Inverse Trigonometric Functions**:
     - `Math.asin(double a)`, `Math.acos(double a)`, `Math.atan(double a)`
     - Returns the arc sine, arc cosine, or arc tangent, respectively, in radians.
     - Example: `Math.asin(1.0)` returns `Math.PI / 2`.
   - **Angle Conversion**:
     - `Math.toRadians(double angleDegrees)`: Converts an angle from degrees to radians.
     - `Math.toDegrees(double angleRadians)`: Converts an angle from radians to degrees.
     - Example: `Math.toRadians(180)` returns `Math.PI`.

### 5. **Rounding Functions**
   - **Round to Nearest Integer**:
     - `Math.round(double a)` or `Math.round(float a)`
     - Rounds to the nearest integer, returning an `int` for `float` and `long` for `double`.
     - Example: `Math.round(2.7)` returns `3`.
   - **Ceiling and Floor**:
     - `Math.ceil(double a)`: Returns the smallest integer greater than or equal to `a` as a `double`.
     - `Math.floor(double a)`: Returns the largest integer less than or equal to `a` as a `double`.
     - Example: `Math.ceil(2.3)` returns `3.0`, `Math.floor(2.9)` returns `2.0`.
   - **Truncate Decimal Places**:
     - `Math.rint(double a)`: Returns the closest integer as a `double`, choosing the even integer if two are equally close.
     - Example: `Math.rint(2.5)` returns `2.0`, `Math.rint(3.5)` returns `4.0`.

### 6. **Random Number Generation**
   - **`Math.random()`**: Returns a `double` value between `0.0` (inclusive) and `1.0` (exclusive).
   - Example of generating an integer between 0 and 9:
     ```java
     int randomNum = (int)(Math.random() * 10);
     ```

### 7. **Special Functions**
   - **Hypotenuse Calculation**:
     - `Math.hypot(double x, double y)`
     - Returns `sqrt(x^2 + y^2)` without intermediate overflow or underflow.
     - Example: `Math.hypot(3, 4)` returns `5.0`.
   - **Copy Sign**:
     - `Math.copySign(double magnitude, double sign)`
     - Returns the first argument with the sign of the second argument.
     - Example: `Math.copySign(1.0, -2.0)` returns `-1.0`.
   - **Exact Arithmetic**:
     - `Math.addExact(int x, int y)`, `Math.subtractExact(int x, int y)`, `Math.multiplyExact(int x, int y)`, `Math.negateExact(int x)`
     - Throws an `ArithmeticException` if an overflow occurs, useful for safe integer arithmetic.

### Example of Common Usage
```java
public class MathExamples {
    public static void main(String[] args) {
        // Using constants
        System.out.println("PI: " + Math.PI);
        
        // Basic operations
        System.out.println("Absolute value of -10: " + Math.abs(-10));
        System.out.println("Max of 10 and 20: " + Math.max(10, 20));

        // Exponential and logarithmic functions
        System.out.println("2^3: " + Math.pow(2, 3));
        System.out.println("Square root of 16: " + Math.sqrt(16));
        
        // Trigonometric functions
        System.out.println("sin(PI/2): " + Math.sin(Math.PI / 2));
        
        // Rounding functions
        System.out.println("Ceiling of 2.3: " + Math.ceil(2.3));
        System.out.println("Floor of 2.7: " + Math.floor(2.7));
        
        // Random number
        System.out.println("Random number: " + Math.random());
    }
}
```

This `Math` class provides most of the mathematical functionality you’ll need directly, making it highly efficient for both simple and complex calculations in Java.


====
---

---

### 3. **String Conversion**
Java provides convenient ways to convert other data types to `String` and vice versa:

- **Convert Primitive to String**:
    - `String.valueOf(dataType)`: Converts a primitive data type to a `String`.
    - Example:
        ```java
        int number = 123;
        String strNumber = String.valueOf(number); // Converts int to String "123"
        ```
    - **Alternative**: Concatenation with an empty string also converts to `String`.
        ```java
        String strNumber = number + ""; // Also gives "123"
        ```

- **Convert String to Primitive**:
    - Use `Integer.parseInt()`, `Double.parseDouble()`, etc., to parse a `String` to a corresponding data type.
    - Example:
        ```java
        String str = "456";
        int num = Integer.parseInt(str); // Converts String "456" to int 456
        ```

**Note**: This conversion will throw a `NumberFormatException` if the string is not formatted correctly.

---

### 4. **Wrapper Class Conversion**
Java provides wrapper classes (like `Integer`, `Double`, etc.) for converting between primitives and objects. These are especially useful for data conversion in collections and generics.

- **Boxing** (primitive to wrapper):
    - Java automatically wraps primitives into objects of their corresponding wrapper class.
    - Example:
        ```java
        int num = 5;
        Integer numObj = num; // Auto-boxing
        ```

- **Unboxing** (wrapper to primitive):
    - Wrapper objects are automatically converted back to primitives.
    - Example:
        ```java
        Integer numObj = 5;
        int num = numObj; // Auto-unboxing
        ```

---

### 5. **Conversion Between Numeric Types and Characters**
- **Convert `int` to `char`**:
    - You can cast an integer to a character, which will convert it to the corresponding Unicode character.
    - Example:
        ```java
        int num = 65;
        char letter = (char) num; // Converts to 'A' (Unicode 65)
        ```

- **Convert `char` to `int`**:
    - Java automatically converts characters to their ASCII/Unicode integer values.
    - Example:
        ```java
        char letter = 'A';
        int num = letter; // Converts to 65
        ```

---

### 6. **Common Conversions with `Math`**
- **Rounding Decimal Numbers**:
    - `Math.round(double a)`: Converts a `double` to the nearest `long`.
    - Example:
        ```java
        double num = 9.65;
        long rounded = Math.round(num); // Rounds to 10
        ```

---
