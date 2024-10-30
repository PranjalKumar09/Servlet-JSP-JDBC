###  PathVariable, Static Resources, and File Upload

---

#### 1. **@PathVariable**
- `@PathVariable` maps URL segments directly to method parameters, enabling dynamic URLs.
- **Example**: Retrieve user information using dynamic URL parts:
  ```java
  @RequestMapping("/user/{id}")
  public String demo(@PathVariable("id") int id) {
      return "page";
  }
  
  @RequestMapping("/user/{id}/{name}")
  public String demo(@PathVariable("id") int id, @PathVariable("name") String name) {
      System.out.println("ID: " + id + ", Name: " + name);
      return "home";
  }
  ```
  - **Note**: The `{id}` and `{name}` segments in the URL are bound to `int id` and `String name` parameters, allowing flexible path-based requests.

#### 2. **Static Resource Setup**
- Store static resources such as CSS, JS, and images in a dedicated `resources` folder under `WEB-INF/`, with specific subdirectories:
  - `WEB-INF/resources/img` for images
  - `WEB-INF/resources/css` for stylesheets
  - `WEB-INF/resources/js` for scripts
- **Spring MVC Configuration** to serve static resources:
  ```xml
  <mvc:resources mapping="/resources/**" location="/WEB-INF/resources/"/>
  ```
  - This mapping allows you to access resources through `/resources/` in your application.

- **Dependency Setup for JSTL** (required to use JSP tag libraries):
  ```xml
  <dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>3.0.2</version>
  </dependency>
  <dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
    <version>3.0.1</version>
  </dependency>
  ```
  - **CSS Linking Example in JSP**:
    ```jsp
    <head>
      <title>Page Title</title>
      <%@ include file="../resources/css/css.jsp" %>
      <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
    </head>
    ```
  - **Like Image can access as**:
    ```jsp
    
    <img src="resources/img/img2.jpg" alt="Relative Path Test 2" width="300" height="auto">
    <img src="resources/img/notes1.webp" alt="Relative Path Test 2" width="300" height="auto">

    <img src="<c:url value='/resources/img/img2.jpg' />" alt="JSP c:url Test 2" width="300" height="auto">
    <img src="<c:url value='/resources/img/notes1.webp' />" alt="JSP c:url Test 1" width="300" height="auto">
    ```


#### 3. **File Upload Configuration**
- **Multipart Resolver Configuration** (`spring_servlet.xml`):
  - Enables Spring to handle multipart file uploads.
  ```xml
  <bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
  ```

- **Servlet Configuration in `web.xml`**:
  - Configure file size limits to control upload constraints.
  ```xml
  <servlet>
    <servlet-name>spring</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
    <multipart-config>
      <max-file-size>2097152</max-file-size> <!-- 2MB max file size -->
      <max-request-size>4194304</max-request-size> <!-- 4MB max request size -->
      <file-size-threshold>0</file-size-threshold>
    </multipart-config>
  </servlet>
  ```

- **File Upload Form** (`file_upload.jsp`):
  - Ensure the form has `enctype="multipart/form-data"` for file uploads.
  ```jsp
  <form action="fileupload" enctype="multipart/form-data" method="post">
      <input type="file" name="img" class="form-control">
      <button class="btn btn-primary">Upload</button>
  </form>
  ```

#### 4. **Controller Logic for Handling File Uploads**
- In the controller, use `MultipartFile` to access file properties and save the file to the server:
  ```java
  @RequestMapping(path = "/fileupload", method = RequestMethod.POST)
  public String fileUpload(@RequestParam("img") MultipartFile file, Model model, HttpServletRequest request) throws IOException {
      if (!file.isEmpty()) {
          String path = request.getServletContext().getRealPath("/") + "WEB-INF/resources/img/" + file.getOriginalFilename();
          try (FileOutputStream fos = new FileOutputStream(path)) {
              fos.write(file.getBytes());
          }
          model.addAttribute("imgName", file.getOriginalFilename());
      }
      return "file_success";
  }
  ```
  - **Important**: Always check if `file.isEmpty()` to avoid processing empty uploads.
  - Retrieve various file properties such as `file.getName()`, `file.getOriginalFilename()`, and `file.getSize()` for validation or logging.

#### 5. **Displaying Uploaded Files (`file_success.jsp`)**
- Use JSTL tags to display the uploaded image dynamically:
  ```jsp
  <html>
  <head>
      <title>File Upload Success</title>
      <%@ include file="../resources/css/css.jsp" %>
  </head>
  <body>
      <div class="text-center">
          <h1>File Upload Successful</h1>
          <img src="<c:url value='/resources/img/${imgName}' />">
      </div>
  </body>
  </html>
  ```
  - **Note**: Even though images are saved outside the project structure (e.g., in the Tomcat folder), `c:url` allows referencing them directly if they are in `WEB-INF/resources/`.
