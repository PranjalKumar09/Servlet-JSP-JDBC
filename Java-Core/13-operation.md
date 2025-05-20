---

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
* **No error on overflow**.

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

System.out.println(t instanceof Thread);   // true  
System.out.println(t instanceof Runnable); // true  
System.out.println(t instanceof Object);   // true  
System.out.println(t instanceof String);   // false
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

* `&` → **Always evaluates both sides**.
* `&&` → **Short-circuit**: evaluates second operand **only if needed**.

---

### **Assignment Types**

1. Simple: `x = 10;`
2. Chain: `x = y = z = 10;`
3. Compound: `x += 10;`

* **Right-associative**

```java
a = b = c = d = 2;
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
