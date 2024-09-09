package com.servlets;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("user_name");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String course=req.getParameter("course");
        String phone=req.getParameter("phone");

        resp.setContentType("text/html");
        resp.getWriter();
        resp.getWriter().println("<h1>Registration Successful!</h1>");
        resp.getWriter().println("<p>Name: " + name + "</p>");
        resp.getWriter().println("<p>Email: " + email + "</p>");
        resp.getWriter().println("<p>Password: " + password + "</p>");
        resp.getWriter().println("<p>Course: " + course + "</p>");
        resp.getWriter().println("<p>Name: " + name + "</p>");
        resp.getWriter().println("<p>Phone: " + phone + "</p>");
        
    }
}
