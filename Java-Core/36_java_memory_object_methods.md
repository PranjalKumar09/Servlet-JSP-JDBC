
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

EXTRA INFORMATION

A record cannot define any instance field explicitly.
The direct superclass type of a record class is Record. Thus, a record cannot have an extends clause and so it cannot extend any other class. not even other record
record can implement any no of interface


inner class can be sealed too

Records cannot be marked abstract, sealed, or non-sealed.






The serialization mechanism treats instances of a record class differently than ordinary serializable or externalizable objects. In particular, a record object is deserialized using the canonical constructor.



    It cannot contain an instance field declaration (static fields are allowed).
    It cannot have an instance initializer (static initializers are allowed).
    It cannot have abstract or native methods.




 If a canonical constructor is not provided by the programmer explicitly, the compiler will provide one automatically with the same access modifier as that of the record itself.

  you write a non-canonical constructor in a record explicitly then, on the first line of such a constructor, you must provide a call to either the canonical constructor or another constructor. For example:



public record Student(int id, String name){
    public Student(){ //a non-canonical constructor
        this(10); //this line or a call to the canonical constructor is required 
    }
    public Student(int id){ //another non-canonical constructor
        this(id, ""); //this line is required 
    }
    public Student(int id, String name){ //regular form canonical constructor
        this.id = id; this.name=name;
    }
}




You can define a top level record i.e. directly under a package, a nested member record class i.e. within a class/interface, or a local record i.e. within a method.
A nested record class is implicitly static. That is, every member record class and local record class is static. It is permitted for the declaration of a member record class to redundantly specify the static modifier, but it is not permitted for the declaration of a local record class.

A record class is implicitly final. It is permitted for the declaration of a record class to redundantly specify the final modifier.


difference between a record and a regular class with respect to constructors is that the compiler provides a canonical constructor for a record even when the programmer provides a non-canonical constructor and does not provide the canonical constructor. In a regular class, the compiler does not provide the default no-args constructor if the programmer provides any other constructor for the class.
 A canonical constructor cannot be generic.
A canonical constructor cannot have a throws clause (not even a throws clause with unchecked exceptions is allowed) but a non-canonical constructor may have a throws clause. 




interface can have nested sealed class, 
nested sealed class must have permit
