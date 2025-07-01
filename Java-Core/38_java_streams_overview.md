# **Java Streams**

## **Stream Overview**
- **Immutable** element flow
- **Processing Modes**: Sequential or parallel (`list.stream().parallel()`).
- **Element Access**: Elements are processed once and then unavailable.
- **Pipeline**: Uses **method chaining** with **intermediate** and **terminal** operations.
  - **Intermediate**
  - **Terminal**
- **Lazy Evaluation**: Intermediate operations are deferred until a terminal operation triggers traversal.
- **Finite/Infinite Streams**: Supports both (e.g., `Stream.generate()` for infinite).

## **Stream Creation**
- From **collections**: `list.stream()`.
- From **arrays**: `Arrays.stream(array)`.
- From **Stream class**: `Stream.of()`, `Stream.generate()`, `Stream.iterate()`.
- **Primitive Streams**: `IntStream`, `LongStream`, `DoubleStream` (extend `BaseStream`).

## **Stream Operation Categories**
1. **Intermediate**: Transform or filter streams, return a new stream.
2. **Terminal**: End the pipeline, produce a result or side effect.
3. **Short-Circuit**: Produce finite results, even with infinite input (e.g., `findFirst`, `takeWhile`).

## **Functional Interfaces (`java.util.function`)**
Streams use functional interfaces, often implemented as lambda expressions.

| **Interface**      | **Purpose**                     | **Method**                     | **Example Use**                     |
|--------------------|---------------------------------|--------------------------------|-------------------------------------|
| `Predicate<T>`     | Test, returns `boolean`         | `boolean test(T t)`           | Filter: `p -> p.getPrice() > 10`   |
| `Function<T, R>`   | Transform `T` to `R`            | `R apply(T t)`                | Map: `p -> p.getName()`            |
| `UnaryOperator<T>` | Transform `T` to `T`            | `T apply(T t)`                | Map: `n -> n.trim()`               |
| `Consumer<T>`      | Process element, no return      | `void accept(T t)`            | ForEach: `p -> p.setDiscount(0.1)` |
| `Supplier<T>`      | Produce element, no input       | `T get()`                     | Generate: `() -> Math.random()`    |

### **Primitive Variants**
- **Input**: `IntPredicate`, `LongPredicate`, `DoublePredicate`, `IntConsumer`, etc.
- **Output**: `ToIntFunction<T>`, `IntSupplier`, etc.
- **Input-Output**: `IntToDoubleFunction`, `IntUnaryOperator`, etc.
- **Bi-Variants**: `BiPredicate`, `BiFunction`, `BiConsumer` for two arguments.

## **Key Stream Operations**
### **Intermediate Operations**
- **Filter**: `filter(Predicate<T>)` – Retains elements matching condition.
- **Map**: `map(Function<
Stream handling operation categories
    Intermediate: perform action &  produce another system.
    Terminal: usually at End processing
    Short-cercuit: produce finite result, even infinite inputT, R>)` – Transforms elements to a new type.
  - Variants: `mapToInt`, `mapToLong`, `mapToDouble`.
- **FlatMap**: `flatMap(Function<T, Stream<R>>)` – Flattens nested streams.
  - Variants: `flatMapToInt`, `flatMapToLong`, `flatMapToDouble`.
- **Peek**: `peek(Consumer<T>)` – Performs action without modifying stream (debugging).
- **Distinct**: Removes duplicates.
- **Sorted**: Sorts elements (natural order or custom `Comparator`).
- **Skip**: `skip(long n)` – Skips first `n` elements.
- **Limit**: `limit(long n)` – Restricts to first `n` elements.
- **TakeWhile**: `takeWhile(Predicate<T>)` – Takes elements while predicate is true.
- **DropWhile**: `dropWhile(Predicate<T>)` – Drops elements while predicate is true.

### **Terminal Operations**
- Returns single result
- **ForEach**: `forEach(Consumer<T>)` – Applies action to each element.
  - `forEachOrdered`: Ensures order (useful in parallel streams).
- **Count**: Returns `long` count of elements.
- **Min/Max**: Returns `Optional<T>` based on `Comparator`.
- **Sum/Average**: For primitive streams (`IntStream`, `DoubleStream`).
- **Collect**: Aggregates to a collection (e.g., `collect(Collectors.toList())`).
- **Reduce**: Combines elements (e.g., sum, concatenation).
- **Short-Circuit**:
  - `allMatch`, `anyMatch`, `noneMatch`: Return `boolean`.
  - `findAny`, `findFirst`: Return `Optional<T>`.

