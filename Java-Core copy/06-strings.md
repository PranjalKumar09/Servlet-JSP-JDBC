### Enhanced Notes on String Handling 
#### **Testing String Start/End**
- **`startsWith(String)`**:
  Tests if the string starts with a prefix.
  ```java
  System.out.println("Bhopal".startsWith("Bho"));  // true
  ```

- **`endsWith(String)`**:
  Tests if the string ends with a suffix.
  ```java
  System.out.println("Bhopal".endsWith("pal"));  // true
  ```

#### **Utility Methods**

- **`getChars(int srcBegin, int srcEnd, char[] dest, int destBegin)`**:
  Copies characters from the string into a character array.
  ```java
  char[] arr = new char[4];
  "Hello".getChars(0, 4, arr, 0);
  System.out.println(arr);  // Output: "Hell"
  ```


---


#### **Replacing Substrings**
- **`replace(int start, int end, String)`**:
   - Replaces the characters from `start` to `end-1` with the specified string.
   ```java
   StringBuffer sb = new StringBuffer("Hello World");
   sb.replace(6, 11, "India");
   System.out.println(sb);  // Output: "Hello India"
   ```


365
