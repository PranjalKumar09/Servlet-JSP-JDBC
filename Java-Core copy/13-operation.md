[!Java-Core/13-operation.md](Java-operator_prescdence.png)


### **Unary Operators & Primitive Types**

```java
char ch = 'a';
ch++; // 'b'
```

* `++` and `--` are valid for all primitive types **except boolean**.

---

### **Integer Overflow Behavior**

```java
byte b = 0;
while (b++ < 128)
    System.out.println(b); // Infinite loop
```

* Loops: `0 → 127`, then wraps to `-128 → 127` (byte range = -128 to 127).
* *No error on overflow. It just cycles similar to this follows in all data types in their data range*
---

### **Arithmetic Operations**

* Result type = **higher of the operand types**.
* **0/0** (Maths) = Undefined.

```java
System.out.println(10 / 0);      // int → ArithmeticException (runtime)
System.out.println(10 / 0.0);    // double → Infinity
System.out.println(0 / 0);       // int → ArithmeticException
System.out.println(0.0 / 0.0);   // double → NaN
```

* `ArithmeticException` (AE) only in **integral** division/modulo %.
* **Java does not support operator overloading** (e.g., `5 + 5`, `"a" + "b"` is allowed).

---

### **String + Numeric Concatenation**

```java
String a = "str";
int b = 10, c = 20, d = 30;
System.out.println(a + b + c + d);     // str102030
System.out.println(b + a + c + d);     // 10str2030
System.out.println(b + c + d + a);     // 60str
System.out.println(a + b + (c + d));   // str1050
```

---

### **Relational Operators**

* Valid: `<`, `>`, `<=`, `>=`, `==`, `!=`
* **Not allowed for `boolean`** except `==`, `!=`
* **Object types (e.g., String) not allowed** in `<`, `>`, etc.

```java
System.out.println(10 < 20 < 30); // Error: 10 < 20 → boolean, can't compare with 30, so nesting not allowed
```

* `'a' == 97` → `true` (char promoted to int)
* `10 == 10.00` → `true` (int promoted to double)
* `false == false` → `true`

---

### **Object Comparison**
 
* `==` is reference comparison 
```java
String s1 = new String("Pranjal");
String s2 = new String("Pranjal");

System.out.println(s1 == s2);         // false (different objects)
System.out.println(s1.equals(s2));    // true (content match)

String s3 = "Pranjal";
String s4 = "Pranjal";

System.out.println(s3 == s4);         // true (same string pool literal)

String s5 = null;
// s5.equals("abc"); → NPE
System.out.println("abc".equals(s5)); // false (safe)
```

---

### **`instanceof` Operator**

```java
Thread t = new Thread();

System.out.println(t instanceof Thread);   // true  // Only false if t is null(here it is not)
System.out.println(t instanceof Runnable); // true  
System.out.println(t instanceof Object);   // true  
System.out.println(t instanceof String);   // false

Parent p = new Child();
boolean result = p instanceof Child; // result is true
```

---

### **Integer Caching**

```java
Integer a = 128, b = 128;
System.out.println(a == b);      // false (outside -128 to 127)
System.out.println(a.equals(b)); // true

Integer x = 100, y = 100;
System.out.println(x == y);      // true (cached)
```

---

### **Unrelated Object Types**

```java
String s = new String();
Thread t = new Thread();
System.out.println(s == t); // Compile-time error: unrelated types
```

* **Object**

  * ↳ `Thread`
  * ↳ `String`
  * No relation between `Thread` and `String`.

---

### **Bitwise Operators**

```java
System.out.println(4 & 5); // 4 (numeric bitwise AND)

System.out.println(~4);    // -5 (bitwise complement)
System.out.println(~true); // Error (not valid on boolean)

System.out.println(!true); // false (boolean NOT)
System.out.println(!4);    // Compile-time error
```

---

### **Logical vs Bitwise**

* `&&`, `||` → **Short-circuit** logical ops: second operand evaluated **only if needed**

  * Example: `a != 0 && (10/a > 1)` → avoids division by zero
