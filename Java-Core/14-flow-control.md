# 🔁 **Flow Control in Java 
---

## ✅ **1. Selection Statements**

### **if / else if / else**

* `else if(condition)` → **condition must be boolean**.
* Curly braces `{}` are **optional**, but recommended for clarity.
* `else` binds to the nearest unmatched `if`
**Valid**
``` java
if (temperature >= 4)          // Outer if
    if (humidity < 6)          // Inner if (nested)
        System.out.println("Too Low");
    else                       // This 'else' belongs to 'if (humidity < 6)'
        System.out.println("Just Right");
else                           // This 'else' belongs to 'if (temperature >= 4)'
    System.out.println("Too High");
``` 

### **switch**

* **Syntax**:

  ```java
  switch(expression) {
      case value1:
          // statements
          break;
      ...
      default:
          // statements
  }
  ```

* Nothing is compulsory: **Curly braces `{}` are mandatory** in `switch` only.
``` java
 switch(x){
    // nothing
 } // Valid
```

* `switch` expression types allowed:

  * `byte`, `short`, `int`, `char`, `enum`, `String`, `var` 
  * **Wrapper classes** of these types are also allowed.
* Not allowed: `long`, `float`, `double`, **`boolean`**.

### **Cases**

* Case labels must be **compile-time constants**.

  ```java
  void fn(final int y){
  final int x = 3;
  switch(x + 1) {
      case x + 0: // valid (evaluates to constant)
      // case y: // compilation error, as this is not compile time constant 
      ...
  }
  }
  ```
* **No variables allowed** in `case` labels.
* **Max number of cases**: Limited to `int` range.
* Case fall-through behavior:

  * If no `break`, all following cases execute.
* `default`:

  * Optional.
  * Can be placed **anywhere**, including first.

* For `byte` it should be in `byte range`
``` java
byte b = 2;
switch (b) {
    case 1: // valid
    case 127: // valid
    case -128: // valid
        break;
    // case 200: // ❌ Compilation error:
}
```

#### **Example:**

```java
int x = 0;
switch(x) {
    default: System.out.println("def");
    case 0: System.out.println(0);
    case 1: System.out.println(1); break;
    case 2: System.out.println(2);
}
```

* If `x = 0` → `def`, `0`, `1`
* If `x = 1` → `1`, `2`
* If `x = 3` → `def`, `0`, `1`

---

## 🔁 **2. Iterative Statements**


- **Entry-Controlled Loops**: Condition checked before loop execution, e.g., `while` and `for` loops.
- **Exit-Controlled Loops**: Condition checked after loop execution, e.g., `do-while` loop, ensuring the loop executes at least once.
### **while**

```java
while(condition) {
    // executes while condition is true
}
```

### **do-while**

```java
do {
    // executes at least once
} while(condition);
```

### **for**

```java
for(initialization; condition; update) {
    // body
}
```

* All three parts (**init, condition, update**) are optional:

  ```java
  for(;;) {
      // infinite loop
  }
  ```
* Statements allowed in any section:

  ```java
  int i = 0;
  for(System.out.println("start"); i < 2; System.out.println("continue"))
      i++;
  // Output: start, continue, continue
  ```

### **Enhanced for-each**

```java
int[] arr = {10, 20, 30};
for(int val : arr)
    System.out.println(val);
```

* Iterates over arrays/collections in normal order.
* Cannot modify or pattern control iteration (e.g., skipping indices).
* Right side cant be: `Map`, `Object`
* Can be `[]`, `List`, `Set`
---

## 🔁 **3. Transfer Statements**

### **break**

* Exits:

  * The **current loop**.
  * The **current switch**.
  * A **labeled block**:

    ```java
    l1: {
        System.out.println("begin");
        break l1;
        System.out.println("end"); // unreachable
    }
    System.out.println("hi");
    // Output: begin, hi
    ```

### **continue**

* Skips current loop iteration and proceeds to next.
* Must not be followed by unreachable code:

  ```java
  if(condition) {
      continue;
      System.out.println("unreachable"); // compile error
  }
  ```

#### Labeled continue
``` java
outer:  
for(int i=0; i<3; i++) {  
    inner:  
    for(int j=0; j<3; j++) {  
        if(j == 1) continue outer; // Skips to next iteration of 'outer' loop  
    }  
}  
```
### **return**

* Exits from a method.
* Can be used with or without a value depending on method return type.


### **assert**

* Used for testing assumptions during development.
* Throws `AssertionError` if the condition is false (when enabled with `-ea`).

---

## 📌 **Other Key Points**

* **Unreachable Code**:
  Java does not allow code that will **never be executed**.

* **switch(expression)**:

  * The expression can be an evaluated result like `x + 10`, as long as it resolves to a **single constant value**.

* **Multiple Case Labels**:

  ```java
  case 1: case 2:
      // shared code // Clubbing Cases allowed
      break;
  ```

* **while(i < size)**:
  * Final `i` becomes `== size` after the loop ends.

------

### 🔁 `switch` + `yield` (Java 17)

#### ✅ **Switch Expression with `yield`**

* Use when case has a block (`{}`) and you need to **return a value**.

```java
int result = switch (day) {
    case MONDAY, SUNDAY -> 1; // OK, no block, no yield needed
    case TUESDAY -> 2;
    case FRIDAY -> cost+=1;
    default -> {
        yield 0;
    }
};
```

#### 🔹 `yield`

* **Keyword** to return a value from a block in a `switch` expression & stop to fall through case
* Needed **only in block-style** cases.

---

### ⚠️ Rules & Tips

* `switch` can return a value (expression form).
* Use `->` for concise cases.
* Use `yield` inside `{}` when logic is more complex.
* Works with `String`, `enum`, primitive types, wrapper classes.

---
* Both could use `:` or `->`


**All are same**
``` java
case 1, 2: --;
case 1, 2-> --;
case 1: case 2: --;
case 1: case 2-> --;
```

==================================

switch with arrow either return value or throw exception


switch(x){
  default:

}
valid