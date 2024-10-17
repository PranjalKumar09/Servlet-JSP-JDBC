package com.servlet;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class Download extends HttpServlet {

    public static int BUFFER_SIZE = 1024 * 1000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("fn");
        String path = getServletContext().getRealPath("") + "imgs" + File.separator + filename;

        File file = new File(path);
        OutputStream os = null;
        FileInputStream fis = null;

        try {
            // Set response headers
            resp.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getName()));
            resp.setContentType("application/octet-stream");

            if (file.exists()) {
                os = resp.getOutputStream();
                fis = new FileInputStream(file);
                byte[] bf = new byte[BUFFER_SIZE];
                int byteRead;

                // Corrected the assignment in the while loop
                while ((byteRead = fis.read(bf)) != -1) {
                    os.write(bf, 0, byteRead);
                }

                // Flush output stream after writing
                os.flush();
            } else {
                resp.setContentType("text/html");
                resp.getWriter().print("<h4>Image not found: " + filename + "</h4>");
                System.out.println("File not found: " + filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Properly close the resources in the finally block
            if (fis != null)
                fis.close();
            if (os != null)
                os.close();
        }
    }
}
