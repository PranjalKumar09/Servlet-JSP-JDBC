package com.servlet;

import java.io.IOException;

import com.conn.DBConnect;
import com.dao.StudentDAO;
import com.entity.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String qualification = req.getParameter("qualification");
        String email = req.getParameter("email");

        Student s = new Student(name, dob, address, qualification, email);
        System.out.println(s);

        StudentDAO dao = new StudentDAO(DBConnect.getConn());

        boolean f =  dao.addStudent(s);

        HttpSession session = req.getSession();

        if (f) {
            session.setAttribute("succMsg", "Student Details submit Succesfully");
            resp.sendRedirect("add_student.jsp");
            // System.out.println("Data inserted");
    }
        else{
            // System.out.println("Something wrong on server");
            session.setAttribute("errorMsg", "Failed to submit student details");
            resp.sendRedirect("add_student.jsp");
        }



    }
    
}
