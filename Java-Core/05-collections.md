### **Collections in Java**

A **collection** is a group of objects, also known as its elements. 

To handle such collections efficiently, Java provides a robust set of predefined **classes and interfaces** called the **Collections Framework**, available in the `java.util` package.  

---

### **Why Learn Collections?**

A common question is: *Why use collections instead of arrays?*  

While arrays are fundamental and useful for storing data, they have significant limitations:  
1. **Fixed Size**: The size must be declared during initialization and cannot be changed later.  
2. **Static Nature**: Arrays remain of fixed size, regardless of requirements.  
3. **Limited Operations**: Arrays lack built-in methods for tasks like insertion, removal, searching, or sorting.  
4. **No Data Structure Support**: Arrays aren't based on widely-used data structures.  
5. **Homogeneous Elements**: Arrays can only store elements of the same type.  

---

### **Advantages of Collections**

- **Dynamic Sizing**: Collections can grow or shrink as needed.  
- **Reduced Effort**: Predefined methods simplify programming.  
- **Enhanced Performance**: Boosts program quality and speed.  
- **Versatile Operations**: Provide methods for insertion, deletion, sorting, and more.  

---

### **Arrays vs. Collections**

| **Aspect**              | **Arrays**                                  | **Collections**                              |
|--------------------------|---------------------------------------------|----------------------------------------------|
| **Size**                | Fixed size                                  | Dynamically growable or shrinkable           |
| **Data Type**           | Can hold primitives and objects             | Can hold only objects (no primitives)        |
| **Data Homogeneity**    | Stores homogeneous elements                 | Supports both homogeneous and heterogeneous elements |
| **Performance**         | High performance, poor memory utilization   | Lower performance, better memory utilization |
| **Ease of Coding**      | Complex coding                              | Simplifies coding with built-in methods      |

--- 

### **Types of Collections**

1. **Lists**  
   - Ordered collection that may contain duplicates.  
   - Similar to arrays but more versatile.  

2. **Sets**  
   - Do not allow duplicates.  
   - Provide random access to elements.  

3. **Maps**  
   - Associate unique keys with values.  
   - Allow random access via keys.

---

### **Important Methods in the `Collection` Interface**  

The `Collection` interface, part of `java.util`, is the root interface for most collection classes. Key methods include:  

| **Method**                  | **Description**                          |
|-----------------------------|------------------------------------------|
| `boolean add(Object obj)`   | Adds an object to the collection.        |
| `void clear()`              | Removes all elements.                   |
| `boolean contains(Object obj)` | Checks if an object is in the collection. |
| `boolean equals(Object obj)` | Compares two collections for equality.  |
| `int hashCode()`            | Returns the hash code of the collection.|
| `boolean isEmpty()`         | Checks if the collection is empty.      |
| `Iterator iterator()`       | Returns an iterator for the collection. |
| `boolean remove(Object obj)` | Removes a specific object.             |
| `int size()`                | Returns the number of elements.         |

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

---

### **The `List` Interface**  

The `List` interface extends `Collection` and represents an **ordered collection (sequence)**.  

- **Key Features**:  
  - Preserves insertion order.  
  - Allows duplicate elements.  
  - Access elements by index.  

- **Implementation Classes**:  
  - `ArrayList`  
  - `LinkedList`  
  - `Vector`  
  - `Stack`  

---

### **The `ArrayList` Class**  

`ArrayList` is a popular implementation of the `List` interface.  

- **Features**:  
  - Automatically grows in capacity.  
  - Allows duplicates.  
  - Maintains insertion order.  
  - Default initial capacity is 10 (can be customized).  

- **Creating an `ArrayList`**:  
```java
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();
list.add("Example");
```

--- 


================================================================================================================================

Here’s an enhanced and concise version of your notes:

---

### **Creating an `ArrayList`**

`ArrayList` can be created in two ways:  

1. **Type-Unsafe `ArrayList`**  
   - Syntax:  
     ```java
     ArrayList obj = new ArrayList();
     ```
   - Allows adding elements of different types without restriction:  
     ```java
     obj.add("Amit");  // String
     obj.add(25);      // Integer
     obj.add(true);    // Boolean
     ```
   - While easy to create, there’s no type safety, making it error-prone during runtime.  

2. **Type-Safe `ArrayList`**  
   - Syntax (using the diamond operator `< >` introduced in Java 7):  
     ```java
     ArrayList<String> obj = new ArrayList<>();
     ```
   - Ensures only specified data types (e.g., `String`) can be added:  
     ```java
     obj.add("Amit");  // Correct
     obj.add(25);      // Error: Compiler prevents it
     obj.add(true);    // Error: Compiler prevents it
     ```

