package com.doctor.servlet;

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

@WebServlet("/editDoctor")
public class EditDoctor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String fullName = req.getParameter("fullName");
            String dob = req.getParameter("dob");
            String qualification = req.getParameter("qualification");
            String specialist = req.getParameter("specialist");
            String email = req.getParameter("email");
            String mobNo = req.getParameter("mobNo");
            int id = Integer.parseInt(req.getParameter("id"));

            Doctor d = new Doctor(id, fullName, dob, qualification, specialist, email, mobNo, "");
            DoctorDao dao = new DoctorDao(DBConnect.getConn());

            HttpSession session = req.getSession();

            if (dao.editDoctor(d))
                session.setAttribute("succMsg", "Doctor Edited Successfully");
            else
                session.setAttribute("errorMsg", "Failed to Edit Doctor");
            
            resp.sendRedirect("doctor/edit_profile.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}