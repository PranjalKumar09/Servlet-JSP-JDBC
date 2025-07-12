## **Java Collections vs Arrays**

### **Arrays**

* **Fixed size** (limited).
* **Homogeneous** data type (can store primitives or objects).
* Not based on standard data structure implementations.
* No built-in method support (manual operations).
* **Faster** performance for static/fixed-size data.
* Suitable for low-level programming or when size is known.

### **Collections**

* **Growable in nature**: Internally creates a new array and copies data when capacity is exceeded (performance overhead).
* Can store **homogeneous or heterogeneous** objects.
* Only supports **objects**, not primitives.
* Built-in **classes and methods** for operations.
* **Better readability and maintainability**.
* Lower performance compared to arrays for fixed-size use.
* Preferred for dynamic or flexible data structures.

---

## **Collections Framework Hierarchy**

### **Collection (Interface)**

* Represents a group of individual objects.
* Common methods:

  ```java
  boolean add(Object o);
  boolean addAll(Collection c);
  boolean remove(Object o);
  boolean removeAll(Collection c);
  boolean retainAll(Collection c);
  void clear();
  boolean contains(Object o);
  boolean containsAll(Collection c);
  int size();
  Object[] toArray();
  Iterator iterator(); // Forward-only
  boolean isEmpty();
  int hashCode(); // from object
  ```

---

## **List Interface**

* **Ordered collection**: Maintains insertion order.

* Allows **duplicate** elements.

* **Indexed access**:

  ```java
  void add(int index, Object o);
  boolean addAll(int index, Collection c);
  Object get(int index);
  Object set(int index, Object newElement);
  Object remove(int index);
  int indexOf(Object o);
  int lastIndexOf(Object o);
  ```

* **ListIterator**:

  * Supports both forward and **backward traversal**.
**Note**:  
- For **arrays**: `reviews[0]` (indexed access).  
- For **lists**: `reviews.get(0)` (method access).  
---

## **ArrayList (Class)**

* **Growable / Resizable array** implementation.
* Allows **null** and **duplicate** elements.
* Maintains **insertion order**.
* Supports **heterogeneous** objects.
* Implements `RandomAccess` for fast random access.

### **Constructors**

```java
ArrayList l = new ArrayList(); // Default capacity = 10
ArrayList<Product> list1 = new ArrayList<>();
ArrayList<Product> list2 = new ArrayList<>(20);       // Custom initial capacity  
ArrayList<Product> list3 = new ArrayList<>(set1);     // Initialize from existing collection  
List<Product> list4 = Arrays.asList(p1, p2);          // Fixed-size (backed by array)  
List<Product> list5 = List.of(p1, p2);                // Immutable (varargs)  
```

* When full, new capacity: `newCapacity = (currentCapacity * 3) / 2 + 1`

  * Example: 10 → 16 → 25

### **Type Safety (Generic)**

```java
ArrayList<String> list = new ArrayList<>();
```

---

## **Example**

```java
ArrayList l = new ArrayList();
l.add("A");
l.add(10);
l.add("A");
l.add(null); // Valid
```

---

## **Common Pitfall**

```java
ArrayList l = new ArrayList();
try {
    while (true) {
        l.add("MyString");
    }
} catch (RuntimeException e) {
    System.out.println("Runtime exception");
} catch (Exception e) {
    System.out.println("Exception");
}
System.out.println("Ready");
```

* **No output**; `OutOfMemoryError` is not a `RuntimeException` or `Exception`, so the catch blocks don’t execute.

---

## **indexOf() and equals()**

```java
List l = new ArrayList();
Patient p = new Patient("Mike");
l.add(p);

int f = l.indexOf(new Patient("Mike")); // equals() overridden then 0 else -1
int f = l.indexOf(p);                   // 0
// int f = l.indexOf(Patient("Mike")); // ERROR
Patient p1 = new Patient("Mike");
int f = l.indexOf(p1);                  //  equals() overridden then 0 else -1
if (f >= 0) System.out.println("found");
```


