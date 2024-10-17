package com.user.servlet;

import java.io.IOException;

import com.dao.AppointmentDAO;
import com.db.DBConnect;
import com.entity.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int doctor_id = Integer.parseInt(req.getParameter("doctor_id"));
        String fullName = req.getParameter("fullName");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String appoint_date = req.getParameter("appoint_date");
        String phno = req.getParameter("phno");
        String disease = req.getParameter("disease");
        String address = req.getParameter("address");
        String status = "Pending";
        String email = req.getParameter("email");

        AppointmentDAO dao = new AppointmentDAO(DBConnect.getConn());
        Appointment ap = new Appointment(email, user_id, doctor_id, fullName, gender, age, appoint_date, phno, disease, address, status);

        HttpSession session = req.getSession();
        
        if (dao.addAppointment(ap)){
            session.setAttribute("succMsg" , "Appointment added successfully!");
            resp.sendRedirect("user_appointment.jsp");
        }
        else{
            session.setAttribute("errorMsg", "Appointment not added");
            resp.sendRedirect("user_appointment.jsp");
        }
    }
    

}