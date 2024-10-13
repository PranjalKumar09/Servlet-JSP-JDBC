### Spring Expression Language (SpEL) Overview
SpEL is a powerful expression language in Spring that supports runtime manipulation of object graphs. It can be used in both XML and annotation-based Spring configurations.
#### Expression Types in SpEL:

  1. **Arithmetic**: Basic operations like `+`, `-`, `*`, `/`
  2. **Relational**: Comparisons like `==`, `>`, `<`, `>=`, `<=`
  3. **Logical**: Boolean logic like `&&`, `||`, `!`
  4. **Conditional**: Ternary operator (`? :`)
#### Key SpEL API Components:

  - **Expression Interface**: Represents an evaluatable expression.
  - **SpelExpression Class**: Implements the `Expression` interface.
  - **ExpressionParser Interface**: Parses expressions into `Expression` objects.
  - **SpelExpressionParser Class**: Standard implementation of `ExpressionParser`.
  - **EvaluationContext Interface**: Provides the context for expression evaluation.
  - **StandardEvaluationContext Class**: Default implementation of `EvaluationContext`.
### Example with Annotations

```java

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
    @Value("#{2+3}") public int sum;
    @Value("#{2-3}") public int sub;
    @Value("#{2*3}") public int mul;
    @Value("#{2/3}") public int div;
    @Value("#{1==1}") public boolean equalvalid;
    @Value("#{1>2 || 3==5}") public boolean checkStatus;
    @Value("#{1 lt 5}") public boolean checkStatus2;
    @Value("#{1<5 ? 'Yes' : 'No'}") public String str;
    @Value("#{T(java.lang.Math).sqrt(144)}") public double num4;
    @Value("#{T(java.lang.Math).sqrt(144).intValue()}") public int num4inInt;
    @Value("#{T(spel.Student).call()}") public String result;
    @Value("#{T(java.lang.Math).PI}") public double num5;

    public static String call() {
        return "Method Called";
    }
}
```

### Parsing SpEL Programmatically

```java
SpelExpressionParser expressionParser = new SpelExpressionParser();
Expression expression = expressionParser.parseExpression("'Hello World'");
System.out.println(expression.getValue(context));
```

### Example Output:

``` 
Student{sum=5, sub=-1, mul=6, div=0, equalvalid=true, checkStatus=false, checkStatus2=true, str='Yes', num4=12.0, num4inInt=12, result='Method Called', num5=3.141592653589793}
Hello World
```

### Switching from XML to Annotation-based Configuration

```java
package com.java_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Emp {
    @Autowired
    private Address address;

    @Override
    public String toString() {
        return "Emp{address=" + address + '}';
    }
}

@Component
public class Address {
    private String street;
    private String city;

    // Getters, setters, and toString()
}
```

### Application with Annotation-based Configuration

```java
package com.java_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Emp emp = (Emp) context.getBean("getEmp");
        System.out.println(emp);
    }
}
```

### Config Class

```java
package com.java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.java_config")
public class Config {

    @Primary
    @Bean
    public Address addrs() {
        return new Address();
    }

    @Bean
    public Address getAdd() {
        return new Address();
    }

    @Bean
    public Emp getEmp() {
        return new Emp();
    }

    @Bean(name = {"another_name"})
    public Emp getEmpByAnotherName() {
        System.out.println("getEmpByAnotherName called");
        return new Emp();
    }
}
```

### Handling Multiple Beans:

    ioc container first check with type name , then check it with  variable name , in multiple beans then give error if none found
  - Use `@Primary` in configuration to give one bean priority.
  - Use `@Qualifier` on the injection side to specify which bean to use.
  - Use `@Bean(name={"another_name"})` to define custom bean names.