## **Optional Class**
- Represents a value that may or may not exist.
- Methods:
  - `isPresent()`: Returns `true` if value exists.
  - `get()`: Retrieves value (throws exception if empty).

## **Examples**
### **Basic Stream Pipeline**
```java
List<Product> list = new ArrayList<>();
list.stream()
    .parallel()                     // Parallel processing
    .filter(p -> p.getPrice() > 10) // Intermediate: Filter
    .forEach(p -> p.setDiscount(0.2)); // Terminal: Apply discount
```

### **Equivalent Loop**
```java
for (Product p : list) {
    if (p.getPrice() > 10) {
        p.setDiscount(0.2);
    }
}
```

### **Complex Pipeline**
```java
list.stream()
    .filter(p -> p.getDiscount() == 0)         // Filter zero-discount products
    .peek(p -> p.applyDiscount(0.1))           // Apply 10% discount
    .map(p -> p.getBestBefore())               // Map to best-before date
    .forEach(d -> d.plusDays(1));              // Add 1 day to date
```

### **Primitive Stream**
```java
IntStream.generate(() -> (int)(Math.random() * 10)) // Random numbers
    .takeWhile(n -> n != 3)                        // Stop at 3
    .sum();                                        // Sum remaining
```

### **FlatMap Example**
```java
List<Order> orders = new ArrayList<>();
double total = orders.stream()
    .flatMap(order -> order.items())               // Flatten order items
    .filter(item -> item.getName().equals("Tea"))  // Filter "Tea"
    .mapToDouble(item -> item.getPrice().doubleValue()) // Map to price
    .sum();                                        // Sum prices
```

### **Predicate Combination**
```java
Predicate<Product> foodFilter = p -> p instanceof Food;
Predicate<Product> priceFilter = p -> p.getPrice().compareTo(BigDecimal.valueOf(2)) < 0;
list.stream()
    .filter(foodFilter.negate().or(priceFilter))   // Non-food or price < 2
    .forEach(p -> p.setDiscount(0.1));             // Apply 10% discount
```

### **Function Composition**
```java
Function<Product, String> nameMapper = p -> p.getName();
UnaryOperator<String> trimMapper = String::trim;
ToIntFunction<String> lengthMapper = String::length;
list.stream()
    .map(nameMapper.andThen(trimMapper))           // Get name, trim
    .mapToInt(lengthMapper)                        // Map to length
    .sum();                                        // Sum lengths
```

### **Sorting and Limiting**
```java
Stream.of("A", "C", "B", "D", "B", "D")
    .distinct()                                    // Remove duplicates
    .sorted()                                      // Sort (A, B, C, D)
    .skip(2)                                       // Skip first 2 (C, D)
    .forEach(System.out::println);                 // Print each
```

### **TakeWhile/DropWhile**
```java
Stream.of("B", "C", "A", "E", "D", "F")
    .takeWhile(s -> !s.equals("D"))                // Take until "D" (B, C, A)
    .dropWhile(s -> !s.equals("C"))                // Drop until "C" (C, A)
    .limit(2)                                      // Limit to 2
    .forEach(System.out::println);                 // Print: C, A
```

### **Aggregation Operations**
```java
List<Integer> numbers = List.of(10, 20, 30);
long count = numbers.stream().count();             // 3
Optional<Integer> max = numbers.stream().max(Integer::compare); // 30
int sum = numbers.stream().mapToInt(i -> i).sum(); // 60
OptionalDouble avg = numbers.stream().mapToInt(i -> i).average(); // 20.0
```

## **Key Points**
- **BaseStream**: Defines core stream behavior (sequential/parallel).
- **Generics**: `Stream<T>` supports generics; primitive streams avoid boxing.
- **Parallel Streams**: Use `parallel()` for performance; `forEachOrdered` ensures order.v
- **Functional Interface Defaults**:
  - `Function`: `andThen`, `compose`, `identity`.
  - `Predicate`: `and`, `or`, `negate`, `isEqual`.
- **Thread Safety**: Streams are not thread-safe; ensure proper synchronization in parallel processing.

---
