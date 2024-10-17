    package com.servlet;

    import java.io.File;
    import java.io.IOException;
    import java.sql.Connection;
    import java.sql.PreparedStatement;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.MultipartConfig;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.Part;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;

    @WebServlet("/upload")
    @MultipartConfig
    public class UploadFile extends HttpServlet {

        private static final String UPLOAD_DIRECTORY = "imgs"; // Folder inside webapp to store uploaded images

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

            // Save the file to the server directory
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Create the directory if it does not exist
            }

            try {
                Connection conn = DBConnect.getConn();

                String sql = "INSERT INTO img_details (img_name, remark) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, fileName);
                    ps.setString(2, remark);

                    int rowsInserted = ps.executeUpdate();

                    if (rowsInserted == 1) {
                        String filePath = uploadPath + File.separator + fileName;
                        part.write(filePath); // Save the uploaded file

                        session.setAttribute("msg", "File uploaded successfully: " + fileName);
                    } else {
                        session.setAttribute("msg", "Database error: Failed to save file information.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    session.setAttribute("msg", "Database error occurred.");
                } finally {
                    conn.close(); // Close the database connection
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("msg", "Database driver error occurred.");
            }

            // Redirect to index.jsp with the message
            resp.sendRedirect("index.jsp");
        }
    }
