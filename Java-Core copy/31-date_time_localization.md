## ✅ **Java Date, Time, and Localization APIs**

---

### 📆 **Date & Time API**

#### 1. **Packages & Libraries**

* **`java.util`** – Legacy date/time (e.g., `Date`, `Calendar`)
* **`java.time`** – Modern Java Date-Time API (Java 8+)
* **`org.joda.time`** – Joda Time API (external, pre-Java 8)

---

#### 2. **Working with Current Date**

* Use **factory static methods** like `now()` to get current date/time.

  ```java
  LocalDate date = LocalDate.now();
  LocalDateTime dt = LocalDateTime.now();
  ```

---

#### 3. **Creating Dates**

* Use `of(...)` methods; values must be **valid**, else `DateTimeException` is thrown.

  ```java
  LocalDate date = LocalDate.of(2020, 12, 25); // Valid
  ```

---

#### 4. **Parsing & Formatting**

* **Parse (String → Date)**:

  ```java
  LocalDate date = LocalDate.parse("2018-04-15", DateTimeFormatter.ISO_DATE);
  ```

* **Format (Date → String)**:

  ```java
  String str = date.format(DateTimeFormatter.ISO_DATE);
  ```

* ⚠️ Example of runtime exception:

  ```java
  LocalDate.parse("2014-12-01").format(DateTimeFormatter.ISO_DATE_TIME); // Invalid: expecting DateTime
  ```

---

#### 5. **Date-Time Manipulation**

```java
LocalDateTime dt = LocalDateTime.of(2014, 7, 31, 1, 1);
dt = dt.plusDays(30).plusMonths(1);
System.out.println(dt.format(DateTimeFormatter.ISO_DATE)); // "2014-08-31"
```

---

### 📦 **ResourceBundle (java.util.ResourceBundle)**

#### 1. **Definition**

* A **ResourceBundle** stores **localizable resources** (messages or patterns).
* Stored as:

  * `.properties` files (e.g., `messages.properties`)
  * `.class` files extending `ListResourceBundle`

#### 2. **Structure**

* Files are named per locale:

  * `messages.properties` (default)
  * `messages_en_GB.properties`
  * `messages_ru.properties`

* Key-Value format:

  ```
  hello=Hello there!
  ```

* If a locale-specific key is missing, the **default bundle** is used.

---

### 🗣️ **Formatting Message Patterns**

#### 1. **Using MessageFormat**

```java
Locale locale = new Locale("en", "GB");
ResourceBundle bundle = ResourceBundle.getBundle("package.messages", locale);

String pattern = bundle.getString("product");
String result = MessageFormat.format(pattern, name, price, quantity, bestBefore);
```

* `MessageFormat` supports:
  * Formatting values into text
  * Parsing values from text

---

### 🔢 **Number Formatting (java.text.NumberFormat)**

#### 1. **General Notes**

* `NumberFormat.parse()` returns `java.lang.Number`.

  * `Number` is superclass for `Integer`, `Double`, `BigDecimal`, etc.

---

#### 2. **CompactNumberFormat**

* Use for compact (shortened) number formatting based on locale:

```java
NumberFormat compact = NumberFormat.getCompactNumberInstance(locale, NumberFormat.Style.SHORT);
compact.format(1000); // e.g., "1K" or "1 mil"
```

---

#### 3. **PercentFormat**

```java
NumberFormat percentFormat = NumberFormat.getPercentInstance();
percentFormat.setMaximumFractionDigits(2);
percentFormat.format(0.065); // "6.5%"
```

* Without setting fractional digits:

```java
percentFormat.format(0.065); // "6%"
```

---

### 🧪 **JShell Examples**

```java
jshell> NumberFormat compactFormat = NumberFormat.getCompactNumberInstance(locale, Style.SHORT);
compactFormat ==> java.text.CompactNumberFormat@1b8f0de4

jshell> percentFormat
Signatures: percentFormat: java.text.NumberFormat

jshell> percentFormat.setMaximumFractionDigits(2);
jshell> percentFormat.format(0.065);
$31 ==> "6.5%"
```

---

### 📘 **Summary**

* ✅ Use `now()` and `of(...)` to create date/time objects.
* ✅ `parse()` and `format()` for conversion between `String` and `LocalDate/LocalDateTime`.
* ✅ `ResourceBundle` helps avoid hardcoding text and supports localization.
* ✅ Use `MessageFormat` for dynamic message construction from patterns.
* ✅ Use `CompactNumberFormat` and `PercentFormat` for locale-specific, concise number output.

---

========
An instance of java.time.Instant represents time elapsed since Java epoch

Instant.now() will return the time as of UTC, while LocalDateTime.now() will return the time as per the time shown by the operating system clock, depend on timezone

Neither Instant nor LocalDateTime contain time zone information. 




java.time.temporal.TemporalAccessor is the base interface that is implemented by LocalDate, LocalTime, and LocalDateTime concrete classes. This interface defines read-only access to temporal objects, such as a date, time, offset or some combination of these, which are represented by the interface TemporalField.


resource bundle is immutable