package com.user.servlet;

import java.io.IOException;

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class UserRegister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fullName = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User u = new User(fullName, email, password);

            UserDao dao =  new  UserDao(DBConnect.getConn());

            HttpSession session = req.getSession();
            

            // HttpServlet session = req.getSession();
            boolean f = dao.Register(u);
            if (f) {
                session.setAttribute("succMsg", "Registered Successfully");
                resp.sendRedirect("sginup.jsp");
                // System.out.println("Register Successfully");
            } else {
                session.setAttribute("errorMgs", "Something wrong on Server");
                resp.sendRedirect("sginup.jsp");
                // System.out.println("Not Registered , Failed!!!");
            }

            
        } catch (Exception e) {
            
        }

    }
    

}
