package com.pranjal.servlet;

import java.io.IOException;

import com.pranjal.conn.HibernateUtil;
import com.pranjal.dao.EmpDao;
import com.pranjal.entity.Emp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String department = req.getParameter("department");
        String salary = req.getParameter("salary");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Emp emp = new Emp(name, department, salary, email, password);

        EmpDao dao = new EmpDao(HibernateUtil.getSessionFactory());

        HttpSession session = req.getSession();
        

        if (dao.saveEmp(emp)){
            session.setAttribute("msg","Emp registered successfully");
            System.out.println("Data Inserted Successfully");}
            else {
                session.setAttribute("msg","Emp falied to register");
                System.out.println("Something Failed");}
     resp.sendRedirect("index.jsp");
    }

    
}
