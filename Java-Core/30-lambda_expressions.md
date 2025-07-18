## Lambda Expressions & Functional Interfaces

### Lambda Expressions
- **Purpose**: Enables concise code and functional programming in Java.
- **Definition**: Anonymous (nameless) function with no return type or access modifier.
- **Syntax**:
  - Basic: `() -> System.out.println("Hello");` (single statement, no curly braces needed)
  - With parameters: `(int a, int b) -> System.out.println(a + b);`
  - Single parameter: `x -> x * x;` (no parentheses needed for single parameter)
  - Multi-line: `(x) -> { return x * x; };` (curly braces and `return` required for multiple statements)
- **Key Features**:
  - **Type Inference**: Compiler automatically infers parameter types (e.g., `(a, b)` instead of `(int a, int b)`).
  - **Arguments**: Supports zero or multiple parameters (comma-separated for multiple, empty `()` for none).
  - **Body**: Single expression (no braces) or multiple statements (with braces and `return` if needed).

  #### **Lambda Syntax Variations**  
- **Parameter Modifiers**:  
  ```java
  list.removeIf((final String s) -> s.equals("remove"));  // Explicit type  
  list.removeIf((final var s) -> s.equals("remove"));     // Inferred type (Java 10+)  
  ```
- **Simplified Forms**:  
  ```java
    Comparator<String> sortText = (s1, s2) -> s1.compareTo(s2);  // Brackets optional for single inferred-type param  
    Runnable r = () -> System.out.println("No params");           // Empty brackets for zero params
  ```
- **Predefined Expressions**:  
  ```java
    Predicate<String> filter = s -> s.startsWith("A");
    list.removeIf(filter);  // Reuse lambda expression
  ```


**Note**:  
- Lambda variables **must not conflict** with variables from the enclosing scope:  
  ```java
  String conflict = "test";  
  Predicate<String> invalid = conflict -> conflict.isEmpty();  // ❌ Compile error (name clash)  
  Predicate<String> valid = s -> s.equals(conflict);          // ✅  

### Functional Interfaces
- **Definition**: An interface with exactly **one abstract method** (SAM - Single Abstract Method).
- **Examples**:
  - `Runnable`: `run()`
  - `Callable`: `call()`
  - `Comparable`: `compareTo()`
- **Usage with Lambda**:
  - Lambda expressions provide a concise way to implement functional interfaces.
  - Example:
    ```java
    interface Interf {
        void add(int a, int b);
    }
    class Test {
        public static void main(String[] args) {
            Interf i = (a, b) -> System.out.println("Sum: " + (a + b));
            i.add(10, 20); // Output: Sum: 30
        }
    }
##### `@FunctionalInterface`
* `Purpose`: Explicitly marks an interface as a functional interface (compile-time check).
* The interface must have exactly one abstract method (SAM).
  ``` JAVA
  @FunctionalInterface
  interface MathOperation {  
      int calculate(int a, int b); // SAM  
      // int subtract(int a, int b); // ❌ Compile error (violates SAM rule)  
      default void log() { System.out.println("Logged"); } // Allowed (non-abstract)  
  }
#### **Method References**  
- **Semantic shorthand** for lambda expressions invoking existing methods.  
- **Syntax**:  
  ```java
  Class::staticMethod      // Math::max  
  instance::instanceMethod // System.out::println  
  Class::instanceMethod    // String::length  
- **Example**:
  ```java
    // Lambda:
    Collections.sort(list, (s1, s2) -> s1.compareToIgnoreCase(s2));  
    // Method reference equivalent:  
    Collections.sort(list, String::compareToIgnoreCase);  

    // Lambda:  
    list.forEach(s -> System.out.println(s));  
    // Method reference equivalent:  
    list.forEach(System.out::println);    
  ```


### Lambda with Threads
- **Without Lambda**:
  ```java
  class MyRunnable implements Runnable {
      public void run() {
          for (int i = 0; i < 10; i++) {
              System.out.println("Child Thread");
          }
      }
  }
  class Test {
      public static void main(String[] args) {
          MyRunnable r = new MyRunnable();
          Thread t = new Thread(r);
          t.start();
          for (int i = 0; i < 10; i++) {
              System.out.println("Main Thread");
          }
      }
  }
  ```
- **With Lambda**:
  ```java
  class Test {
      public static void main(String[] args) {
          Runnable r = () -> {
              for (int i = 0; i < 10; i++) {
                  System.out.println("Child Thread");
              }
          };
          Thread t = new Thread(r);
          t.start();
          for (int i = 0; i < 10; i++) {
              System.out.println("Main Thread");
          }
      }
  }
  ```
  - **Advantage**: Reduces boilerplate code by directly defining the `run()` method using a lambda.

### Predefined Functional Interfaces: Predicate
- **Package**: `java.util.function.*`
- **Definition**: A functional interface that evaluates a condition and returns a boolean.
  ```java
  interface Predicate<T> {
      boolean test(T t); // one abstract method
  }
  ```
- **Example**:
  ```java
  import java.util.function.Predicate;
  class Test {
      public static void main(String[] args) {
          Predicate<Integer> p = i -> i > 10;
          System.out.println(p.test(100)); // true
      }
  }
  ```
#### **Predicate Operations**  
- **Static Methods**:  
  ```java
  Predicate.isEqual(obj)  // Tests equality with target object  
  Predicate.not(predicate) // Negates a predicate (Java 11+)  
  ```
-**Chaining**:  
  ```java
    Predicate<Product> isFood = p -> p instanceof Food;  
    Predicate<Product> isCheap = p -> p.getPrice() < 2.0;  
    menu.removeIf(isFood.negate().or(isCheap));  // Remove non-food OR cheap items  
    menu.removeIf(Predicate.isEqual(new Food("Cake")));  // Remove exact match  
  ```


#### Predicate Joining
- **Methods** (default methods in `Predicate`):
  - `and()`: Combines two predicates (logical AND).
  - `or()`: Combines two predicates (logical OR).
  - `negate()`: Negates the predicate result.
- **Example**:
  ```java
  import java.util.function.Predicate;
  class Test {
      public static void main(String[] args) {
          int[] x = {0, 5, 10, 15, 20, 25};
          Predicate<Integer> p1 = i -> i > 10;
          Predicate<Integer> p2 = i -> i % 2 == 0;
          
          m1(p1, x);          // Elements > 10: 15, 20, 25
          m1(p1.negate(), x); Angstrom; // Elements ≤ 10: 0, 5, 10
          m1(p1.and(p2), x);  // Elements > 10 and even: 20
          m1(p1.or(p2), x);   // Elements > 10 or even: 0, 5, 10, 15, 20, 25
      }
      
      static void m1(Predicate<Integer> p, int[] x) {
          for (int x1 : x) {
              if (p.test(x1)) {
                  System.out.println(x1);
              }
          }
      }
  }
  ```

### Key Note
- Functional interfaces have exactly one abstract method.
- Lambda expressions simplify functional interface implementation.
- Predicates are boolean-valued functional interfaces used for filtering or testing conditions.
- Lambda expressions are commonly used with threads (`Runnable`) and predicates for concise code.

---


=====

Predicate takes exactly one argument


constructor can also be called <ClassName>