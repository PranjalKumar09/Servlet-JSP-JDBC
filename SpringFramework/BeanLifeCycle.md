**Bean Life Cycle in Spring**
 

### 1. **XML Configuration Method**

  - Define the `init` and `destroy` methods in the XML file.
```xml
<bean class="org.example.Student2" name="student2" p:id="2" p:name="Raja" init-method="init" destroy-method="destroy" />
```

  - In `Student2` class:
``` java
public void init() {
    System.out.println("init called");
}
public void destroy() {
    System.out.println("destroy called");
}
```

### 2. **Interface Implementation Method**

  - Implement `InitializingBean` and `DisposableBean` interfaces in the class.
```java
public class Student3 implements InitializingBean, DisposableBean {
    @Override
    public void destroy() {
        System.out.println("destroyer called");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        // Initialization logic
    }
}
```

  - **No changes in XML required**:
```xml
<bean class="org.example.Student3" name="student3" p:id="2" p:name="Raja" />
```

### 3. **Annotation Method**

  - Add Jakarta library dependency:
```xml
<dependency>
  <groupId>jakarta.servlet</groupId>
  <artifactId>jakarta.servlet-api</artifactId>
  <version>6.1.0</version>
  <scope>provided</scope>
</dependency>
```

  - Use `@PostConstruct` and `@PreDestroy` annotations:
```java
@PostConstruct
public void init() {
    System.out.println("init called");
}

@PreDestroy
public void destroy() {
    System.out.println("destroy called");
}
```

 
**Autowiring in Spring**
 

  - **Autowiring**: Automatically injects object dependencies using setter or constructor injection.
#### Advantages:

  - Reduces code by handling dependencies implicitly.
#### Disadvantages:

  - Less control for the developer specially over primitive data type
### Autowiring Modes:

  1. **No** (default)
  2. **byName**
  3. **byType**
  4. **Constructor**
  5. **autodetect** (deprecated)
#### Example:

```xml
<bean name="address" class="com.autowiring.Address" p:city="Sidhi" p:state="MP" />
<bean name="st" class="com.autowiring.Student" autowire="byName" />
```

  - Bean name (`address`) must match the field name in the `Student` class.
### Autowiring by Type:

```xml
<bean name="st" class="com.autowiring.Student" autowire="byType" />
```

### Autowiring by Constructor:

```xml
<bean name="st" class="com.autowiring.Student" autowire="constructor" />
<bean name="st" class="com.autowiring.Student" autowire="byType"> </bean>

```
- Ensure constructor arguments match the required types.
-If multiple constructor present , we can do `primary ="true"` in bean

#### Using `@Autowired` (Annotation-Based Autowiring):

  - Include `<context:annotation-config />` in the XML to enable annotations.
``` java
@Autowired
private Address address;

// Setter Injection
@Autowired
public void setAddress(Address address) {
    this.address = address;
}

// Constructor Injection
@Autowired
public Student(Address address) {
    this.address = address;
}
```

  - **@Qualifier**: Use when multiple beans are present:
``` java
@Autowired
@Qualifier("beann")
private Address address;
```
**Note**: If only one bean is present, `@Autowired` will work without `@Qualifier`.
