**Spring Boot Essentials**

```java
@SpringBootApplication
public class DemoProject1Application implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoProject1Application.class, args);
        MyMessage msg = context.getBean(MyMessage.class);
        System.out.println(msg.getMessage());
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello World");
    }
}
```


### Key Annotations

- **@SpringBootApplication**: Combines three annotations:
  1. **@SpringBootConfiguration**: Indicates configuration and enables bean definitions.
  2. **@EnableAutoConfiguration**: Enables Spring Boot’s auto-configuration based on classpath contents.
  3. **@ComponentScan**: Scans for components within the specified package.

- 1. **CommandLineRunner Interface**: A functional interface in Spring Boot used to execute code after the application context is loaded. Often used for initializing data or running startup logic.
  2. `run(String... args)` Method: This method is implemented when using `CommandLineRunner` and contains the code that should execute on application startup. The `args` parameter represents command-line arguments.

run(String... args) Method: This method is implemented when using CommandLineRunner and contains the code that should execute on application startup.
### Auto-Configuration in Spring Boot
- **Auto-Configuration**: Implemented via `spring-boot-autoconfigure.jar`, enables configuration without explicit setup.
- **Conditional Annotations**:
  - **@ConditionalOnClass**: Configuration activates only if specified classes are on the classpath.
  - **@ConditionalOnMissingBean**: Configures a bean only if it hasn’t been defined elsewhere.

### Configuration Files
- **application.properties**:
  - Set logging levels: `logging.level.org.springframework=debug`

### Component Examples

- **SecurityConfig**:
  ```java
  @Configuration
  public class SecurityConfig {
      // Security configuration
  }
  ```

- **MyMessage Component**:
  ```java
  @Component
  public class MyMessage {
      public String getMessage() {
          return "Hello, Kumar";
      }
  }
  ```

### Spring Boot Starters
- **spring-boot-starter-parent**:  
  ```xml
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>3.3.5</version>
      <relativePath/>
  </parent>
  ```
  - Manages dependencies and configurations for Spring Boot projects  
  - Sets default Java version to 1.8 and UTF-8 encoding  
  - Preconfigures plugins: surefire, jar, failsafe  
  - Executes `repackage` goal with `repackage` execution ID  
  - Supports resource filtering and profile-specific configs





- **Popular Starters**:
  - **spring-boot-starter-mappedByeb**: For web applications
  - **spring-boot-starter-security**: For security features

This setup provides a flexible, loosely coupled application structure, ideal for building and configuring Spring Boot projects.
