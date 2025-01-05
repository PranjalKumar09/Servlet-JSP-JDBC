### Email Sending in REST API

#### 1. **EmailRequest Model**
Create a class to define the structure of the email request:

```java
package com.pranjal.model;

public class EmailRequest {
    private String title;
    private String subject;
    private String body;
    private String recipientEmail;

    // Getters and setters
}
```

#### 2. **EmailService Interface**
Define the service interface to handle email sending:

```java
public interface EmailService {
    void sendEmail(EmailRequest emailRequest) throws Exception, UnsupportedEncodingException;
}
```

#### 3. **EmailService Implementation**
Implement the email sending logic using `JavaMailSender`:

```java
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailRequest emailRequest) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom("coderkumarshukla@gmail.com", emailRequest.getTitle());
        helper.setTo(emailRequest.getRecipientEmail());
        helper.setSubject(emailRequest.getSubject());
        helper.setText(emailRequest.getBody(), true);

        mailSender.send(mimeMessage);
    }
}
```

#### 4. **Controller to Send Email**
Create a controller to handle email requests from the client:

```java
@Autowired
private EmailService emailService;

@PostMapping("/send-mail")
public ResponseEntity<?> sendMail(@RequestBody EmailRequest emailRequest) {
    try {
        emailService.sendEmail(emailRequest);
        return new ResponseEntity<>("Email Sent Successfully", HttpStatus.OK);
    } catch (MessagingException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>("Email Send Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>("Email Send Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

#### 5. **Dependencies**
Ensure the correct dependency is included in `pom.xml` for email functionality:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

#### 6. **Configuration in `application.properties`**
Ensure the correct properties for email configuration:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

#### 7. **Postman Request**
To send an email via Postman, use a JSON request with the following structure:

```json
{
    "title": "Email Title",
    "subject": "Email Subject",
    "body": "Email Body",
    "recipientEmail": "recipient@example.com"
}
```

#### 8. **Error Handling**
Always use try-catch blocks for exception handling to capture any issues during the email sending process.





---
---
---



### **Key Differences**
| **Aspect**           | **Request Parameters**                | **Request Body**                     |
|-----------------------|---------------------------------------|---------------------------------------|
| **Location**          | In the URL (query string or path)    | In the body of the HTTP request       |
| **Visibility**        | Visible in the URL                   | Not visible in the URL               |
| **Use Case**          | Filtering, identifying resources     | Sending structured or large data     |
| **HTTP Methods**      | Mostly GET (sometimes DELETE)        | POST, PUT, PATCH, DELETE             |
| **Data Size**         | Limited by URL length                | Can handle large payloads            |



