# Session Tracking

Session refers to a specific time interval for user interactions. **Session Tracking** is the mechanism for maintaining a user's state (data) across multiple requests in a stateless protocol like HTTP. This is also called **Session Management** in servlets.

HTTP protocol is stateless, so we use various techniques for session tracking.


```java
String name = req.getParameter("name");
PrintWriter out = resp.getWriter();
resp.setContentType("text/html");
out.println("Welcome to Servlet1 name=" + name);
out.print("<a href='servlet2'>Servlet2</a>");
```

## Session Tracking Techniques

1. **Cookies**
2. **Hidden Form Field**
3. **URL Rewriting**
4. **HttpSession**

Example: When navigating from Servlet1 to Servlet2 via a hyperlink, data (e.g., user name) from Servlet1 may not be available in Servlet2 because hyperlinks (like `<a href="servlet2">Servlet2</a>`) do not carry data.

### Why `doGet` and not `doPost`?

- **doGet**:  
  - Used for retrieving data or navigating between resources via URLs.
  - Common with hyperlinks, as they make GET requests.
  
- **doPost**:  
  - Used for submitting form data that modifies server state.
  - Better for handling form submissions but unsuitable for hyperlinks.

---

## Cookies

- **Cookies** are a session tracking technique that maintains state on the client-side.
- Defined as key-value pairs, cookies are stored in the user's browser and are part of the HTTP header.

``` java
Cookie[] cookies = req.getCookies();
if (cookies == null) {
    resp.sendRedirect("index.html");
}
ck[0].setMaxAge(0);
ck[1].setMaxAge(0);
```


### Characteristics of Cookies:
- **Size**: Limited to around 4KB.
- **Persistence**: Can be either session-based (deleted on browser close) or persistent (remain until expiry).
- **Visibility**: Accessible by client-side scripts (e.g., JavaScript).
- **Security**: Vulnerable to XSS; `HttpOnly` and `Secure` attributes add some protection.
- **Expiration**: Can be set to expire at a specific time, or session-based.

---

## URL Rewriting

URL rewriting is a technique where data is passed between servlets through URL parameters. This is useful when cookies are disabled, or when you don’t want to use HttpSession.

- Syntax: `https://localhost:8080/projectname?name1=value1&name2=value2`
- Typically uses the `doGet` method.

``` java
out.print("<a href='  ?username=" + name + "'>Go to Servlet 2</a>");
```
``` java
String username = req.getParameter("username");
```
---

## Hidden Form Field

Hidden form fields are used to maintain the state of the user in forms, without displaying the data to the user.

- Syntax: `<input type="hidden" name="param" value="value">`
- Can be used with both `doPost` and `doGet` methods, but `doPost` is preferred for better security.

---

## HttpSession

`HttpSession` is a server-side session tracking mechanism that creates a session ID for each user, allowing the server to maintain data across multiple requests from the same user.

- Methods:
  - `session.setAttribute()`: Store session data.
  - `session.getAttribute()`: Retrieve session data.
  - `session.removeAttribute()`: Remove session data.
  - `session.invalidate()`: End the session.

``` java
HttpSession session = req.getSession();
session.setAttribute("objname", "value");
session.invalidate();
```
---

## Cookies vs. HTTP Sessions

### Cookies
- **Storage**: Client-side (browser).
- **Size**: Limited to 4KB.
- **Persistence**: Session-based or persistent.
- **Visibility**: Accessible by client-side scripts.
- **Security**: Less secure, vulnerable to attacks like XSS.

### HTTP Sessions
- **Storage**: Server-side.
- **Size**: No strict limit (as data is on the server).
- **Persistence**: Managed by the server, not affected by browser closure.
- **Visibility**: Not visible to client-side scripts.
- **Security**: More secure than cookies.

---

## `doGet` vs. `doPost`

### doGet
- **Purpose**: Handles HTTP GET requests.
- **Data Size**: Limited by URL length.
- **Caching**: Cached by browsers and proxies.
- **Bookmarking**: URLs with GET parameters can be bookmarked.
- **Idempotency**: Should not alter server state (idempotent).
- **Use**: Retrieving or displaying data.

### doPost
- **Purpose**: Handles HTTP POST requests.
- **Data Size**: No size limit as data is sent in the request body.
- **Caching**: Not cached by default.
- **Bookmarking**: Cannot be bookmarked.
- **Idempotency**: May alter server state (non-idempotent).
- **Use**: Submitting forms or uploading files.


