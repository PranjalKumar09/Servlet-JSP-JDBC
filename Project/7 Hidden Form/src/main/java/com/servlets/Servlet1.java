package com.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("nm");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<form action='servlet2'><input type='hidden'  value='"+name+"' name='user'><br><button type='submit' >Serdsfcvlet2</button></form>"); // input type can be done "text" to check
        

    }
    

}
