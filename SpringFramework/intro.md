
## Spring Framework
  - A **dependency injection** framework used to develop **loosely coupled** Java EE applications.
  - Facilitates easier development and testing of enterprise Java applications.
  - Developed by **Rod Johnson** in **2003**.
### IoC (Inversion of Control) Container
  - Responsible for **instantiating**, **configuring**, and **assembling** objects based on XML or annotations.
  - Two main types:
    a. **BeanFactory**: Basic container with limited functionality.
    b. **ApplicationContext**: Extends `BeanFactory` with auto-registration of `BeanPostProcessor`, `BeanFactoryPostProcessor`, and support for `ApplicationEvent` publishing. Advanced container used in large-scale applications.
    
#### Its Functions:
  - Instantiates application classes.
  - Configures objects and dependencies between them.
#### Example - **Interface & Implementations:**
``` java
public interface Action {
    void eat();
    void sleep();
}

public class Ram implements Action {
    public void eat() { System.out.println("Ram eats"); }
    public void sleep() { System.out.println("Ram sleeps"); }
}
```

##### With basic BeanFactory
``` java
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class App {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("beans.xml");
        BeanFactory factory = new XmlBeanFactory(resource);

        Ram ramBean = (Ram) factory.getBean("ram");
        ramBean.sleep();
    }
}

```

#### Main Class with Dependency Injection:

``` java
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Ram ramBean = (Ram) context.getBean("ram");
        ramBean.sleep();
    }
}
```

#### XML Configuration (`beans.xml`):
- Must set to UTF-16
```xml
<?xml version="1.0" encoding="UTF-16" ?>
<beans xmlns="http://www.springframework.org/schema/beans">
    <bean id="ram" class="org.example.Ram"/>
</beans>
```

### Dependency Injection (DI)

  - Design pattern that removes hard dependencies, making code more **loosely coupled**.
  - **Types**:
    a. **Setter Injection**
    b. **Constructor Injection**
#### Data Types in DI:

  - **Primitive**: int, char, long, etc.
  - **Collection**: list, set, map.
  - **Reference**: object.
### Setter Injection Example

``` java
public class Student {
    private int id;
    private String name;
    private String address;
    // It should must have setters
}
```

#### XML Configuration for Setter Injection:

``` xml
<bean id="student" class="org.example.Student">
    <property name="id" value="2"/>
    <property name="address" value="New Delhi"/>
    <property name="name" value="Jaislmer"/>
</bean>
```


Afer adding , below line we can do all like `p:--`
xmlns:p="http://www.springframework.org/schema/p"

``` xml
 <bean name="st3" class="org.example.Student" p:id="3" p:address="demo_address" p:name="demo_name">
    </bean>
```


#### Object refrence->

``` java 
public class Person {
    private String name;
    private  Address address;
    --
}
``` 

``` xml
    <bean class="org.example.Address" name="ad1" p:id="1" p:city="pune" >
    </bean>
    <bean class="org.example.Person" name="p1" p:name="Raghav" >
        <property name="address" ref="ad1"/>
    </bean>
```

Above xml can also be done like

``` xml
<bean class="org.example.Person" name="p1" p:name="Raghav" p:address-ref="ad1" >
    </bean>
```

``` java
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Person st1= (Person) context.getBean("p1");
```


### Constructor Injection

#### Ambiguity in Constructor Injection

  - **Problem**: When a class has multiple constructors, Spring might not know which one to invoke.
  - **Solution**:
    - Spring selects the constructor with the most arguments, unless specified via the `index` or `type` attributes in XML.

#### 2. **Spring’s Default Behavior**:

  - **Constructor Selection**:
    - By default, Spring resolves the ambiguity by selecting the constructor with the largest number of arguments that can be satisfied by the arguments specified in the XML `<constructor-arg>` tags.
    - If no `<constructor-arg>` tags are provided, Spring will try to use the default (no-arg) constructor if available.
  - **Data Type Matching**:
    - Spring attempts to match the types of the provided values in the XML configuration with the constructor parameters.
    - If multiple constructors can match, Spring resolves ambiguity by looking at the number and types of the arguments.
    - In the case of ambiguity, Spring might prioritize based on the order in which the constructor arguments are written, but this is not guaranteed.
#### 3. **Data Type in XML Configuration**:

  - By default, XML treats values as `String`. If you need to pass a different data type (like `int`), you must explicitly specify it or rely on Spring’s type conversion mechanism.
  - Example:
``` xml
<bean id="student" class="com.example.Student">
    <constructor-arg value="1" type="int" />
    <constructor-arg value="John" />
    <constructor-arg value="Computer Science" />
</bean>
```
  - But if  there is **only** constructor like down, then we don't need to specify type as int
``` java
student(int, String, String)
```

  - In this case, the first argument would be interpreted as an `int` (as specified), and the others default to `String`.
#### 4. **Index and Order**:

  - The `index` attribute can be used in `<constructor-arg>` to explicitly define the argument position in the constructor.
``` xml
<bean id="student" class="com.example.Student">
    <constructor-arg index="0" value="1" />
    <constructor-arg index="1" value="John" />
    <constructor-arg index="2" value="Computer Science" />
</bean>
```

#### 5. **Example: Student Class**:

``` java
public class Student {
    private int id;
    private String name;
    private String branch;
    private Address address;

    // Constructor 1
    public Student(int id, String name) {
        --
    }

    // Constructor 2
    public Student(int id, String name, String branch) {
        --
    }

    // Constructor 3
    public Student(int id, String name, String branch, Address address) {
        --
    }
}

public class Address {
    private String city;
    private String state;
    
    // Constructor and getters/setters...
}
```

#### 6. **Behavior Without index or type**:

  - If no `index` or `type` is specified, Spring attempts to match arguments by type and order, leading to possible ambiguity if multiple constructors share similar argument types.

