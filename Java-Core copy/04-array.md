### 1. **Basics of Array Declaration and Initialization**

* **Declaration:**

  ```java
  int[] x;          // Recommended
  int []x;          // Valid
  int x[];          // Valid
  int x [];         // Valid (spacing ignored)
  ```

* **Invalid:**

  ```java
  int[5] x;         // ❌ Compile-time error – can't specify size in declaration
  ```

* **Initialization:**

  ```java
  x = new int[3];   // Creates array with default values (0 for int)
  ```

* **Array Properties:**

  * Fixed in size
  * Homogeneous (same data type only)
  * Arrays are **objects** in Java
  * Default values:
    * `int` → 0, `boolean` → false, `Object` → null

---

### 2. **Dimensions and Valid Syntax**

* **1D Arrays:**

  ```java
  int[] a, b;         // Both 1D
  int[] a, b[], c[];  // a: 1D, b & c: 2D
  ```

* **2D Arrays:**

  ```java
  int[][] a, b;       // Both 2D
  int[][] a, b[];     // a: 2D, b: 3D
  ```

* **Invalid Declarations:**

  ```java
  int[] a, []b;             // ❌ Invalid
  int[] a, b, []c, d[];     // ❌ Invalid
  ```

---

### 3. **Array Construction & Class Info**

* Every array is an **object**:

  ```java
  int[] x = new int[3];
  System.out.println(x.getClass().getName());  // Output: [I
  ```

* **Array Type Codes:**

  | Type        | Output from `getClass().getName()` |
  | ----------- | ---------------------------------- |
  | `int[]`     | `[I`                               |
  | `int[][]`   | `[[I`                              |
  | `long[]`    | `[J`                               |
  | `byte[]`    | `[B`                               |
  | `boolean[]` | `[Z`                               |
  | `Object[]`  | `[Ljava.lang.Object;`              |
  | `Student[]` | `[LStudent;` (`L` only for non-primitives) |

---

### 5. **Runtime Exceptions**

* `int[] x = new int[-3];`
  ✅ Compile-time: OK
  ❌ Runtime: `NegativeArraySizeException`

* **Accessing invalid index:**

  ```java
  arr[10]; // ArrayIndexOutOfBoundsException
  ```

---

### 6. **Array Length vs String Length**

| Expression     | Description      |
| -------------- | ---------------- |
| `arr.length`   | ✅ Array property |
| `str.length()` | ✅ String method  |
| `arr.length()` | ❌ Error          |
| `str.length`   | ❌ Error          |

---

### 7. **Jagged vs Rectangular Arrays**

* **Rectangular (Same columns per row):**

  ```java
  int[][] arr = new int[3][4];  // 3 rows, 4 columns each
  ```

* **Jagged (Different columns):**

  ```java
  int[][] arr = new int[3][];
  arr[0] = new int[2];
  arr[1] = new int[4];
  arr[2] = new int[1];
  ```

✅ Also valid:

```java
int[][] arr = {
    {10, 20},
    {30, 40, 50}
};
```

---

### 8. **Anonymous Arrays**

* Used for one-time usage:

  ```java
  new int[] {10, 20, 30};  
  customMethod(new int[] {1, 2, 3});
  ```

---

### 9. **Command-Line Arguments**

* Passed to `main(String[] args)`
* `args.length == 0` is valid

Example:

```bash
$ java Test A B C
```

---

### 10. **Array Reference Assignments**

* Allowed if **type and dimensions** match:

  ```java
  int[] a2 = {1, 2, 3};
  int[] a3 = {4, 5};
  a3 = a2; // ✅ Valid
  ```

* ❌ Size doesn't need to match

* ❌ Assigning `int[][]` to `int[]` → Compile-time error

---

### 11. **Null and Exceptions**

```java
    int [][] x = new int[4][];

String str;
str.concat("abc");  // NullPointerException
str.length();       // NullPointerException

Student[] students = new Student[3];
students[1] = new Student("X");
students[2] = new Student("Y");
for (Student s : students)
    System.out.println(s.name);  // NullPointerException for students[0]
```

---

### 12. **Object Arrays**

```java
Object[] a = new Object[10];
a[0] = new Object();
a[1] = new String("Kumar");    // Valid
a[2] = "Kumar";      // Valid

Runnable[] r = new Runnable[10];
```

---

### 13. **Maximum Array Size**

* Max array size: `Integer.MAX_VALUE` → 2,147,483,647
* Practical limits may cause `OutOfMemoryError` before that

---

### 14. **Garbage Collection & Arrays**

