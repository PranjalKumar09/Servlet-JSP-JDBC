package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;



public class SecondProgram extends GenericServlet{

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("Generic Servlet");
        res.setContentType("text/html");
        
        // Get the PrintWriter to write the response
        PrintWriter out = res.getWriter();
        
        // Write the current date and time to the response
        out.println("<html><body>");
        out.println("<h1>Date and Time: " + new Date().toString() + "</h1>");
        out.println("</body></html>");
        
        // Flush and close the PrintWriter
        out.flush();
        out.close();
    }

}
