
### **Wrapper Classes in Java**

#### **Overview**

Wrapper classes are used to convert primitive types into objects and vice versa. Each primitive type (e.g., `int`, `boolean`, `char`) has a corresponding wrapper class (`Integer`, `Boolean`, `Character`).

#### **Converting Between Primitives and Wrapper Objects**

* **From Primitive to Wrapper Object:**

  * Use `valueOf()` method for conversion:

    ```java
    int a = 34;
    Integer b = Integer.valueOf(a);  // Convert int to Integer
    String str = "123.34";
    Float f = Float.valueOf(str);    // Convert String to Float
    ```

* **From Wrapper Object to Primitive:**

  * Use `xxxValue()` method:

    ```java
    int c = b.intValue();  // Convert Integer to int
    ```

* **From String to Primitive:**

  * Use `parseXXX()` method (e.g., `parseInt()`, `parseDouble()`):

    ```java
    int age = Integer.parseInt("25");  // Convert String to int
    ```

* **From String to Wrapper Object:**

  * Use `valueOf()` method:

    ```java
    Integer i = Integer.valueOf("10");
    ```

* **From Primitive to String:**

  * Use `toString()` method:

    ```java
    String s = Integer.toString(10);  // Convert int to String
    ```

* **From Wrapper Object to String:**

  * Use `toString()` method:

    ```java
    String s = i.toString();  // Convert Integer to String
    ```

#### **Constants and Utility Methods in Wrapper Classes**

* **Wrapper Classes Provide Constants:**

  * `Integer.MIN_VALUE`, `Integer.MAX_VALUE`
  * `Short.MIN_VALUE`, `Short.MAX_VALUE`

* **Utility Methods:**

  1. `valueOf()`: Returns cached or new instances without creating an object each time.
  2. `xxxValue()`: Converts a wrapper object to a primitive (`byteValue()`, `shortValue()`, `intValue()`, etc.).
  3. `parseXXX()`: Converts a string to a primitive value.
  4. `toString()`: Converts a wrapper object to its string representation.

#### **Immutability of Wrapper Classes**

* Wrapper classes (like `Integer`, `Double`, `Boolean`) and `BigDecimal` are **immutable**.

* **BigDecimal Example:**

  ```java
  BigDecimal bd = new BigDecimal("12.99");
  bd = bd.add(new BigDecimal("5.01")).setScale(2, RoundingMode.HALF_UP);  // Chaining methods
  ```

* **BigDecimal Methods:**

  * `.add()`, `.multiply()`, `.setScale()`
  * **RoundingMode:** `RoundingMode.HALF_UP`, `RoundingMode.FLOOR`, etc. It is enum

#### ** boxing and Unboxing**

* **Autoboxing:** Conversion from **primitive** to wrapper object.

  ```java
  Integer i = 10;  // int is automatically boxed into Integer
  ```

* **Unboxing:** Conversion from wrapper object to primitive.

  ```java
  int x = i;  // Integer is automatically unboxed to int
  ```

#### **Special Considerations**

* **Boolean Constructor:**

  * `Boolean("false")` or `Boolean("False")` returns `false`.
  * Any non-null, non-"false" value (e.g., `"true"`, `"True"`) returns `true`.

* **Invalid Input Examples:**

  * `new Integer("ten")` throws `NumberFormatException`.
  * `Boolean(null)` returns `false`.

* **String to Primitive Conversion:**

  * Use `parseXXX()` methods (e.g., `Integer.parseInt()`).
  * **Note**: long /int cannot be converted directly to a `String` without conversion methods.

* **Method Chaining in BigDecimal:**

  ```java
  BigDecimal result = bigDecimal1.add(bigDecimal2).setScale(2, RoundingMode.HALF_UP);
  ```

* **Radix Value for Parsing Integers:**

  ```java
  Integer i = Integer.valueOf("B2B", 16);  // Converts hexadecimal "B2B" to decimal 2859
  ```

#### **BigDecimal vs BigInteger**

* **BigDecimal** is used for precise decimal arithmetic, while **BigInteger** is better suited for large integers.

  * Use **BigInteger** when dealing with integers that exceed the limits of primitive types.

#### **Summary: Conversion Flow**

| **Type**                   | **Method**   |
| -------------------------- | ------------ |
| String → Primitive         | `parseXXX()` |
| String → Wrapper Object    | `valueOf()`  |
| Primitive → String         | `toString()` |
| Primitive → Wrapper Object | `valueOf()`  |
| Wrapper Object → String    | `toString()` |
| Wrapper Object → Primitive | `xxxValue()` |

---
