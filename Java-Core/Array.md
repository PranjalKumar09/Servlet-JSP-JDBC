
### Arrays in Java

#### **Overview**
- An **array** is a collection of elements of the same data type (primitive or non-primitive).
  - Example: 
    - Integer array → `int[] arr = {1, 2, 3};`
    - String array → `String[] names = {"Alice", "Bob"};`

#### **Array Characteristics in Java**
1. **Arrays are treated as objects.**
   - Created using the `new` keyword.
   - Default values are assigned to array elements:
     - 0 for numeric types.
     - `false` for `boolean`.
     - `null` for reference types.

2. **Syntax**
   - **Declaration** (creating array reference):
     ```java
     <data type>[] <array reference name>;
     OR
     <data type> <array reference name>[];
     ```
     Example:
     ```java
     int[] arr;  // Preferred style
     ```
   - **Initialization** (allocating memory):
     ```java
     <array reference> = new <data type>[size];
     ```
     Example:
     ```java
     int[] arr = new int[10];
     ```
   - **Direct Initialization**:
     ```java
     int[] arr = {10, 20, 30, 40};
     ```

3. **Variable Size**
   - Array size can be defined using a variable:
     ```java
     int n = 10;
     int[] arr = new int[n];
     ```
   - Negative size results in `NegativeArraySizeException`.

#### **Garbage Collection in Java**
- **Garbage blocks**: Dynamic memory blocks no longer referenced.
  - Example:
    ```java
    int[] A = {10, 20};
    int[] B = {30, 40};
    A = B;  // Previous A becomes garbage.
    ```
- **Garbage Collector**:
  - In java dynamic blocks or objects have undetermined life time, JVM automatically identifies and collects garbage blocks.
  - Deallocation is managed exclusively by JVM, not the programmer.
  

#### **`length` Property**
-  **Properties** are **special methods** which can we called without using parenthesis. Programmers cannot develop properties in
their class.
- Used to determine the size of an array.
- **Read-only**: Cannot be modified.
- Example:
  ```java
  int[] arr = {10, 20, 30};
  System.out.println("Size of array is " + arr.length);
  ```

#### **Example: Using `length` Property**
```java
class LengthPropertyDemo {
    public static void main(String[] args) {
        int n = args.length;
        int sum = 0;

        if (n <= 1) {
            System.out.println("Please enter at least 2 numbers");
            System.exit(0);
        }

        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(args[i]);
        }
        System.out.println("Sum is " + sum);
    }
}
```

#### **Key Points**
1. Arrays in Java have **undetermined lifetime** due to JVM-managed garbage collection.
2. The `length` property is accessible on all array types and ensures dynamic size checking.
3. Default values in arrays prevent uninitialized memory errors.



### **Enhanced `for` Loop (Introduced in Java 5.0)**

#### **Syntax**
```java
for (<data type> <variable> : <array reference>) {
    // Read-only operations
}
```

- **Key Points**:
  1. The variable's **data type** must match the array's data type.
  2. Mainly used for **read-only operations** (no modifications allowed).

#### **Example**
```java
class EnhancedForDemo {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        for (int x : arr) {
            System.out.println(x);
        }
    }
}
```

#### **Drawbacks**
1. **Traversal starts at the first element** and continues to the last; cannot reverse traverse.
2. Loop cannot terminate midway or exit early.
3. **Variable scope**: The loop variable must be declared inside the loop header.
4. Cannot perform array **modifications** (only allows traversal).

---

### **Two-Dimensional Arrays in Java**

#### **Types of 2D Arrays**
1. **Rectangular Arrays**:
   - All rows have the **same number of columns**.
   - Example:
     ```java
     int[][] arr = new int[3][4]; // 3 rows, 4 columns
     ```

2. **Jagged Arrays**:
   - Rows can have **different numbers of columns**.
   - Example:
     ```java
     int[][] arr = new int[3][];
     arr[0] = new int[2]; // Row 0 has 2 columns
     arr[1] = new int[4]; // Row 1 has 4 columns
     arr[2] = new int[3]; // Row 2 has 3 columns
     ```

#### **Declaration**
- **Creating reference**:
  ```java
  <data type>[][] <array reference>;
  OR
  <data type> <array reference>[][]; 
  ```
  Example:
  ```java
  int[][] arr;
  ```

- **Creating array object**:
  ```java
  arr = new <data type>[rows][columns];
  ```
  Example:
  ```java
  int[][] arr = new int[3][4]; // 3 rows, 4 columns
  ```

---

### **Working with 2D Arrays**

#### **Output of Code Snippet**
```java
int[][] arr = new int[3][4];

1. System.out.println(arr.length);    // Output: 3 (number of rows)
2. System.out.println(arr[0].length); // Output: 4 (columns in row 0)
3. System.out.println(arr[0][0].length); // Error: arr[0][0] is an int, not an array
```

