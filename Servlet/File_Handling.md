# File Handling in JDBC and JSP (Uploading and Downloading)

---

## 1. Uploading Files Using JSP and JDBC

### HTML Form for Uploading Files
The form uses the `multipart/form-data` encoding type, required for file uploads.

```html
<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" class="form-control" name="files" required>
    <input type="text" name="remark" placeholder="Enter remark">
    <button type="submit" class="btn btn-primary">Upload</button>
</form>
```


### Servlet for Uploading Files (UploadFile class)

Handles file upload and saves the file on the server’s directory. Also, saves file details (name, remark) into a database using JDBC.

- The @MultipartConfig annotation is required to handle file uploads.
- The file is saved to a directory defined in UPLOAD_DIRECTORY.
- After the file is uploaded, its information is saved in the database using JDBC.


``` java 
@WebServlet("/upload")
@MultipartConfig
public class UploadFile extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "imgs";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("files");
        String fileName = part.getSubmittedFileName();
        String remark = req.getParameter("remark");
        HttpSession session = req.getSession();

        if (fileName == null || fileName.isEmpty()) {
            session.setAttribute("msg", "No file uploaded.");
            resp.sendRedirect("index.jsp");
            return;
        }

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try (Connection conn = DBConnect.getConn()) {
            String sql = "INSERT INTO img_details (img_name, remark) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, fileName);
                ps.setString(2, remark);
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted == 1) {
                    String filePath = uploadPath + File.separator + fileName;
                    part.write(filePath);
                    session.setAttribute("msg", "File uploaded successfully: " + fileName);
                } else {
                    session.setAttribute("msg", "Failed to save file information.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "Error occurred.");
        }
        resp.sendRedirect("index.jsp");
    }
}
```

#### Important Steps in `doPost`:
**Retrieve File and Remark:**
   - `Part part = req.getPart("files")`: Gets the uploaded file part.
   - `String fileName = part.getSubmittedFileName()`: Retrieves the original file name.
   - `String remark = req.getParameter("remark")`: Retrieves additional remarks from the form.

**File Validation:**
   - Checks if `fileName` is null or empty.
   - If so, sets an error message in the session and redirects to `index.jsp`.

**Prepare Upload Directory:**
   - Constructs the upload path using `getServletContext().getRealPath("")`.
   - Checks if the directory exists, and creates it if it doesn't.

**File Writing:**
   - If the database insert is successful, writes the uploaded file to the server's file system.


# Downloading Files Using JSP and JDBC

## JSP Code for Displaying and Downloading Files

- **Functionality**: Displays different icons for various file types and provides a download link for each file.
- **Supported File Types**: `.pdf`, `.xls`, and images.
- **Code Snippet**:
    ```jsp
    <tr>
        <%
        String imgName = rs.getString("img_name");
        if (imgName != null && imgName.endsWith(".pdf")) { %>
            <th scope="row"><img src="imgs/pdf.png" width="200px" height="100px"></th>
            <td><a href="download?fn=<%= imgName %>" class="btn btn-primary btn-sm">Download</a></td>
        <% } else if (imgName != null && imgName.endsWith(".xls")) { %>
            <th scope="row"><img src="imgs/xlsx.png" width="200px" height="100px"></th>
            <td><a href="download?fn=<%= imgName %>" class="btn btn-primary btn-sm">Download</a></td>
        <% } else { %>
            <th scope="row"><img src="imgs/<%= imgName %>" width="200px" height="100px"></th>
            <td><a href="download?fn=<%= imgName %>" class="btn btn-primary btn-sm">Download</a></td>
        <% } %>
        <td><%= rs.getString("remark") %></td>
    </tr>
    ```

## Servlet for Downloading Files

- **Purpose**: Handles file download requests by reading the file from the server directory and writing it to the response output stream.
- **Headers**:
  - `Content-Disposition`: Triggers the browser's download prompt.
- **Error Handling**: Responds with a message if the file is not found.
- **Code Snippet**:
    ```java
    @WebServlet("/download")
    public class Download extends HttpServlet {
    
        public static int BUFFER_SIZE = 1024 * 1000;
    
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String filename = req.getParameter("fn");
            String path = getServletContext().getRealPath("") + "imgs" + File.separator + filename;
            File file = new File(path);
    
            if (file.exists()) {
                resp.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getName()));
                resp.setContentType("application/octet-stream");
    
                try (FileInputStream fis = new FileInputStream(file); OutputStream os = resp.getOutputStream()) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.flush();
                }
            } else {
                resp.setContentType("text/html");
                resp.getWriter().print("<h4>File not found: " + filename + "</h4>");
            }
        }
    }
    ```
