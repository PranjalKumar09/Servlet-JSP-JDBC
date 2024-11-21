### 1. **User Entity**:
Define fields for `enabled` and `verificationCode` to handle account verification status and code generation.

```java
@Entity
public class User {
    private boolean enabled;
    private String verificationCode;

    // Getters and setters
}
```

### 2. **Controller**:
Set up registration and verification endpoints to handle the verification process.

- **Register New User** (`/saveUser`): Generates a verification link, sends it by email, and returns success or failure messages.
- **Verify Account** (`/verify`): Verifies the account when the link is clicked.

```java
@Controller
public class UserController {
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session, HttpServletRequest request) {
        String baseUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
        // http://localhost:8080/saveUser -> http://localhost:8080
        User savedUser = userService.saveUser(user, baseUrl);

        if (savedUser != null) {
            session.setAttribute("msg", "Registered Successfully! Please check your email for verification.");
        } else {
            session.setAttribute("msg", "Failed to Register.");
        }
        return "redirect:/register";
    }

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("code") String code, Model model) {
        boolean isVerified = userService.verifyAccount(code);
        model.addAttribute("msg", isVerified ? "Account Verified" : "Account Verification Failed");
        return "message";
    }
}
```

### 3. **UserService**:
Implement methods to save the user, send the verification email, and handle account verification.
- In User Service like 
void sendEmail(User user, String path);
public boolean verifyAccount(String verficationCode);
```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JavaMailSender mailSender;

    public User saveUser(User user, String baseUrl) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setVerificationCode(UUID.randomUUID().toString());
        
        User newUser = userRepo.save(user);
        if (newUser != null) {
            sendVerificationEmail(newUser, baseUrl);
        }
        return newUser;
    }

    public void sendVerificationEmail(User user, String baseUrl) {
        String to = user.getEmail();
        String subject = "Account Verification";
        String verificationUrl = baseUrl + "/verify?code=" + user.getVerificationCode();
        String content = "Dear " + user.getFullName() + ",<br>"
                + "Please verify your registration by clicking the link below:<br>"
                + "<a href=\"" + verificationUrl + "\">Verify Account</a><br>"
                + "Thank you!";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("your_email@example.com", "Your Name");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyAccount(String verificationCode) {
        User user = userRepo.findByVerificationCode(verificationCode);
        if (user == null) {
            return false;
        }
        user.setEnabled(true);
        user.setVerificationCode(null);
        userRepo.save(user);
        return true;
    }
}
```
    VerifyAccount only called when clicked in email , also after saving , and updating just setted the verificationCode to null

### 4. **UserRepository**:
Create a query method to retrieve a user by their `verificationCode`.

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByVerificationCode(String verificationCode);
}
```

### 5. **Configuration Changes**:
Implement dynamic checking for the `enabled` field.

```java
@Override
public boolean isEnabled() {
    return user.isEnabled();
}
```

### 6. **Dependency for Email**:
Implement dynamic checking for the `enabled` field.

```java
@Override
public boolean isEnabled() {
    return user.isEnabled();
}
```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>