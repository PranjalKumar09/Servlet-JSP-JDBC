### Spring Security Custom Login Page and Logout Configuration

#### 1. **Creating a Custom Login Page in Spring Security**

- **Login Page Configuration**:
  - Define a custom login page URL using `.loginPage("/signIn")`.
  - Set a login processing URL using `.loginProcessingUrl("/login")`. This URL handles form submissions from the login page, so the `action` attribute in the form should match this URL (`<form action="/login" method="post">`).
  - Optionally, handle login failures using `.failureUrl("/invalid")`, which redirects users to an error page if login fails.

- **Controller Mappings for Custom Login and Error Pages**:
  - Create controller mappings to serve the custom login and error pages:
    ```java
    @RequestMapping("/signIn")
    public String signIn() {
        return "login";  // Return "login.html" for the custom login page
    }

    @RequestMapping("/invalid")
    public String error() {
        return "error";  // Return "error.html" for login errors
    }
    ```

#### 2. **Setting Up Security Filter Chain in `SecurityConfig`**

The `filterChain` method configures the core aspects of authentication and access control:

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for testing (re-enable for production)

        // Authorization Configurations
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/signIn", "/login").permitAll()  // Allow public access to login/sign-in URLs
            .anyRequest().authenticated()  // Require authentication for all other endpoints
        )

        // Custom Form Login Configuration
        .formLogin(form -> form
            .loginPage("/signIn")  // Define custom login page
            .loginProcessingUrl("/login")  // URL that processes the login form submission (it is action of form)
            .defaultSuccessUrl("/profile", true)  // Redirect to profile page upon successful login
            .permitAll()  // Allow public access to the login page and form
        )

        // Custom Logout Configuration
        .logout(logout -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout1", "POST"))  // Specify logout URL with POST method
            .logoutSuccessUrl("/logout1?logout=true")  // Redirect after logout to custom logout page
            .invalidateHttpSession(true)  // Invalidate session upon logout
            .deleteCookies("JSESSIONID")  // Clear session cookie
            .addLogoutHandler(new CookieClearingLogoutHandler("JSESSIONID"))  // Explicitly clear JSESSIONID cookie
            .permitAll()  // Allow public access to logout
        );

    return http.build();
}
```

#### 3. **Detailed Explanation of Security Configuration Parts**

- **CSRF Configuration**:
  - `.csrf(AbstractHttpConfigurer::disable)` disables CSRF for testing purposes. CSRF protection is recommended in production for security, but it can interfere with logout functionality if using non-POST methods for logout.

- **Authorization Settings**:
  - `.authorizeHttpRequests(...)`: This section controls which URLs require authentication.
    - `.requestMatchers("/signIn", "/login").permitAll()`: Allows unauthenticated users access to the `/signIn` (custom login page) and `/login` (form submission URL).
    - `.anyRequest().authenticated()`: Requires all other URLs to be accessed only by authenticated users.

- **Custom Login Configuration**:
  - `.formLogin(...)`: Configures a custom login page and its behaviors.
    - `.loginPage("/signIn")`: Specifies the URL to serve the login form.
    - `.loginProcessingUrl("/login")`: Specifies the URL for form submissions, where Spring Security handles authentication.
    - `.defaultSuccessUrl("/profile", true)`: After successful login, redirects users to `/profile`.
    - `.failureUrl("/invalid")`: Optionally, redirects to `/invalid` if login fails, showing an error page.

- **Custom Logout Configuration**:
  - `.logout(...)`: Configures logout behavior.
    - `.logoutRequestMatcher(new AntPathRequestMatcher("/logout1", "POST"))`: Specifies a custom logout URL (`/logout1`) that requires a POST request. Using POST is more secure and prevents accidental logouts from simple URL access.
    - `.logoutSuccessUrl("/logout1?logout=true")`: Redirects to `/logout1?logout=true` after logout. This URL can serve a custom logout page.
    - `.invalidateHttpSession(true)`: Ends the session and clears session attributes.
    - `.deleteCookies("JSESSIONID")`: Deletes the `JSESSIONID` cookie upon logout.
    - `.addLogoutHandler(new CookieClearingLogoutHandler("JSESSIONID"))`: Clears any lingering session cookies to ensure a complete logout.

#### 4. **Custom Logout Controller and Page**

- **Logout Controller Mapping**:
  - The controller serves the logout page when accessed with the `logout=true` parameter.
    ```java
    @GetMapping("/logout1")
    public String logout(@RequestParam(value = "logout", required = false) String logout) {
        return "logout";  // Show custom logout page if `logout=true`
    }
    ```

- **Logout HTML Page**:
  - `logout.html` displays a confirmation message for successful logout.
  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Logout Page</title>
  </head>
  <body>
    <h1>You have successfully logged out.</h1>
    <a href="/signIn">Click here to login again</a>
  </body>
  </html>
  ```

#### 5. **Login Error Handling**

- **Error Display on the Same Page**:
  - Display login errors directly on the login page using Thymeleaf:
    ```html
    <th:block th:if="${param.error}">
        <p class="text-center text-danger">Invalid details</p>
    </th:block>
    ```

- **Separate Error Page**:
  - Optionally, redirect to a separate error page for failed logins using `.failureUrl("/invalid")` in `SecurityConfig`.

-  `` for custom error messages




