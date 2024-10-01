package com.admin.servlet;

import java.io.IOException;

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addDoctor")
public class AddDoctor extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String fullName = req.getParameter("fullName");
            String dob = req.getParameter("dob");
            String qualification = req.getParameter("qualification");
            String specialist = req.getParameter("specialist");
            String email = req.getParameter("email");
            String mobNo = req.getParameter("mobNo");
            String password = req.getParameter("password");

            Doctor d = new Doctor(fullName, dob, qualification, specialist, email, mobNo, password);
            DoctorDao dao = new DoctorDao(DBConnect.getConn());

            HttpSession session = req.getSession();

            if (dao.registerDoctor(d)){
                session.setAttribute("succMsg", "Doctor Add Successfully");
                resp.sendRedirect("admin/doctor.jsp");
            }
            else {
                session.setAttribute("errorMsg", "Failed to Add Doctor");
                resp.sendRedirect("admin/doctor.jsp");
            }


            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