#### **Example Program**
```java
import java.util.Scanner;

class TwoDDemo {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int[][] arr;
        int sum = 0;

        System.out.println("Enter number of Rows and Columns");
        int r = kb.nextInt();
        int c = kb.nextInt();

        arr = new int[r][c];

        // Input elements
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter numbers in row " + (i + 1));
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = kb.nextInt();
                sum += arr[i][j];
            }
        }

        // Display array elements
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        // Calculate and display sum and average
        System.out.println("\nSum of all numbers is " + sum);
        System.out.println("Average is " + ((float) sum / (r * c)));
    }
}
```

---

### **Key Points Summary**
**2D Arrays**:
   - Can be **rectangular** (uniform rows and columns) or **jagged** (varying column sizes per row).
   - `arr.length`: Number of rows.
   - `arr[0].length`: Columns in the first row.
   - `arr[0][0].length`: Error, as individual elements are not arrays.


### Jagged Arrays in Java

---

### **Jagged Arrays Overview**
- A **jagged array** is a 2D array where rows can have **different numbers of columns**.
- Memory is allocated row by row, making jagged arrays flexible and efficient for irregular datasets.

#### **Key Characteristics**
1. Jagged arrays are declared like rectangular arrays but rows are assigned separately.
2. Each row is an independent 1D array that can have a different size.

#### **Declaration and Initialization**

1. **Step-by-step Initialization**
   ```java
   int[][] arr = new int[2][];  // Declare a jagged array with 2 rows
   arr[0] = new int[2];         // First row has 2 columns
   arr[1] = new int[3];         // Second row has 3 columns

   // Assigning values
   arr[0][0] = 10;
   arr[0][1] = 20;
   arr[1][0] = 30;
   arr[1][1] = 40;
   arr[1][2] = 50;
   ```

2. **Inline Initialization**
   ```java
   int[][] arr = new int[][] {
       {10, 20, 30, 40}, // Row 0
       {50, 60, 70}      // Row 1
   };
   ```

3. **Compact Notation**
   ```java
   int[][] arr = {
       {10, 20, 30},      // Row 0
       {50, 60},          // Row 1
       {70, 80, 90}       // Row 2
   };
   ```

---

### **Code Example: Jagged Array Input and Processing**

```java
import java.util.Scanner;

class JaggedDemo {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        // Declare the jagged array
        int[][] arr;

        // Input number of rows
        System.out.println("Enter number of Rows:");
        int r = kb.nextInt();
        arr = new int[r][];

        // Input columns and values for each row
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter number of columns in row " + (i + 1) + ":");
            int c = kb.nextInt();
            arr[i] = new int[c]; // Initialize row with c columns

            System.out.println("Enter values for row " + (i + 1) + ":");
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = kb.nextInt();
            }
        }

        // Display array and compute sum for each row
        System.out.println("\nJagged Array Contents:");
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
                sum += arr[i][j];
            }
            System.out.println(" Sum is " + sum + "\n");
        }
    }
}
```

---

### **Key Notes**
1. **Memory Allocation**:
   - Memory for each row is allocated individually.
   - Example:
     ```java
     arr[0] = new int[3];  // First row: 3 columns
     arr[1] = new int[5];  // Second row: 5 columns
     ```

2. **Access and Traversal**:
   - Access elements as in regular 2D arrays: `arr[row][column]`.
   - Use nested loops for traversal:
     ```java
     for (int i = 0; i < arr.length; i++) {
         for (int j = 0; j < arr[i].length; j++) {
             System.out.print(arr[i][j] + " ");
         }
     }
     ```

3. **Row-Level Sum**:
   - Sum of row elements can be computed in the inner loop:
     ```java
     int sum = 0;
     for (int j = 0; j < arr[i].length; j++) {
         sum += arr[i][j];
     }
     System.out.println("Row sum is " + sum);
     ```

4. **Dynamic Input**:
   - Use a `Scanner` for runtime input of rows, columns, and elements.
   - Example: Accept variable-sized rows dynamically, as in the example program.

---

### **Output of Example Program**
**Input**:
```
Enter number of Rows:
2
Enter number of columns in row 1:
3
Enter values for row 1:
10 20 30
Enter number of columns in row 2:
2
Enter values for row 2:
40 50
```

**Output**:
```
Jagged Array Contents:
10 20 30  Sum is 60

40 50  Sum is 90
```

---

### **Benefits of Jagged Arrays**
- **Flexibility**: Suitable for scenarios with varying row sizes.
- **Memory Efficiency**: Allocates memory only for required elements.

### **Limitations**
- Slightly more complex to initialize and handle compared to rectangular arrays.