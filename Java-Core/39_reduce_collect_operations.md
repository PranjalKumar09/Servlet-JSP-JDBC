# **Java Streams: `reduce()` and `collect()` Operations**

## **1. `reduce()` Operation**
The `reduce()` operation combines stream elements into a **single result** (e.g., sum, product, concatenation) using a specified accumulator function. It is ideal for **custom aggregations**.

### **Variants of `reduce()`**
1. **Basic `reduce()` (No Identity, Returns `Optional<T>`)**
   - **Syntax**: `Optional<T> reduce(BinaryOperator<T> accumulator)`
   - **Use Case**: Combines elements without an initial value; returns `Optional` (handles empty streams).
   - **Example**: Sum integers.
     ```java
     List<Integer> numbers = List.of(1, 2, 3);
     Optional<Integer> sum = numbers.stream()
                                   .reduce((a, b) -> a + b); // Optional[6]
     ```

2. **`reduce()` with Identity (Initial Value, Returns `T`)**
   - **Syntax**: `T reduce(T identity, BinaryOperator<T> accumulator)`
   - **Use Case**: Provides a default value (`identity`) for empty streams.
   - **Example**: Sum with initial value `0`.
     ```java
     int sum = numbers.stream()
                      .reduce(0, (a, b) -> a + b); // 6
     ```

3. **Advanced `reduce()` (Parallel-Stream Safe, Returns `U`)**
   - **Syntax**: `<U> U reduce(U identity, BiFunction<U, T, U> accumulator, BinaryOperator<U> combiner)`
   - **Use Case**: Merges partial results in **parallel streams** using a `combiner`.
   - **Example**: Concatenate sgitrings.
     ```java
     List<String> words = List.of("Hello", "World");
     String concat = words.parallelStream()
                         .reduce("", 
                                 (partial, str) -> partial + str, // Accumulator
                                 (s1, s2) -> s1 + s2);           // Combiner
     // Output: "HelloWorld"
     ```

### **Key Points for `reduce()`**
- **Identity**: Initial value and default for empty streams.
  - Addition: `0`
  - Multiplication: `1`
  - String concatenation: `""`
- **Accumulator**: `BinaryOperator` to combine two elements (e.g., `(a, b) -> a + b`).
- **Combiner**: Merges partial results in parallel streams.
- **Primitive Streams**: Prefer `sum()`, `min()`, `max()` for efficiency over `reduce()`.

### **Additional Examples**
- **Product of Numbers**:
  ```java
  int product = Stream.of(2, 3, 4)
                     .reduce(1, (a, b) -> a * b); // 24
  ```
- **Max Value**:
  ```java
  Optional<Integer> max = numbers.stream()
                                .reduce(Integer::max); // Optional[3]
  ```
- **Parallel Sum**:
  ```java
  int parallelSum = numbers.parallelStream()
                          .reduce(0, (a, b) -> a + b, (a, b) -> a + b); // 6
  ```

---

## **2. `collect()` Operation**
The `collect()` operation accumulates stream elements into a **result container** (e.g., collections, summaries) using a `Collector` interface implementation.

### **Variants of `collect()`**
1. **General `collect()` (Custom Collector)**
   - **Syntax**: `R collect(Supplier<R> supplier, BiConsumer<R, T> accumulator, BiConsumer<R, R> combiner)`
   - **Use Case**: Custom accumulation into a mutable container.
   - **Example**: Accumulate into a `StringBuilder`.
     ```java
     StringBuilder result = Stream.of("A", "B", "C")
                                 .collect(StringBuilder::new,
                                          StringBuilder::append,
                                          StringBuilder::append);
     // Output: "ABC"
     ```

2. **Predefined Collector (`Collectors` class)**
   - **Syntax**: `R collect(Collector<? super T, A, R> collector)`
   - **Use Case**: Use predefined `Collectors` for common operations.
   - **Example**: Collect to a `List`.
     ```java
     List<Product> drinks = list.stream()
                                .filter(p -> p instanceof Drink)
                                .collect(Collectors.toList());
     ```

3. **`collectingAndThen()` (Post-Processing)**
   - **Syntax**: `R collect(Collector<? super T, A, R> downstream, Function<R, RR> finisher)`
   - **Use Case**: Applies a finishing function to the collected result.
   - **Example**: Collect to a `List` and make unmodifiable.
     ```java
     List<Product> unmodifiable = list.stream()
                                     .filter(p -> p instanceof Drink)
                                     .collect(Collectors.collectingAndThen(Collectors.toList(),
                                                                          Collections::unmodifiableList));
     ```

