package com.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet2")
public class SecondServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("em");

        resp.setContentType("text/html");
        resp.getWriter().println(email+"<h1>Welcome to Home Page</h1>");
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // just for Response purpose
        String email = req.getParameter("em");

        resp.setContentType("text/html");
        resp.getWriter().println(email+"<h1>Welcome to Home Page</h1>");
        
    }
    

}
