
### 2. **Operator Precedence and Associativity**
   - **Examples**:
     - Assignment has the lowest precedence.
   - **Associativity**:
     - Most operators are left-associative (evaluated left to right).
     - Assignment operators (like `=`, `+=`) are right-associative.

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
     - Example: `Math.min(10, 20)` returns `10`.
   - **Signum**:
     - `Math.signum(double a)`
     - Returns `-1.0` if the value is negative, `1.0` if positive, and `0.0` if zero.

### 3. **Exponential and Logarithmic Functions**
  All mentioned return in **double**
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
   2-decimal rounding:
    `Math.round((double) a / b * 100) / 100.0;`

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

- **Convert String to Primitive**:
    - Use `Integer.parseInt()`, `Double.parseDouble()`, etc., to parse a `String` to a corresponding data type.
    - Example:
        ```java
        String str = "456";
        int num = Integer.parseInt(str); // Converts String "456" to int 456
        ```

**Note**: This conversion will throw a `NumberFormatException` if the string is not formatted correctly.



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
