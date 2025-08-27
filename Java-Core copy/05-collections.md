### **Collections in Java**

--- 

### **Types of Collections**

1. **Lists**  

2. **Sets**  

3. **Maps**  
   - Allow random access via keys.

---

### **Collection vs. Collections**

- **`Collection`**:  
  - An interface in `java.util`.  
  - The parent interface for classes like `ArrayList`, `LinkedList`, `HashSet`, etc.  

- **`Collections`**:  
  - A utility class in `java.util`.  
  - Contains static methods for operations like sorting, copying, and searching.  
  - Popular methods:  
    - `sort()`  
    - `copy()`  
    - `binarySearch()`  
    - `reverse()`  
    - `shuffle()`
    - `fill()`: Replaces **all** elements of a list with the specified object.  
  ```java
  Collections.fill(list, "default"); // All elements become "default"



---

### **The `List` Interface**  

The `List` interface extends `Collection` and represents an **ordered collection (sequence)**.  

- **Implementation Classes**:  
  - `ArrayList`  
  - `LinkedList`  
  - `Vector`  
  - `Stack`  

====


`ArrayList` can be created in two ways:  

1. **Type-Unsafe `ArrayList`**  
2. **Type-Safe `ArrayList`**  

---
---
### **Overriding the `equals()` Method**

When adding custom objects to an `ArrayList`, you must override the `equals()` method in your class.  

#### **Why Override `equals()`?**
1. The `remove()`, `indexOf()`, and `contains()` methods internally call the `equals()` method.  
2. By default, the `equals()` method from the `Object` class compares memory addresses.  
   - Even if two objects have identical field values, they are considered unequal.  
3. Overriding `equals()` ensures meaningful comparisons of objects based on their data.  

#### **Example**:
Override the `equals()` method in your class:  
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass() ) return false;
            Emp emp = (Emp) obj;
            return id == emp.id && name.equals(emp.name) && salary == emp.salary;
        }
    

---

### **Sorting a Custom `ArrayList`**

The `Collections` class in `java.util` provides a static `sort()` method to sort `ArrayList` elements:  

#### **Prototype**:  
    ``` java
    public static void sort(List list)
    ```
    
#### **Natural Sorting Order**:
- **Strings**: Alphabetical order.  
- **Integers**: Numeric order.  
- **Dates**: Chronological order.  

However, calling `sort()` on a custom `ArrayList` like `empList` will result in an error because the sorting logic for `Emp` objects is undefined.  

---

### **Defining Sorting Logic for Custom Objects**

To enable sorting for a custom class, you must define a sorting order. This can be achieved by either:  

#### 1. **Implementing `Comparable` Interface**
- Define the "natural order" for objects in the class.  
- Override the `compareTo()` method in your custom class.  

**Example**:
```java
public class Emp implements Comparable<Emp> {
    int id;
    String name;
    double salary;

    public Emp(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int compareTo(Emp other) {
        return this.name.compareTo(other.name); // Sort by name
    }
}
```
Now, calling `Collections.sort(empList)` will sort the list by name.

---

#### 2. **Using a `Comparator`**
- Define multiple sorting orders by creating custom `Comparator` objects.  

**Example**:
```java
Comparator<Emp> salaryComparator = (e1, e2) -> Double.compare(e1.salary, e2.salary);

// Sort by salary
Collections.sort(empList, salaryComparator);
```

---
*Alternative Comparator Syntax**:  
Pass a `Comparator` object directly to `Collections.sort()`:  
```java
class LengthCompare implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}

// Usage:  


Collections.sort(menu, new LengthCompare()); // Sorts by string length

