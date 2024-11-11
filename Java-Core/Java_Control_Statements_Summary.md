**Java Control Statements Summary**

1. **Switch Statement**  
   The `switch` statement is a decision-control structure similar to `if`, used to match a variable against multiple cases.
   
   **Syntax**:
   ```java
   switch(variable_or_expression) {
       case value1:
           // Statements
           break;
       case value2:
           // Statements
           break;
       ...
       default:
           // Statements
   }
   ```
   - Supported types: `byte`, `short`, `char`, `int`. From Java 7 onward, `String` and enumerations are also allowed.
   - **Example**:
     ```java
     int month = 8;
     switch (month) {
         case 1: System.out.println("January"); break;
         case 2: System.out.println("February"); break;
         ...
         default: System.out.println("Invalid Month");
     }
     ```
   - **Clubbing Cases**: Multiple cases can be combined if they share the same outcome.
     ```java
     switch(value) {
         case 1: case 2: case 3:
             // Statements
             break;
         case 4: case 5: case 6:
             // Statements
             break;
         default:
             // Statements
     }
     ```

2. **Ternary Operator**  
   The ternary operator provides a concise alternative to `if-else` or simple `switch` cases, and can be used on the right-hand side of expressions.

   **Syntax**:
   ```java
   variable = (condition) ? true_value : false_value;
   ```

3. **Loops**  
   Loops control repeated execution of code blocks and are categorized as follows:
   - **Entry-Controlled Loops**: Condition checked before loop execution, e.g., `while` and `for` loops.
   - **Exit-Controlled Loops**: Condition checked after loop execution, e.g., `do-while` loop, ensuring the loop executes at least once.
     
     **Example**:
     ```java
     do {
         // Statements
     } while(condition);
     ```

4. **Labeled Break and Continue**  
   Use labeled `break` for exiting nested loops entirely.
   
   **Syntax**:
   ```java
   <labelName>:
   for(init; condition; stmt) {
       for(init; condition; stmt) {
           // Statements
           break <labelName>;
       }
   }
   ```

5. **Input via Scanner Class**  
   The `Scanner` class enables input handling from various sources, such as the keyboard.
   
   **Initialization**:
   ```java
   Scanner kb = new Scanner(System.in);
   ```

   **Common Methods**:
   - For primitive types: `nextInt()`, `nextShort()`, `nextLong()`, `nextFloat()`, `nextByte()`, `nextDouble()`, `nextBoolean()`.
   - For strings:
     - `next()`: Reads a single word (no spaces).
     - `nextLine()`: Reads an entire line (allows spaces).

    