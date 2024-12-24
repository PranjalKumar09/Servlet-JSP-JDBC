### OAuth2 Login with Spring Boot

#### Step 1: Add Dependencies
In your `pom.xml`, include the dependency for Spring Boot OAuth2 Client:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

#### Step 2: Configure Security
Create a `SecurityConfig` class to enable OAuth2 login.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .oauth2Login(Customizer.withDefaults());  // Enable OAuth2 login

        return http.build();
    }
}
```

#### Step 3: Set Up OAuth2 Providers (Google & GitHub)
- **Google OAuth2**:  
  Go to Google Cloud Console > OAuth Consent Screen > Credentials. Obtain the **Client ID** and **Client Secret**.

- **GitHub OAuth2**:  
  Go to GitHub Developer Settings > Developer Tools > OAuth Applications. Obtain the **Client ID** and **Client Secret**.

#### Step 4: Update `application.properties`
Add the OAuth2 provider credentials to the `application.properties` file.

```properties
# Google OAuth2 Configuration
spring.security.oauth2.client.registration.google.client-id=GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=GOOGLE_CLIENT_SECRET

# GitHub OAuth2 Configuration
spring.security.oauth2.client.registration.github.client-id=GITHUB_CLIENT_ID
spring.security.oauth2.client.registration.github.client-secret=GITHUB_CLIENT_SECRET
```

#### Step 5: Set Redirect URI
- For **GitHub**, set the redirect URI as `http://localhost:8080/login/oauth2/code/github`.
- For **Google**, set the redirect URI as `http://localhost:8080/login/oauth2/code/google`.

Now, your Spring Boot app is ready to use OAuth2 login with Google or GitHub!