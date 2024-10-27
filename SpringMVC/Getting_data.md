### Ways to Get Data in Spring MVC Forms

In Spring MVC, there are two main ways to capture form data:

#### 1. **`@ModelAttribute`**:
- Automatically binds form data to an object (like a `User` object) by matching input field names to the object's properties.
- Spring will check for matching form field names (like `fullName`, `email`, etc.) and automatically populate the fields of the object (e.g., `User`).

#### 2. **`@RequestParam`**:
- Used to capture individual form parameters by specifying the exact parameter name.
- Can be used alongside `@ModelAttribute` to capture any additional data not tied to the object, like an `age` field in this case.
- Explicitly captures individual form fields (like `age` in this case) that aren't part of the object.

---

### Example Controller:

```java
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(Model m) {
        return "home";
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    // Using @ModelAttribute to bind form data to the User object
    // and @RequestParam to bind additional form fields like 'age'
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user, @RequestParam("age") int age) {
        System.out.println(user);  // Will automatically bind 'fullName', 'email', 'password'
        System.out.println("Age = " + age);  // Captures 'age' separately using @RequestParam
        return "register";
    }
}
```

### JSP Form Example:

```html
<form action="createUser" method="post">
    <div class="mb-3">
        <label class="form-label">Full Name</label>
        <input type="text" class="form-control" name="fullName">
    </div>
    <div class="mb-3">
        <label class="form-label">Age</label>
        <input type="number" class="form-control" name="age">
    </div>
    <div class="mb-3">
        <label class="form-label">Email Address</label>
        <input type="email" class="form-control" name="email">
    </div>
    <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="password" class="form-control" name="password">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
```

---
### User Class:

```java
public class User {
    private String fullName;
    private String email;
    private String password;

    // Getters and setters
    // To string
}
```