```java
int[] A = {10, 20};
int[] B = {30, 40};
A = B;  // `{10, 20}` is now unreachable → eligible for GC
```

---

### 🔑 **Shortcuts to Remember**

* Left side = reference; Right side = object.
* `int[] x` → 1D int array reference
* `int[][] x = new int[4][];` → Valid (Jagged)
* Starting dimension **must be provided** when allocating:

  ```java
  new int[][3]; // ❌ Error
  new int[0][]; // Valid
  new int[0][2]; // ❌ Error
  ```

---

### 15. **Arrays vs ArrayList**

| Feature     | `Array`            | `ArrayList` (java.util)       |
| ----------- | ------------------ | ----------------------------- |
| Size        | Fixed              | Dynamic                       |
| Stores      | Primitives/Objects | Objects only (use wrappers)   |
| Syntax      | `[]`               | `.add()`, `.get()`, `.size()` |
| Performance | Faster             | Slight overhead (resizing)    |

---

### 🔹 **Array Declaration without Instantiation**

```java
int[] x;
int[] y;
```

✅ **Valid declarations** without initialization. Arrays can be declared without size or instantiation. Instantiation must be done before use.

---

### 🔹 **Array Construction and Object Nature**

```java
int[] x = new int[3];        // Valid: default values = 0
System.out.println(x);       // Output: [I@<hashcode>
```

* Arrays are **objects**, even if holding primitives.
* Stored on **heap**, referenced by variables.



🧠 **Note**: These class names are internal and **not usually used directly by programmers**, but useful for debugging and introspection.

---

### 🔹 **Usage of `java.util.Arrays`**

* `java.util.Arrays` is a utility class for arrays.
* Common methods:

  * `Arrays.sort(array)`: Only works for array not lists
  * `Arrays.toString(array)`
  * `Arrays.equals(array1, array2)`

---



### 🔹 **Array Element Type Clarification**

```java
int[] a[], b;     // a: 2D, b: 1D
```

🧠 Declaring multiple arrays in one line can be confusing. Use clear syntax:

```java
int[][] a;
int[] b;
```

---

### 🔹 **Initializer Restrictions**

```java
int[] a = new int[3] {1, 2, 3};  // ❌ Invalid: cannot combine size + initializer
int[] x;
x = {10, 20, 30};               // ❌ Invalid: must use `new` keyword
```

✅ Valid forms:

```java
int[] a = {1, 2, 3};              // Compact init
int[] b = new int[] {1, 2, 3};    // Anonymous array
```

---

### 🔹 **Assigning `null` to a primitive**

```java
int x = null;    // ❌ Compile-time error: primitives can't be null
```

✅ Only reference types (arrays, objects) can be `null`.

---

### 🔹 **Array Output in `System.out.println()`**

```java
int[] y = {10, 20};
System.out.println(y); // Output: [I@<hashcode>
```

* All objects inherit `toString()` from `Object` → prints `class@hash`
* Override or use `Arrays.toString()` to print content.

---

### 🔹 **Anonymous Arrays**

* For **single-use**, inline creation:

```java
customMethod(new int[] {10, 20, 30});
```

* Cannot assign to a named reference without `new`:

```java
int[] x = {1, 2};        // ✅
int[] y;
y = {1, 2};   z           // ❌
```

---

### 🔹 **Custom Class Arrays**

```java
Student[] s1 = new Student[5];
System.out.println(s1);    // Output: [LStudent;@<hash>
```

* Arrays of custom types print with `L<classname>;` pattern.
* `s1[0]` defaults to `null`.
* Accessing field on `null` → `NullPointerException`.

---

### 🔹 **Jagged Array Creation – Shortcut Logic**

* **Left Side** = Reference Type
* **Right Side** = Object (with memory)

```java
int[][] arr;                   // Reference
arr = new int[2][];            // Object creation (first dim fixed)
arr[0] = new int[3];           // Second dim assigned separately
```

---

### 🔹 **Conceptual Shortcut**

🧠 **Think in terms of Reference vs Object:**

```java
<data_type>[][] ref;  // Reference
ref = new <data_type>[rows][cols];  // Object
```

✅ Useful for:

* Memory management
* Exception handling
* Jagged arrays

---

### 🧠 **Final Notes (Quick Memory Boosters)**

* `int[][] arr = new int[3][4];` → Rectangular
* `int[][] arr = new int[3][];` → Jagged
* `int[] arr = new int[-2];` → Runtime Error: `NegativeArraySizeException`
* `arr.length` (no brackets!) for arrays
* `str.length()` for Strings

---