* `indexOf()` internally uses `equals()` method. Override it in custom classes for proper comparison.

---

## **ArrayList with Wrapper Class**

```java
ArrayList<Integer> l = new ArrayList<>();
l.add(2);
l.add(1);
l.add(3);
l.add(4);
l.add(11);
l.add(5);

l.remove(2); // Removes element at index 2 → value 3
l.remove(new Integer(2)); // Removes element with value 2
```

---

## **Set Interface**

* **No duplicates**.
* **No guaranteed insertion order**.
* Implementations:

  * `HashSet` (HS): No order, fast lookup.
  * `LinkedHashSet`: Maintains insertion order.
  * `TreeSet` (SS): Sorted order, no duplicates.

### **Set Implementations**  
```java
Set<Product> set1 = new HashSet<>();                  // Default capacity (16), load factor (0.75)  
Set<Product> set2 = new HashSet<>(20);                // Custom initial capacity  
Set<Product> set3 = new HashSet<>(20, 0.85f);        // Custom capacity + load factor  
Set<Product> set4 = new HashSet<>(list);              // Initialize from existing collection  
Set<Product> set5 = Set.of(p1, p2);                   // Immutable (varargs)  
```
- **Note**: `HashSet` doubles capacity when threshold (capacity × load factor) is reached.
---

## **Queue Interface**

* Follows **FIFO** (First-In, First-Out).
* Used in scenarios like job scheduling, buffering, etc.

### **Deque Interface**  
- **Double-ended queue** (supports insertion/removal at both ends).  
- Common methods:  
  ```java  
  Deque<Product> menu = new ArrayDeque<>();  
  menu.offerFirst(p1);       // Insert at head  
  menu.offerLast(p2);        // Insert at tail  
  Product tea = menu.pollFirst();  // Remove and return head (null if empty)  
  Product cake = menu.peekFirst(); // Retrieve head without removal  
  ```
---
- **Key Methods:**
``` java
items.put(k, v);                 // Insert/replace key-value pair  
items.putIfAbsent(k, v);         // Insert only if key absent  
items.remove(k);                 // Remove by key  
items.remove(k, v);              // Remove only if key-value match  
items.replace(k, newV);          // Replace value for key  
```



## **Map Interface**

* **Key-value pair** collection.
* Keys: unique; Values: can be duplicated.
* Common implementations:

  * `HashMap`, `TreeMap`, `LinkedHashMap`, etc.

---

## **Other Key Points**

* All collection classes are **serializable** by default.
* **RandomAccess interface**: Implemented by `ArrayList` and `Vector` for fast access by index.
* In collections, you can add objects of either the **current class or its subclasses**.

* `ArrayList` provides constructors to initialize with a specific capacity or copy elements from an existing collection.
### **Additional Constructors**

* `ArrayList(int capacity)`: Creates an `ArrayList` with the specified initial capacity.
* `ArrayList(Collection c)`: Creates an `ArrayList` containing the elements of the specified collection.

---

#### **5. Thread Safety & Legacy Collections**
 

### **Thread Safety**  
- **Modern collections (e.g., `ArrayList`, `HashMap`) are not thread-safe by default**.  
- **Solutions**:  
  ```java  
  Set<Product> readOnlySet = Collections.unmodifiableSet(set);  // Immutable view  
  Map<Product, Integer> syncMap = Collections.synchronizedMap(map); // Synchronized wrapper  
  List<Product> copyOnWriteList = new CopyOnWriteArrayList<>(list); // Thread-safe with copy-on-write  
  ```
* Legacy Alternatives:
  `Vector` (thread-safe `ArrayList`).
  `Hashtable` (thread-safe `HashMap`).

* Prevent collection corruption
  - unmodifiable (fast, read only)
  - synchornized (slow, unsclable)
  - copy-on-write (fast, SC increase)



EXTRA POINT
Map<String, Integer> map = Map.of("a", 1, "b", 2);
Set<String> keys = map.keySet(); // keys = ["a", "b"]



map have values() method 


HashMap, HashSet not thread safe
ArrayLIst not thread safe