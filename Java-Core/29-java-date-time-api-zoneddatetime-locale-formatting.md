## 📅 **Java Date & Time API (java.time package) – Java 8+**

### ✅ **Core Date-Time Classes**

* `LocalDate.now()` → Current date.
* `LocalTime.now()` → Current time.
* `LocalDateTime.now()` → Current date and time.
* `Instant.now()` → Timestamp (machine-readable instant).

### 🔧 **Creating Instances**

* `LocalDate.of(year, month, day)`
* `LocalTime.of(hour, minute, second, nano)` → Leading fields are mandatory.
* `LocalDateTime.of(year, month, day, hour, minute, second, nano)`
* Combine:

  * `someDate.atTime(someTime)`
  * `LocalDateTime.of(LocalDate, LocalTime)`
* Convert:

  * `toLocalDate()`, `toLocalTime()`

### 🛠 **Immutability & Manipulation**

* All are immutable – operations return **new instances**.
* Use:

  * `plusXxx()`, `minusXxx()`, `withXxx()` for modification.
  * `getXxx()` for extracting parts:

    * `getYear()`, `getMonth()`, `getDayOfMonth()`, `getHour()`, etc.

### 🔄 **Method Chaining Example**

```java
LocalDateTime current = LocalDateTime.now();
LocalDateTime modified = current.withMinute(15).withDayOfMonth(3).plusHours(3);
boolean isBefore = current.isBefore(modified);
```

### 📅 **Useful Methods for Modification**

#### **Set Specific Values**

```java
withYear(int), withMonth(int), withDayOfMonth(int), withHour(int), withDayOfYear (int), withNano(int)...
```

#### **Add/Subtract Values**

```java
plusYears(), minusMonths(), plusWeeks(), minusDays(), plusHours(), minusMinutes(), ...
```

#### **Access Specific Values**

```java
getYear(), getMonth(), getDayOfWeek(), getMinute(), getSecond(), ...
```

---

## ⏱ **Instant, Duration & Period**

### ✅ **Definitions**

* `Instant` → A specific timestamp.
* `Duration` → Time in seconds/nanoseconds (e.g., hours, minutes).
* `Period` → Date-based time (e.g., years, months, days).

### ✅ **Creation**

```java
Instant timestamp = Instant.now();
Duration duration = Duration.ofHours(12);
Period period = Period.between(date1, date2);
```

### ✅ **Compare / Measure**

* `between()`, `isNegative()` (for both Duration & Period)

---

## 🗂 **FileTime**

* Represents file timestamp (last modified, accessed, or created).
* Convert with `.toInstant()`:

```java
Path file = Path.of("path");
if (Files.exists(file) && Files.isRegularFile(file)) {
    return Files.getLastModifiedTime(file).toInstant();
}
```

---

## 🌐 **Zone Date and Time**
* Provided by the `java.time.ZonedDateTime` class, which represents a date-time with a time-zone.

### ✅ **ZonedDateTime**

* Combines `LocalDateTime` + `ZoneId`
* Same manipulation methods as `LocalDateTime`
* Convert time zones using:

  ```java
  withZoneSameInstant()
  ```

### ✅ **Example**

```java
ZoneId london = ZoneId.of("Europe/London");
ZoneId la = ZoneId.of("America/Los_Angeles");
LocalDateTime time = LocalDateTime.of(2015, Month.APRIL, 1, 7, 14);
ZonedDateTime londonTime = ZonedDateTime.of(time, london);
ZonedDateTime laTime = londonTime.withZoneSameInstant(la);
```

### ✅ **ZoneId Examples**

* `"America/Los_Angeles"`, `"Europe/London"`, `"GMT+2"`, `"UTC-05:00"`
* `ZoneId.systemDefault()`

---

## 🌍 **Locales (java.util.Locale)**


### ✅ **Constructors**

```java
Locale uk = new Locale("en", "GB");
Locale th = new Locale("th", "TH", "TH"); // Thai digits variant
Locale fr = new Locale("fr", "FR");// or  = Locale.FRANCE  or = Locale.forLanguageTag("fr-FR");
Locale cf = new Locale("fr", "CA");
Locale current = Locale.getDefault();
```
* Example: `Locale hi_IN = new Locale("hi", "IN");` → Hindi language for India.

### ✅ **Locale Structure**

* A `Locale` can represent just a language, or a combination of **language + country/region**.
* An optional **variant** allows customization, e.g., for numbering systems or calendars.
* Use **language tag strings** (e.g., `"th-TH-u-ca-buddhist-nu-thai"`) to build locales that include calendars, number formats, currencies, etc.

