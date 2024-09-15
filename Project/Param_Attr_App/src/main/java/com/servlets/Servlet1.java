package com.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("fn");
        int fn = Integer.parseInt(name);
        String name2 = req.getParameter("sn");
        int sn = Integer.parseInt(name2);

        int sum = fn + sn;
        req.setAttribute("sum", sum);
        RequestDispatcher rd = req.getRequestDispatcher("/servlet2");
        rd.forward(req, resp);
    }


}
