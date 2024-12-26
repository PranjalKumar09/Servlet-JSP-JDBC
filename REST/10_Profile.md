### Spring Boot Profiles - Concise Overview

- **Definition**: A **profile** in Spring Boot allows for the segregation of configuration settings based on different runtime environments (e.g., **dev**, **test**, **uat**, **prod**).
- **Usage**: Profiles enable you to configure specific properties for various environments, making it easier to manage environment-specific settings.

---

### Key Points:

1. **Active Profile**:
   - Spring Boot uses `spring.profiles.active` to specify which profile to activate. For example:
     ```properties
     spring.profiles.active=dev
     ```
   - This will activate the **dev** profile and load configurations from `application-dev.properties` or `application-dev.yml`.

2. **Default Profile**:
   - If no active profile is set, Spring Boot defaults to the `default` profile:
     ```
     No active profile set, falling back to 1 default profile: "default"
     ```

3. **Profile-specific Configurations**:
   - You can create profile-specific configuration files, e.g., `application-dev.properties` or `application-dev.yml`, to override the default properties.
   - In **YAML** files, the profile can be defined like this:
     ```yaml
     spring:
       profiles:
         active: dev
     ```

4. **Avoiding Multiple Profile Files**:
   - If managing multiple profile files becomes cumbersome, consider using a single `application.yml` where profiles are organized under a structured hierarchy, such as:
     ```yaml
     spring:
       profiles:
         active: dev
     ---
     spring:
       config:
         activate:
           on-profile: dev
     ```

5. **Using Profiles in Code**:
   - You can access values from the active profile in your application by using the `@Value` annotation:
     ```java
     @Value("${my.config}")
     private String myConfig;

     @Value("${spring.profiles.active[0]}")
     private String activeProfile;
     ```

6. **Profile Activation and Fallback**:
   - If a property is defined in the active profile, it will be used. If not, the default profile will be used.
   - If the property is missing in both active and default profiles, an error will occur.

---

### Example - Profile Configuration in Spring Boot

#### `ProfileApplication.java`
```java
public class ProfileApplication implements CommandLineRunner {

    @Value("${my.config}")
    private String myConfig;

    @Value("${spring.profiles.active[0]}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Print the active configuration to the console
        System.out.println("Active profile: " + activeProfile);
        System.out.println("My config value: " + myConfig);
    }
}
```

### Conclusion:
- Spring Boot profiles allow flexible configuration management for different environments.
- Using **YAML** files over **properties files** is preferred in the industry for better structure and readability.