```

### **Using the `Comparable` Interface**

To define natural sorting order for custom objects, implement the `Comparable` interface and override its `compareTo()` method.

#### **Prototype**:  
```java
public int compareTo(Object obj)
```

---

### **Summary of Benefits of `ArrayList`**

1. Maintains the insertion order of elements.  
2. Dynamically grows as needed.  
3. Allows adding or removing elements at specific positions.  
4. Provides methods to manipulate stored objects efficiently.

---

### **The `LinkedList` Class**

- Implements the `List` interface.  
- Internally uses a **Doubly Linked List** structure.  
- No initial capacity—grows as elements are added.  
- Preserves the insertion order of elements.  
- **Strengths**: Best for frequent additions and removals.  
- **Weaknesses**: Poor choice for frequent element retrievals due to sequential access.  

---

### **Accessing Elements in a `LinkedList`**


#### **Comparison with `ArrayList`**:
- **`ArrayList`**: Supports **Random Access**, directly fetching elements by index.  
- **`LinkedList`**: Supports **Sequential Access**, traversing from the start to find the required element.

---

### **When to Use `ArrayList` vs `LinkedList`**

| **Criteria**        | **ArrayList**                    | **LinkedList**                  |
|----------------------|----------------------------------|----------------------------------|
| **Access Speed**    | Fast (Random Access)            | Slow (Sequential Access)        |
| **Add/Remove**      | Slower (resizing & shifting)     | Faster (node linking/unlinking) |
| **Memory Usage**    | Compact (contiguous storage)     | Higher (node pointers needed)   |

---

---

### **Summary of Benefits (LinkedList)**

1. Maintains the insertion order of elements.  
2. Efficient for adding/removing elements from the middle of the list.  
3. Ideal for sequential access but not for random access.

---

### **The `Set` Interface**

- **Extends** the `Collection` interface.  
- **Key Features**:  
  - Does not allow duplicate elements.  
  - Unordered collection (no guaranteed element order).  
  - Does not support indexing like lists or arrays.  

---

### **Implementation Classes of `Set`**

1. **HashSet**:  
   - Backed by a **hash table**.  
   - Provides **constant-time** lookup and insertion.  
   - Does not guarantee iteration order.  

2. **TreeSet**:  
   - Backed by a **Binary Search Tree (BST)**.  
   - Maintains elements in **sorted order**.

---

### **The `HashSet` Class**

- Implements the `Set` interface.  
- Internally uses **hashing** for storage and retrieval.  
- Does not guarantee the order of iteration.  

#### **Inserting Elements into a `HashSet`**  
Use the `add()` method to insert elements.  

**Prototype**:  
```java
public boolean add(Object obj)
```

- Accepts an object and attempts to add it to the `HashSet`.  
- Returns `true` if successful, otherwise `false` (e.g., for duplicate elements).  



---

### **What is an Iterator?**

Obtained by calling the `iterator()` method of a collection class.  

   - `boolean hasNext()`
   - `Object next()`

---

### **Key Points**

1. `Set` collections do not allow duplicates.  
2. `HashSet` provides unordered storage, while `TreeSet` ensures sorted order.  

---

### **`Iterator` Overview**

An `Iterator` allows sequential access to elements in a collection.  

#### **Key Methods of `Iterator`**:
1. **`hasNext()`**:  
   - Checks if the collection has more elements to iterate over.  
   - Returns `true` if elements exist; otherwise, `false`.  

2. **`next()`**:  
   - Moves the internal cursor to the next element and returns it.  
   - Throws `NoSuchElementException` if no element is present.
3. **`remove()`**:  
   - Removes the current element from the collection.  
   - Must be called **after** `next()` (i.e., per iteration).  

**Example**:  
```java
Iterator<String> itr = list.iterator();
while (itr.hasNext()) {
    String item = itr.next();
    if (item.equals("target")) {
        itr.remove(); // Removes "target"
    }
}
```

---

### **Working of `Iterator`**

1. **Initialization**:  
   ```java
   Iterator it = hs.iterator();
   ```
   - This initializes the cursor **before** the first element in the collection.


2. **Example**:
   ```java
   HashSet<String> hs = new HashSet<>();
   hs.add("January");
   hs.add("February");
   hs.add("March");
   hs.add("April");

   Iterator<String> it = hs.iterator();
   while (it.hasNext()) {
       String s = it.next();
       System.out.println(s);
   }
   ```

---

### **Steps to Use an `Iterator`**

Obtain an iterator using `collection.iterator()`.  


---

### **HashSet Demo**

#### **Example 1:**
```java
import java.util.*;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> hs = new HashSet<>();
        hs.add("Amit");
        hs.add("Sumit");
        hs.add("Amit"); // Duplicate element
        System.out.println(hs);
    }
}
```

**Output**:
```
[Amit, Sumit]
```

#### **Example 2:**
```java
class Student {
  // variable & method
}

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<Student> hs = new HashSet<>();
        Student s1 = new Student("Amit");
        Student s2 = new Student("Sumit");
        Student s3 = new Student("Amit");

        hs.add(s1);
        hs.add(s2);
        hs.add(s3); // Duplicate object
        System.out.println(hs);
    }
}
```

**Output**:
```
[Amit, Sumit, Amit]
```

---

### **Why Duplicates Occur in `HashSet`?**

- `HashSet` uses **hash codes** to identify objects in its underlying hash table.  
- When adding an object, Java calculates the hash code and checks for duplicates in the hash table.  
- If the hash code matches but the `equals()` method indicates inequality, the object is treated as unique.  

**Null Handling in `hashCode()`**:  
- If an object's field is `null`, it conventionally contributes `0` to the hash code.  

#### **What is a Hash Code?**
- A hash code is an integer uniquely identifying an object, calculated using the `hashCode()` method (inherited from the `Object` class).  
- In collections, **if two objects are equal, their hash codes must also be equal**.  

- However, **hash codes are not guaranteed to be unique**. Two different objects can still produce the same hash code, leading to potential collisions.  
- Example:
  ```java
  String s1 = "FB";
  String s2 = "Ea";
  
  System.out.println(s1.hashCode()); // 2236
  System.out.println(s2.hashCode()); // 2236
  ```
  Both strings yield the same hash code (2236) despite being different, demonstrating a hash collision.

- For scenarios requiring **secure or collision-resistant hashes**, consider using `java.security.MessageDigest` instead of relying on `hashCode()`.  
- `MessageDigest` provides cryptographic hash functions like SHA-256 and MD5 for more robust hashing needs.

#### **The HashCode-Equals Contract**:
- To prevent duplicate entries in `HashSet`, override both `hashCode()` and `equals()` in your class.  

- The method `Objects.hash(Object... values)` can be used to generate a composite hash code for multiple fields:
  ```java
  @Override
  public int hashCode() {
      return Objects.hash(name, price);
  }
  ```
- This approach is concise and ensures a consistent hash code based on relevant object fields.

---

### **The `TreeSet` Class**

- Implements the `Set` interface.  
- Stores elements in a **binary search tree** (BST).  


#### **Example**:
```java
import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>();
        ts.add("C");
        ts.add("A");
        ts.add("B");

        System.out.println(ts); // Output: [A, B, C]
    }
}
```
**Restriction**:  
- `TreeSet` cannot store `null` values (throws `NullPointerException`).  
- All elements must be mutually comparable (homogeneous).

---

### **Key Differences: `HashSet` vs. `TreeSet`**

| **Feature**          | **HashSet**                  | **TreeSet**                   |
|-----------------------|-----------------------------|-------------------------------|
| **Ordering**          | Unordered                  | Sorted                        |
| **Performance**       | Faster (O(1) for lookup)   | Slower (O(log n) for lookup)  |
| **Storage Structure** | Hash Table                 | Binary Search Tree (BST)      |
| **Null Elements**     | Allows one null element    | Does not allow null elements  |

--- 

### **Key Takeaways**

1. Use `HashSet` for fast lookups without concern for order.  
2. Use `TreeSet` for maintaining sorted order.  
3. Always override `hashCode()` and `equals()` in custom classes to ensure correct behavior in hash-based collections.  
4. `Iterator` is a versatile tool for accessing elements sequentially in any `Collection`.  

### **Adding Custom Objects to `TreeSet`**

#### **Scenario: ClassCastException**
If we try to add objects of a custom class (e.g., `Book`) to a `TreeSet` without making the class comparable, Java will throw a `ClassCastException`.  

#### **Why Does This Happen?**
When adding objects to a `TreeSet`, Java needs to compare them to maintain the natural order (ascending or descending).  
- **Homogeneous Objects**: All objects in the `TreeSet` must belong to the same class.  
- **Comparable Objects**: The class must implement the `Comparable` interface to define the sorting criteria.

If the class does not implement `Comparable`, Java cannot compare the objects, resulting in a `ClassCastException`.

---

### **Fix: Implementing the `Comparable` Interface**

To avoid `ClassCastException`, implement the `Comparable` interface in the custom class (e.g., `Book`). Define the `compareTo` method to specify how the objects should be compared.

#### **Example: Adding Custom Objects to a `TreeSet`**

```java
import java.util.*;

