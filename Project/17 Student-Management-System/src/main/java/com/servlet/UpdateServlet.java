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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,    IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String fullname = req.getParameter("fullname");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String qualification = req.getParameter("qualification");
        String email = req.getParameter("email");

        Student student = new Student(id, fullname, dob, address, qualification, email);

        StudentDAO studentDAO = new StudentDAO(DBConnect.getConn());
        
        HttpSession session = req.getSession();
        
        boolean isUpdated = studentDAO.updateStudent(student);

        if (isUpdated) {
            session.setAttribute("succMsg", "Student Details Updated Succesfully");
            resp.sendRedirect("index.jsp");
            // System.out.println("Data inserted");
    }
        else{
            // System.out.println("Something wrong on server");
            session.setAttribute("errorMsg", "Failed to Updated student details");
            resp.sendRedirect("index.jsp");
        }
    }

    
}