---

### **Adding Elements to an `ArrayList`**

Use the `add()` method to insert elements:  

1. **Add at the end**:  
   - Prototype:  
     ```java
     public boolean add(Object obj)
     ```
   - Adds the object to the end of the list.  
     ```java
     cities.add("Bhopal");
     ```

2. **Add at a specific index**:  
   - Prototype:  
     ```java
     public void add(int index, Object obj)
     ```
   - Inserts the object at the specified index. Throws `IndexOutOfBoundsException` if the index is invalid.  
     ```java
     cities.add(0, "Indore");
     ```

---

### **Retrieving Elements from an `ArrayList`**

Use the `get()` method to retrieve elements:  

- **Prototype**:  
  ```java
  public Object get(int index)
  ```
- Accepts an index number and returns the element at that position.  
- Throws `IndexOutOfBoundsException` if the index is invalid.  

**Example**:  
```java
String city = cities.get(0);  // Retrieves the element at index 0
```

---
Here’s an enhanced and concise version of your notes:

---

### **Retrieving Elements from an `ArrayList`**

To retrieve elements, use the `get()` method:  
```java
String s = cities.get(0); // Retrieves the element at index 0
String p = cities.get(1); // Retrieves the element at index 1

System.out.println(s); // Outputs: Indore
System.out.println(p); // Outputs: Bhopal
```

---

### **Checking the Size of an `ArrayList`**

- The `size()` method returns the total number of elements currently in the `ArrayList`.  

**Syntax**:  
```java
public int size()
```

**Example**:  
```java
int n = cities.size();
System.out.println("Size: " + n);
```

---

### **Iterating Through an `ArrayList`**

#### Using Enhanced `for` Loop:  
The enhanced `for` loop simplifies traversal.  
```java
for (String item : cities) {
    System.out.println("Retrieved element: " + item);
}
```

---

### **Searching Elements in an `ArrayList`**

1. **Using `contains()` Method**:  
   - Checks if an element exists in the `ArrayList`.  
   - Returns `true` if found, `false` otherwise.  
   ```java
   boolean found = cities.contains("Bhopal"); // Returns true if "Bhopal" exists
   ```

2. **Using `indexOf()` Method**:  
   - Returns the index of the first occurrence of the element.  
   - Returns `-1` if the element is not found.  
   ```java
   int index = cities.indexOf("Bhopal"); // Returns index of "Bhopal"
   ```

---

### **Removing Elements from an `ArrayList`**

Use the `remove()` method to delete elements.  

**Method Variants**:  
1. **By Index**:  
   ```java
   cities.remove(0); // Removes the element at index 0
   ```

2. **By Object**:  
   ```java
   cities.remove("Indore"); // Removes "Indore" from the list
   ```

---

### **Custom `ArrayList`**

A **custom `ArrayList`** stores objects of user-defined classes.  

#### Example:  
If we have a class `Emp` and want to store `Emp` objects:  
```java
ArrayList<Emp> employees = new ArrayList<>();
```

Here, `Emp` is a programmer-defined class, and the `ArrayList` will store objects of type `Emp`.

---

### **Summary of Key Methods**  

| **Method**               | **Description**                                         |
|--------------------------|---------------------------------------------------------|
| `add()`                  | Adds an element to the `ArrayList`.                     |
| `get()`                  | Retrieves an element at a specified index.              |
| `size()`                 | Returns the number of elements in the `ArrayList`.      |
| `contains()`             | Checks if a specific element exists.                    |
| `indexOf()`              | Finds the index of a specific element.                  |
| `remove()`               | Removes an element by index or by value.                |

--- 


Here’s a concise and polished version of your notes:

---

### **Creating a Custom `ArrayList`**

To store objects of a user-defined class in an `ArrayList`, follow these steps:

#### **Adding Objects to a Custom `ArrayList`**
1. Create an `ArrayList` for the class type:  
   ```java
   ArrayList<Emp> empList = new ArrayList<>();
   ```
2. Create objects of the custom class and add them using the `add()` method:  
   ```java
   Emp e = new Emp(21, "Ravi", 50000.0);
   Emp f = new Emp(25, "Sumit", 40000.0);

   empList.add(e);
   empList.add(f);
   ```

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
```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Emp emp = (Emp) obj;
    return id == emp.id && name.equals(emp.name) && salary == emp.salary;
}
```

---

### **Sorting a Custom `ArrayList`**

