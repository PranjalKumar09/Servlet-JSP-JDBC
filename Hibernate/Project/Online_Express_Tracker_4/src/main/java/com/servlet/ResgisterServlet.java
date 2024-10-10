package com.servlet;


import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/userRegister")
public class ResgisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String FullName = req.getParameter("FullName");
        String Email = req.getParameter("Email");
        String Password = req.getParameter("Password");
        String About = req.getParameter("About");

        User user = new User(FullName, Email, Password, About);

//        System.out.println(user);

        UserDao dao = new UserDao(HibernateUtil.getSessionFactory());
        boolean flag = dao.saveUser(user);

        HttpSession session = req.getSession();

        if (flag) {
            session.setAttribute("msg", "Register Success");
//            System.out.println("Register Success");
        }
        else {
            session.setAttribute("msg", "Unable to Register");
            System.out.println("Register Fail");
        }
        resp.sendRedirect("register.jsp");

    }
}