class Book implements Comparable<Book> {
   // variable & method

    @Override
    public int compareTo(Book other) {
        return this.price - other.price; // Ascending order by price
    }
    @Override
    public String toString() {
      // code
    }
}

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Book> ts = new TreeSet<>();

        Book b1 = new Book("Let Us C", "Kanetkar", 350);
        Book b2 = new Book("Java Certification", "Kathy", 650);
        Book b3 = new Book("Mastering C++", "Venugopal", 500);

        ts.add(b1);
        ts.add(b2);
        ts.add(b3);

        System.out.println(ts); // Books sorted by price
    }
}
```

**Output**:
```
[Book{title='Let Us C', author='Kanetkar', price=350}, 
 Book{title='Mastering C++', author='Venugopal', price=500}, 
 Book{title='Java Certification', author='Kathy', price=650}]
```

---

### **Key `TreeSet` Methods**

1. **`first()`**:  
   Returns  smallest (lowest) 

2. **`last()`**:  
   Returns  largest (highest) 

3. **`lower(Object obj)`**:  
   Returns  largest element strictly less than the given object, or `null` if no such element exists.  

4. **`higher(Object obj)`**:  
   Returns  smallest element strictly greater than the given object, or `null` if no such element exists.  

---

### **Example of Key `TreeSet` Methods**

```java
public class TreeSetMethodsDemo {
    public static void main(String[] args) {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(10);
        ts.add(20);
        ts.add(30);
        ts.add(40);

        System.out.println("TreeSet: " + ts);
        System.out.println("First Element: " + ts.first());
        System.out.println("Last Element: " + ts.last());
        System.out.println("Lower than 30: " + ts.lower(30));
        System.out.println("Higher than 30: " + ts.higher(30));
    }
}
```

**Output**:
```
TreeSet: [10, 20, 30, 40]
First Element: 10
Last Element: 40
Lower than 30: 20
Higher than 30: 40
```

---

### **`HashSet` vs `TreeSet`**

| **Feature**               | **HashSet**                         | **TreeSet**                        |
|---------------------------|--------------------------------------|-------------------------------------|
| **Ordering**              | Unordered                          | Sorted                             |
| **Performance**           | Faster (O(1) for most operations)  | Slower (O(log n) for most operations) |
| **Structure**             | Hash Table                         | Binary Search Tree (BST)           |
| **Null Values**           | Allows one null element            | Does not allow null elements       |
| **Additional Methods**    | No                                 | Provides methods like `first()`, `last()` |

---

### **Key Takeaways**

1. `TreeSet` requires custom classes to implement `Comparable` for sorting.  
2. Use `TreeSet` for ordered collections and `HashSet` for fast lookups without order.  
3. Implement both `hashCode()` and `equals()` in custom classes when using `HashSet`.  
4. `TreeSet` provides additional methods like `first()`, `last()`, `lower()`, and `higher()` for handling sorted elements effectively.  