* `&`, `|`, `^` → **Full evaluation** (both sides always evaluated)

  * Also used for **bitwise operations**


---

### **Assignment Types**

1. Simple: `x = 10;`
2. Chain: `x = y = z = 10;`
3. Compound: `x += 10;`

* **Right-associative**

```java
a = b = c = d = 20;
a += b -= c *= d /= 2;

// Step-wise:
d = 10
c = 200   (c *= d)
b = 180   (b -= c)
a = 20    (a += b)
```

---

### **Ternary Operator**

```java
(condition) ? expr1 : expr2
```

* Only ternary operator in Java.
* Nesting allowed.
* Valid expression: **# of `?` = # of `:`**

```java
(a < b) ? (a < c ? a : (b < c ? b : c)) : ...;
```

---

### **String Methods**

```java
String s = "Hello";

s.toLowerCase();         // creates new String (immutable)
s.equalsIgnoreCase("hELLo"); // true (case ignored)
```

---


* `>>`  → Signed right shift
* `>>>` → **Unsigned** right shift
* `<<`  → Left shift

---

### 🔢 Initial Setup:

```java
int a = 5;    // binary: 00000000 00000000 00000000 00000101
int b = -5;   // binary: 11111111 11111111 11111111 11111011 (2's complement)
```

---

## 🔸 1. **Signed Right Shift (`>>`)**

* Fills leftmost bits with the **sign bit** (preserves sign).
* Divides by 2ⁿ.

```java
System.out.println(a >> 1);  // 5 >> 1 → 2
System.out.println(b >> 1);  // -5 >> 1 → -3
```

### 👇 Explanation:

* `a = 5` → `000...0101` → after `>> 1`: `000...0010` = 2
* `b = -5` → `111...1011` → after `>> 1`: `111...1101` = -3 (sign bit kept)

---

## 🔸 2. **Unsigned Right Shift (`>>>`)**

* Fills leftmost bits with **0** (ignores sign).
* Works differently for negative numbers.

```java
System.out.println(a >>> 1);  // 5 >>> 1 → 2 (same as >>)
System.out.println(b >>> 1);  // -5 >>> 1 → 2147483645 (positive!)
```

### 👇 Explanation:

* `a = 5` → `>>> 1` behaves like `>>` since sign bit is 0.
* `b = -5` (binary `111...1011`) → shifts right with `0`s in front:

  New binary: `01111111...111101` = 2147483645

---

## 🔸 3. **Left Shift (`<<`)**

* Shifts bits to the left, adds 0s at the end.
* Multiplies by 2ⁿ.

```java
System.out.println(a << 1);  // 5 << 1 → 10
System.out.println(b << 1);  // -5 << 1 → -10
```

### 👇 Explanation:

* `a = 5` → `000...0101` → `000...1010` = 10
* `b = -5` → `111...1011` → `111...0110` = -10

---


new knowledge

 the * (multiplication) and % have same prescenduce  => L to R
 only () these bracket can be apply to change the order of expression

 <<< not exist as doing signed, unsigned here same



 Callable.call() allows you to declare checked exceptions while Runnable.run() does not. So if your task throws a checked exception, it would be more appropriate to use a Callable


 to run modular approach
  modular application stored in c:\modules\movies.jar
  ->-p c:\modules\movies.jar or 





  --module or -m: This option specifies the module that you want to run. For example, if you want to run abc.utils.Main class of abc.math.utils module, you should write --module abc.math.utils/abc.utils.Main
If a module jar specifies the Main-Class property its MANIFEST.MF file, you can omit the main class name from  --module option. For example, you can write, --module abc.math.utils instead of --module abc.math.utils/abc.utils.Main.


otected members of a class are accessible outside the package only in subclasses of that class, and only when they are fields of objects that are being implemented by the code that is accessing them.
Basically, it implies that a protected member is accessible in the subclass only using a reference whose declared type is of the same subclass (or its subclass.).


? both side have same data type, when have method , return type cant be void 