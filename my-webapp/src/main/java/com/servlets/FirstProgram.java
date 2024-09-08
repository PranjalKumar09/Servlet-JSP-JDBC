package com.servlets;

import jakarta.servlet.*;
import java.io.IOException;

public class FirstProgram implements Servlet {
    


    private ServletConfig config;

    // This method is invoked when the servlet is first created
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        System.out.println("Servlet initialized");
    }

    // This method handles each request from the client
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // Setting the response content type
        res.setContentType("text/html");

        // Writing the response
        res.getWriter().println("<h1>Hello from FirstProgram Servlet! 2</h1>");
        System.out.println("Servlet 2");
        System.out.println(getServletInfo());
        System.out.println(getServletInfo());
    }

    // This method returns the Servlet configuration
    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    // This method returns information about the Servlet
    @Override
    public String getServletInfo() {
        return "FirstProgram Servlet v1.0";
    }

    // This method is called when the servlet is destroyed
    @Override
    public void destroy() {
        System.out.println("Servlet is being destroyed");
    }
}
