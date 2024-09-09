package com.servlets;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("em");
        String password=req.getParameter("ps");

        RequestDispatcher rd;

        resp.setContentType("text/html");
        

        if("demo@email.com".equals(email) && "demo".equals(password)) {
            /* rd = req.getRequestDispatcher("/servlet2");
            rd.forward(req,resp); */
            resp.sendRedirect("servlet2");  // Redirect to the second servlet

        }
        else{
            resp.getWriter().println("<h1>Invalid email & password</h1>");
            rd = req.getRequestDispatcher("/index.html");
            rd.include(req, resp);  
        }
}
}
