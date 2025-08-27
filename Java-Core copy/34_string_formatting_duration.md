

## ✅ Java String Blocks, Formatting, Duration & Time APIs — Oracle Exam Notes

---

### 📦 **Text Blocks (`"""`) – Multiline Strings (Java 15+)**

#### 2. **Behavior Examples**

* **Indented spaces** before lines may be normalized unless you **intentionally align** or keep a blank line at the end:

```java
String p1 = """
    product 101
  "Hot Tea"
    price 1.99
""";  // Preserves indentation exactly
```
```java
String p1 = """
    product 101
  "Hot Tea"
    price 1.99""";  // Not Preserves indentation exactly
```

---

### 🧵 **StringBuilder Basics**

```java
String c = "TEA TEA";
StringBuilder txt = new StringBuilder(c);
```

* `txt.length()` ➝ `7` (length of "TEA TEA")
* `txt.capacity()` ➝ `23` (length + default buffer size of `16`)

---

### 🧾 **String Formatting with `format()` and `formatted()`**

#### 1. **Common Format Specifiers**

| Specifier | Meaning                                   |
| --------- | ----------------------------------------- |
| `%s`      | String                                    |
| `%d`      | Integer or long                           |
| `%f`      | Floating-pt                               |
| `%1.3f`   | Float: 1 digit before and 3 after decimal |
| `%n`      | Insert line break using system dependent line break|

#### 2. **Examples**

```java
String p2 = "product: %d, %s, price: %2.2f";
p2.formatted(101, "Tea", 1.255); 
// → "product: 101, Tea, price: 1.26"

System.out.printf(p2, 101, "Tea", 1.245);
// → product: 101, Tea, price: 1.25
```

* `formatted()` → returns formatted `String`
* `printf()/format()` → prints directly to console

---

### ⏱️ **Date-Time & Duration API**

#### 1. **Day of the Week**

```java
LocalDate today = LocalDate.now();
today.plusYears(1).getDayOfWeek(); // e.g., SUNDAY
```

---

#### 2. **Duration (Time Difference)**
  
```java
Duration gap = Duration.between(teaTime, LocalTime.now());
gap.toMinutes();      // → -707
gap.toHours();        // → -11
gap.toMinutesPart();  // → -47
```

* `Duration` is used to represent a **time-based amount** (hours, minutes, seconds).
* Negative values indicate past times relative to the second argument.

---

### 🌍 **ZonedDateTime & Offset**

```java
zonedDateTime.getOffset();
```

* Returns the **time-zone offset** (e.g., `+05:30`) from UTC.
