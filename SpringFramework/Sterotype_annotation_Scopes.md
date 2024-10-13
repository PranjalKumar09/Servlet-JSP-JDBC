### Stereotype Annotations in Spring

  - **No Need for <bean> Declaration:**
With stereotype annotations, there's no need to manually define beans in XML.
  - **Component Scanning Configuration:**
To enable component scanning, add the following in `applicationContext.xml`:
```xml
<context:component-scan base-package="sterotype"/>
```

  - **Marking a Class as a Spring Bean:**
You can annotate a class with `@Component` to mark it as a Spring-managed bean.
```java
@Component
public class Emp {
    private int id;
    private String name;
}

Emp student = (Emp) context.getBean("emp");
```
By default, the bean name is the class name in lowercase (e.g., `emp` for `Emp` class).
  - **Custom Bean Name:**
To assign a custom name to the bean:
```java
@Component("employee")
public class Emp {
    private int id;
    private String name;
}

Emp student = (Emp) context.getBean("employee");
```

  - **Injecting Values:**
You can inject values directly using `@Value`:
```java
@Value("23")
private int id;

@Value("Name")
private String name;
```

  - **Injecting Collections:**
You can inject collections (e.g., a list) using SpEL:
```java
@Value("#{ad}")
private List<String> ad;
```

 

### XML Configuration (applicationContext.xml)

```xml
<?xml version="1.0" encoding="UTF-16"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
           http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- Annotation-based configuration -->
    <context:annotation-config />
    <context:component-scan base-package="sterotype"/>

    <!-- Defining a collection -->
    <util:list list-class="java.util.ArrayList" id="ad">
        <value>China</value>
        <value>India</value>
        <value>US</value>
    </util:list>

</beans>
```

### Key Additions:

  - **Util Namespace:**
Added to allow for the collection definition:
```java
xmlns:util="http://www.springframework.org/schema/util"
http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
```

  - **Collection Example:**
```java
<util:list list-class="java.util.ArrayList" id="ad">
    <value>China</value>
    <value>India</value>
    <value>US</value>
</util:list>
```



## Spring Bean Scopes

  1. **Singleton (default)**:
Single instance of  bean is created for the entire Spring IoC container. Every request for this bean will return the same instance.
  2. **Prototype**:
A new instance of the bean is created every time it is requested from the Spring IoC container.
  3. **Request**:
A new bean instance is created for each HTTP request. This scope is only valid in a **web-aware** Spring ApplicationContext.
  4. **Session**:
A new bean instance is created for each HTTP session. Also valid only in a **web-aware** Spring ApplicationContext.
  5. **Global-session**:
Used in **portlet** applications; a new bean instance is created for each global HTTP session. Applicable only in **web-aware** Spring ApplicationContext.
### Example with `@Scope`

```java
@Scope("prototype")
@Component
public class Emp {
    // class definition
}
```
When you request the bean from the context:
```java
Emp emp1 = (Emp) context.getBean("emp");
System.out.println(emp1.hashCode());

Emp emp2 = (Emp) context.getBean("emp");
System.out.println(emp2.hashCode());
```

  - **Singleton**: Both `emp1` and `emp2` will have the **same hashCode** (same instance).
  - **Prototype**: `emp1` and `emp2` will have **different hashCodes** (different instances).
### Defining Scope in XML
You can also define the scope in an XML configuration:
```xml
<bean id="emp" class="com.example.Emp" scope="prototype" />
```