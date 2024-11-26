
### Spring Boot Security: Enhanced User Account Management with Account Lock Feature on Failed Login Attempts

#### Entity Layer

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String email;
    private String mobileNumber;
    private String password;
    private String role;
    private boolean enabled;
    private String verficationCode;

    // New fields for account locking mechanism
    private boolean isAccountNotLocked;
    private int failedAttempt;
    private Date lockedTime;
}
```

- **Fields Explanation**:
  - `isAccountNotLocked`: Indicates if the account is locked due to too many failed login attempts.
  - `failedAttempt`: Keeps count of the failed login attempts.
  - `lockedTime`: Records the time when the account was locked, used to determine when the account can be unlocked.

#### Repository Layer

```java
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByVerficationCode(String verificationCode);

    @Query("update User u set u.failedAttempt=?1 where u.email=?2")
    @Modifying
    void updateFailedAttempt(int attempt, String email);
}
```

- **Methods**:
  - `updateFailedAttempt(int attempt, String email)`: Updates the number of failed attempts for a specific user.

#### Service Layer

```java
public interface UserService {
    User saveUser(User user, String url);
    void removeSessionMessage();
    void sendEmail(User user, String path);
    boolean verifyAccount(String verficationCode);

    void increaseFailedAttempt(User user);
    void resetAttempt(String email);
    void lock(User user);
    boolean unlockAccountTimeExpired(User user);
}
```

#### Service Implementation

```java
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JavaMailSender mailSender;

    // Lock duration and maximum attempt constants
    private static final long LOCK_DURATION_TIME = 60 * 60 * 1000; // 1 hour
    public static final long MAX_ATTEMPTS = 3;

    @Override
    public User saveUser(User user, String url) {
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_USER");

        user.setVerficationCode(UUID.randomUUID().toString());

        // Initialize user lock and attempt fields
        user.setAccountNotLocked(true);
        user.setFailedAttempt(0);
        user.setLockedTime(null);

        User newUser = userRepo.save(user);

        if (newUser != null) {
            sendEmail(newUser, url);
        }

        return newUser;
    }

    @Override
    public void increaseFailedAttempt(User user) {
        int attempt = user.getFailedAttempt() + 1;
        userRepo.updateFailedAttempt(attempt, user.getEmail());
    }

    @Override
    public void resetAttempt(String email) {
        userRepo.updateFailedAttempt(0, email);
    }

    @Override
    public void lock(User user) {
        user.setAccountNotLocked(false);
        user.setLockedTime(new Date());
        userRepo.save(user);
    }

    @Override
    public boolean unlockAccountTimeExpired(User user) {
        if (user.getLockedTime() == null) return true;

        long lockTime = user.getLockedTime().getTime();
        long currentTime = System.currentTimeMillis();

        if (lockTime + LOCK_DURATION_TIME < currentTime) {
            user.setLockedTime(null);
            user.setAccountNotLocked(true);
            user.setFailedAttempt(0);
            userRepo.save(user);
            return true;
        }

        return false;
    }
}
```

- **Account Lock and Unlock Logic**:
  - `increaseFailedAttempt(User user)`: Increments the failed attempt count on each failed login.
  - `resetAttempt(String email)`: Resets failed attempts upon successful login.
  - `lock(User user)`: Locks the account by setting `isAccountNotLocked` to `false` and records the `lockedTime`.
  - `unlockAccountTimeExpired(User user)`: Checks if the lock duration has expired and resets lock status if needed.

#### Security Configuration

```java
 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for development, consider enabling for production
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/**").hasRole("USER")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/**").permitAll()  // Allow all other requests
        )
        .formLogin(form -> form
            .loginPage("/login")  // Custom login page
            .loginProcessingUrl("/userLogin")  // URL to submit login form
            .defaultSuccessUrl("/user/profile", true)  // Redirect on successful login
            .failureHandler(customFailureHandler)  // Custom failure handler
            .successHandler(successHandler)  // Custom success handler
            .permitAll()
        );

    return http.build();
 }
```

#### Custom Authentication Handlers

**Success Handler**:

```java
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    private final UserServiceImpl userServiceImpl;

    public SuccessHandler(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUser customUser = (CustomUser)authentication.getPrincipal();
        User user = customUser.getUser();

        if (user != null) {
            userServiceImpl.resetAttempt(user.getEmail());
        }

        if (AuthorityUtils.authorityListToSet(authentication.getAuthorities()).contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin/profile");
        } else {
            response.sendRedirect("/user/profile");
        }
    }
}
```

**Failure Handler**:

```java
@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String email = request.getParameter("username");
        User user = userRepo.findByEmail(email);

        if (user != null && user.isEnabled()) {
            if (user.isAccountNotLocked()) {
                if (user.getFailedAttempt() < UserServiceImpl.MAX_ATTEMPTS - 1) {
                    userService.increaseFailedAttempt(user);
                } else {
                    userService.lock(user);
                    exception = new LockedException("Your account is locked due to multiple failed attempts.");
                }
            } else {
                if (userService.unlockAccountTimeExpired(user)) {
                    exception = new LockedException("Account is unlocked! Please try logging in.");
                } else {
                    exception = new LockedException("Account is locked! Please try again after the lock duration expires.");
                }
            }
        } else if (!user.isEnabled()) {
            exception = new LockedException("Account is inactive. Please verify your account.");
        }

        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}
```

#### Custom User Details Implementation

```java
public class CustomUser implements UserDetails {
    private User user;

    public CustomUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
```

#### Configuration for Mail and Circular Reference
properties
```java
# Email configurations
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=mail@gmail.com
spring.mail.password=password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Allow circular references in Spring (if needed)
spring.main.allow-circular-references=true
```

**Important Notes**:
- **Circular References**: `spring.main.allow-circular-references=true` is necessary if Spring dependencies create circular references.
- **Transactional Annotation**: The `@Transactional` annotation in `UserServiceImpl` ensures data integrity, especially when handling critical operations like account locking.

This structure will allow for a secure user authentication process with account lockout protection, improving security and usability by managing failed login attempts effectively.