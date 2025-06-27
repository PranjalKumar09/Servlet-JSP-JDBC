
## Pattern Matching for `instanceof`

```java
public void order(Product p) {
    if (p instanceof Food f && f.getBestBefore().isBefore(LocalDate.now())) {
        LocalDate bestBefore = f.getBestBefore();
    } else {
        // 'f' is out of scope here
    }
}
```
- Pattern matching allows type casting and variable declaration in one step.
- Variable (`f`) is only in scope within the `if` block.

---

## 🔒 Sealed Classes & Interfaces

- **Purpose:** Restrict which classes/interfaces can extend or implement them.
- **Syntax:** Use `sealed` keyword and `permits` clause.

### Key Rules
1. Only types listed in `permits` can extend/implement the sealed class/interface.
2. Permitted types must be:
   - In the same module, or
   - In the same package (if unnamed module).
3. Each permitted subclass must be declared as:
   - `final` (no further subclassing)
   - `sealed` (can restrict further)
   - `non-sealed` (unrestricted subclassing)

### Example

```java
package shop;
public abstract sealed class Product permits Food, Drink {}

public non-sealed class Food extends Product {}
public final class Drink extends Product {}
```

### Invalid Example

```java
public class Other extends Product {} // ❌ Compile-time error: not in permits list
```

---

## 🟦 Records

- Immutable, implicitly `final` data classes.
- Only field types and names required in header.
- Implicitly extends `java.lang.Record`.
- Auto-generates: accessors, constructor, `equals`, `hashCode`, `toString`.
- Can declare nested types (including records), static fields/methods.
- `record` must have `()    `

### Example

```java
public record Product(String name, double price) {}

Product p1 = new Product("Tea", 1.99);
Product p2 = new Product("Tea", 1.99);
boolean same = p1.equals(p2);
int hash = p1.hashCode();
String name = p1.name();
String text = p1.toString();
```

### Custom Record Constructors

- Default constructor auto-generated.
- Custom constructors can be:
  - **Canonical (full):**
    ```java
    public record Product(String name) {
        public Product(String name) {
            this.name = name.toUpperCase();
        }
    }
    ```
  - **Compact:**
    ```java
    public record Product(String name) {
        public Product {
            name = name.toUpperCase();
        }
    }
    ```

### Methods & Fields in Records

- Can override methods, add static fields/methods.
- Cannot add instance fields outside the header (breaks immutability).

```java
public record Crane(int numberEggs, String name) {
    @Override public int numberEggs() { return 10; }
    @Override public String toString() { return name; }
    private static int type = 10; // OK
    // public int size; // ❌ DOES NOT COMPILE
    // private boolean friendly; // ❌ DOES NOT COMPILE
}
```

---

## 🏭 Factory Method Pattern

- Hides specific constructor usage; controls object creation.
- Used when object creation depends on conditions.
- Constructor is usually private; static method returns instance.

### Example

```java
class Person {
    private int age;
    private String name;

    private Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void show() {
        System.out.println(age + ", " + name);
    }

    public static Person createPerson(int age, String name) {
        if (age <= 0) return null;
        return new Person(age, name);
    }
}
```
- Example: If `age <= 0`, `createPerson` returns `null`.

---