The `Collections` class in `java.util` provides a static `sort()` method to sort `ArrayList` elements:  

#### **Prototype**:  
```java
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

### **Summary of Key Points**

- Always override the `equals()` method in custom classes to ensure correct behavior for `remove()`, `indexOf()`, and `contains()`.
- Use the `Collections.sort()` method to sort `ArrayList` elements.  
- For custom sorting, implement the `Comparable` interface or use a `Comparator`.

--- 


Here’s a concise and polished version of your notes:

---

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

### **Adding Elements to a `LinkedList`**

#### **Example**:
```java
LinkedList<String> cities = new LinkedList<>();
cities.add("Bhopal");
cities.add("Paris");
cities.add("Delhi");
```

This creates three nodes, with each node holding the values `"Bhopal"`, `"Paris"`, and `"Delhi"`. The nodes are linked together via references.  

#### **Adding an Element at a Specific Position**:
To add `"New York"` at position 3 (index 2):
```java
cities.add(2, "New York");
```

**Steps Performed Internally**:
1. Create a new node with `"New York"`.  
2. Break the link between `"Paris"` and `"Delhi"`.  
3. Adjust the links:  
   - `"Paris"` ↔ `"New York"` ↔ `"Delhi"`.  

**Resulting List**:  
`"Bhopal" ↔ "Paris" ↔ "New York" ↔ "Delhi"`

---

### **Accessing Elements in a `LinkedList`**

- **`get()` Method**:  
  Allows retrieval of elements by index but requires sequential traversal, making it slower for large lists.

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
Here’s an enhanced and concise version of your notes:

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

**Example**:
```java
HashSet<String> hSet = new HashSet<>();
hSet.add("C");
hSet.add("A");
hSet.add("B");
```

---

### **What is an Iterator?**

An `Iterator` allows sequential traversal of elements in **List** and **Set** collections.

#### **Key Features**:
1. Acts as a pointer to iterate through elements in a collection.  
2. Obtained by calling the `iterator()` method of a collection class.  

**Prototype**:  
```java
public Iterator iterator()
```

3. Provides methods to traverse the collection:  
   - `boolean hasNext()`: Returns `true` if more elements exist; otherwise, `false`.  
   - `Object next()`: Returns the next element in the collection.

#### **Example**:
```java
HashSet<String> hSet = new HashSet<>();
hSet.add("C");
hSet.add("A");
hSet.add("B");

Iterator<String> itr = hSet.iterator();
while (itr.hasNext()) {
    System.out.println(itr.next());
}
```

---

### **Key Points**

1. `Set` collections do not allow duplicates.  
2. `HashSet` provides unordered storage, while `TreeSet` ensures sorted order.  
3. Use `Iterator` for sequential access to elements in a `Set` or `List`.  


### Enhanced Notes

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

---

### **Working of `Iterator`**

1. **Initialization**:  
   ```java
   Iterator it = hs.iterator();
   ```
   - This initializes the cursor **before** the first element in the collection.

2. **Traversal**:  
   - Call `it.hasNext()` to check if the next element exists.  
   - Call `it.next()` to move the cursor to the next element and retrieve it.  

3. **Example**:
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

1. Obtain an iterator using `collection.iterator()`.  
2. Use a loop to check `hasNext()` and iterate as long as it returns `true`.  
3. Use `next()` to access each element during the loop.  

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
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
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

#### **What is a Hash Code?**
- A hash code is an integer uniquely identifying an object, calculated using the `hashCode()` method (inherited from the `Object` class).  
- In collections, **if two objects are equal, their hash codes must also be equal**.  

#### **The HashCode-Equals Contract**:
- To prevent duplicate entries in `HashSet`, override both `hashCode()` and `equals()` in your class.  

---

### **The `TreeSet` Class**

- Implements the `Set` interface.  
- Stores elements in a **binary search tree** (BST).  
- Automatically arranges elements in **sorted order**.  

#### **When to Use `TreeSet`**:
- When you need to retrieve elements in sorted order.  

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
    private String title;
    private String author;
    private int price;

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public int compareTo(Book other) {
        return this.price - other.price; // Ascending order by price
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + "', author='" + author + "', price=" + price + '}';
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
   Returns the smallest (lowest) element in the set.  

2. **`last()`**:  
   Returns the largest (highest) element in the set.  

3. **`lower(Object obj)`**:  
   Returns the largest element strictly less than the given object, or `null` if no such element exists.  

4. **`higher(Object obj)`**:  
   Returns the smallest element strictly greater than the given object, or `null` if no such element exists.  

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


