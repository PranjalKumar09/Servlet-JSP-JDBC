### **Exception Handling in Spring MVC**

1. **Handling All Errors with `Exception.class`:**
   - Use `Exception.class` to catch all errors, either locally in a controller or globally.

2. **Local Exception Handling in Controller:**
   ```java
   @RequestMapping("/home")
   public String home() {
       Integer.parseInt("Pranjal"); // Triggers NumberFormatException
       return "home";
   }

   @ExceptionHandler(NumberFormatException.class)
   public String numberException() {
       return "error"; // Custom error page
   }
   ```
   
3. **Global Exception Handling with `@ControllerAdvice`:**
   - Add `@ControllerAdvice` annotated classes to your component scan for global exception handling.

   ```java
   @ControllerAdvice
   public class CustomExceptionHandler {

       @ExceptionHandler(Exception.class)
       public String handleException() {
           System.out.println("General Exception");
           return "error"; // Error page for generic exceptions
       }

       @ExceptionHandler(NumberFormatException.class)
       public String handleNumberFormatException() {
           System.out.println("NumberFormatException");
           return "error"; // Error page for NumberFormatException
       }
   }
   ```
   - **Execution Order**: More specific exception classes (like `NumberFormatException`) are handled before general ones (`Exception.class`).

---

### **Interceptor in Spring MVC**

1. **Interceptor Concept**:
   - An **Interceptor** is similar to a **Servlet Filter** and is used to process requests before they reach the controller.
   - It allows handling of repetitive tasks like validation, logging, and authorization.

2. **AuthHandlerInterceptor Example**:
   ```java
   public class AuthHandlerInterceptor implements HandlerInterceptor {

       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           System.out.println("preHandle Interceptor");
           User user = (User) request.getSession().getAttribute("loginUser");
           if (user != null) return true;

           response.getWriter().write("<h1>Please Login</h1>");
           return false; // Stops processing if user is not logged in
       }

       @Override
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           System.out.println("postHandle Interceptor");
       }

       @Override
       public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           System.out.println("afterCompletion Interceptor");
       }
   }
   ```
   - **Flow**: `preHandle()` must return `true` for `postHandle()` and `afterCompletion()` to be called.

3. **Configuring Interceptors in `spring_servlet.xml`**:
   ```xml
   <mvc:interceptors>
       <mvc:interceptor>
           <mvc:mapping path="/user/*" />
           <bean class="com.controller.AuthHandlerInterceptor" />
       </mvc:interceptor>
   </mvc:interceptors>
   ```

