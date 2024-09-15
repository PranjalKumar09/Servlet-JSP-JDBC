package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/profile")
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        
        if (cookies==null )
            resp.sendRedirect("index.html");
        else
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            out.println("<h1>Welcome!</h1>");
            out.println("<p>Email: " + cookies[0].getValue() + "</p>");
            out.println("<p>Password: " + cookies[1].getValue() + "</p>");
            out.print("<a href='logout'>Log out</a>");


    }

}
