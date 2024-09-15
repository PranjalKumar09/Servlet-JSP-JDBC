package com.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

    @WebServlet("/servlet2")
    public class Servlet2 extends HttpServlet{

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int fn = Integer.parseInt(req.getParameter("fn"));
            int sn = Integer.parseInt(req.getParameter("sn"));

            int sum = (int)req.getAttribute("sum");

            int mul = fn*sn;

            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Addition: " + sum + "</h1>");
            resp.getWriter().println("<h2>Multiplication: " + mul + "</h2");

            

        }
        
    }