### ✅ **Language Tag Format**

* Uses **ISO 639 (language)**, **ISO 3166 (country)**, or **UN M.49 (area)** codes.

#### Locale Keywords:

* `u-ca`: calendar type
* `u-nu`: numbering system
* `tz`: time zone
* `cu`: currency
* `fw`: first day of week
* `rg`: region override

---

## 💲 **Formatting and Parsing Numbers (java.text.NumberFormat)**

* The `.parse()` method returns a `Number`, which can be converted to primitives (e.g., `int`, `double`) or wrapper types (`Integer`, `Double`) as needed.


### ✅ **Initialization**

```java
Locale locale = new Locale("en", "GB");
NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
NumberFormat percent = NumberFormat.getPercentInstance(locale);
NumberFormat number = NumberFormat.getNumberInstance(locale);
```

### ✅ **Formatting**

```java
BigDecimal price = BigDecimal.valueOf(2.99); // or BigDecimal.ONE
Double tax = 0.2;
int qty = 12345;

String formattedPrice = currency.format(price);  // £2.99
String formattedTax = percent.format(tax);       // 20%
String formattedQty = number.format(qty);        // 12,345
```

### ✅ **Parsing**

```java
percent.setMaximumFractionDigits(2);
BigDecimal p = BigDecimal.valueOf((Double) currency.parse("£1.70"));  // 1.75 
Double t = (Double) percent.parse("12%");  0.12
int q = number.parse("54,321").intValue();  // 54321
```

---

## ⏰ **Formatting & Parsing Date and Time (java.time.format.DateTimeFormatter)**
* `java.time.format.FormatStyle` provides predefined formatting styles: `FULL`, `LONG`, `MEDIUM`, and `SHORT`.


### ✅ **Using Patterns**

```java
Locale locale = new Locale("en", "GB");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy", locale);
LocalDate date = LocalDate.of(2019, Month.APRIL, 1);
String formatted = date.format(formatter);  // Monday 01 Apr 2019
// or: formatter.format(date)
```

### ✅ **Parsing**

```java
LocalDate date = LocalDate. ("Tuesday 31 Mar 2020", formatter);
```

### ✅ **Localized Format**

```java
Locale ru = new Locale("ru");
DateTimeFormatter formatter = DateTimeFormatter
    .ofLocalizedDate(FormatStyle.MEDIUM)
    .localizedBy(ru);
String formatted = date.format(formatter);  // 31 мар. 2020 г.
```

---

### 🧩 **Default & Custom Formats**

* By default, date-time values follow the **ISO-8601** format: `YYYY-MM-DDTHH:MM:SS`, e.g., `2011-12-03T10:15:30`.
* To customize, use `DateTimeFormatter.ofPattern()` or the `FormatStyle` enum (`FULL`, `LONG`, `MEDIUM`, `SHORT`).


## 🗓 **Date and Time Format Symbols**

| Symbol | Meaning          | Examples      |
| ------ | ---------------- | ------------- |
| G      | Era              | AD, BC        |
| y      | Year             | 2025, 25      |
| Y      | Year-of-era      | 2025          |
| D      | Day-of-year      | 189           |
| M / L  | Month-of-year    | 07, Jul, July |
| d      | Day-of-month     | 10            |
| E      | Day-of-week name | Monday, Tue   |
| a      | AM/PM marker     | AM, PM        |
| H      | Hour (0-23)      | 14            |
| h      | Hour (1-12)      | 2             |
| m      | Minute           | 30            |
| s      | Second           | 45            |
| S      | Millisecond      | 123           |
| z      | Time zone name   | GMT, PST      |
| Z / X  | Zone offset      | +0000, +00:00 |

---


- In Period method,like getXxxs() , end with s, like getYears()


===
Upper case M is for Month. For example, for February and December, the following will be printed:

M => 2, 12
MM => 02, 12
MMM => févr., déc.
MMMM => février, décembre


text style is determined based on the number of pattern letters used. Less than 4 pattern letters will use the short form. Exactly 4 pattern letters will use the full form

If the count of letters is one, then the value is output using the minimum number of digits and without padding. Otherwise, the count of digits is used as the width of the output field, with the value zero-padded as necessary. The following pattern letters have constraints on the count of letters. Only one letter of 'c' and 'F' can be specified. Up to two letters of 'd', 'H', 'h', 'K', 'k', 'm', and 's' can be specified. Up to three letters of 'D' can be specified. 

t is not valid form
z is valid form

Number/Text: If the count of pattern letters is 3 or greater, use the Text rules above. Otherwise use the Number rules above.