### **Key Collectors (from `Collectors` class)**
- **Aggregation**:
  - `summarizingDouble(ToDoubleFunction<T>)`: Returns `DoubleSummaryStatistics` (min, max, sum, average).
    ```java
    DoubleSummaryStatistics stats = list.stream()
                                       .collect(Collectors.summarizingDouble(p -> p.getPrice().doubleValue()));
    // Access: stats.getMin(), stats.getMax(), stats.getSum(), stats.getAverage()
    ```
- **Joining**:
  - `joining()`: Concatenates strings.
  - `joining(delimiter)`, `joining(delimiter, prefix, suffix)`: Customizes concatenation.
    ```java
    String names = list.stream()
                       .map(Product::getName)
                       .collect(Collectors.joining(", ", "[", "]")); // [name1, name2, name3]
    ```
- **Grouping/Partitioning**:
  - `groupingBy(Function<T, K>)`: Groups elements into a `Map<K, List<T>>`.
    ```java
    Map<Customer, List<Product>> byCustomer = list.stream()
                                                 .collect(Collectors.groupingBy(Product::getCustomer));
    ```
  - `partitioningBy(Predicate<T>)`: Splits into `Map<Boolean, List<T>>` (true/false).
    ```java
    Map<Boolean, List<Product>> partitioned = list.stream()
                                                 .collect(Collectors.partitioningBy(p -> p.getPrice() > 10));
    ```
- **Mapping**:
  - `mapping(Function<T, U>, Collector)`: Applies transformation before collecting.
    ```java
    List<String> names = list.stream()
                             .collect(Collectors.mapping(Product::getName, Collectors.toList()));
    ```
- **Filtering**:
  - `filtering(Predicate<T>, Collector)`: Filters elements within a collector.
    ```java
    Map<Customer, List<Product>> cheapProducts = list.stream()
                                                    .collect(Collectors.groupingBy(Product::getCustomer,
                                                                                  Collectors.filtering(p -> p.getPrice() < 10,
                                                                                                       Collectors.toList())));
    ```
- **FlatMapping**:
  - `flatMapping(Function<T, Stream<U>>, Collector)`: Flattens nested streams within a collector.
    ```java
    Map<Customer, Set<Product>> items = orders.stream()
                                             .collect(Collectors.groupingBy(Order::getCustomer,
                                                                           Collectors.flatMapping(order -> order.items().stream(),
                                                                                                 Collectors.toSet())));
    ```

### **Key Points for `collect()`**
- **Collector Interface**: Defines `supplier`, `accumulator`, and `combiner`.
- **Performance**: `collect()` uses mutable containers, often more efficient than `reduce()` for complex aggregations.
- **Predefined Collectors**: Provided by `Collectors` class for common tasks (e.g., `toList`, `toSet`, `toMap`).
- **Downstream Collectors**: Combine with `mapping`, `filtering`, `flatMapping`, `groupingBy`, etc., for advanced operations.

---

## **Key Differences: `reduce()` vs. `collect()`**
- **Purpose**:
  - `reduce()`: Combines elements into a single value (e.g., sum, max).
  - `collect()`: Accumulates into a mutable container (e.g., `List`, `Map`).
- **Performance**:
  - `collect()` is often more efficient for complex aggregations due to mutable containers.
  - `reduce()` is simpler for single-value results but may be less efficient for large datasets.
- **Parallel Streams**:
  - Both support parallel processing with a `combiner`.
  - `collect()` is optimized for mutable reductions (e.g., `Collectors.toList()`).

---

## **Additional Examples**
- **Sum with `collect()`**:
  ```java
  double totalPrice = list.stream()
                          .collect(Collectors.summingDouble(p -> p.getPrice().doubleValue()));
  ```
- **Group by Price Range**:
  ```java
  Map<String, List<Product>> byPriceRange = list.stream()
                                               .collect(Collectors.groupingBy(p -> p.getPrice() < 10 ? "Cheap" : "Expensive"));
  ```
- **Join Names**:
  ```java
  String joined = list.stream()
                      .map(Product::getName)
                      .collect(Collectors.joining(";")); // name1;name2;name3
  ```

---


===

.toList() doesnt take anything


Collectors.joining() -> default is ""