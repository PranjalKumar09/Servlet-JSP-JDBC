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
  Object  et(int index);
  Object set(int index, Object newElement);
  Object remove(int index);
  int indexOf(Object o);
  int lastIndexOf(Object o);
  ```

* **ListIterator**:

  * Supports both forward and **backward traversal**.

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

---

## **Queue Interface**

* Follows **FIFO** (First-In, First-Out).
* Used in scenarios like job scheduling, buffering, etc.

---

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

---

