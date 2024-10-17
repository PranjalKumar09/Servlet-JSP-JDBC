package com.admin.servlet;

import java.io.IOException;

import com.dao.DoctorDao;
import com.db.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteDoctor")
public class DeleteDoctor extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {int id = Integer.parseInt(req.getParameter("id"));
            DoctorDao dao = new DoctorDao(DBConnect.getConn());

            HttpSession session = req.getSession();

            if (dao.deleteDoctor(id)){
                session.setAttribute("succMsg", "Doctor Delted Successfully");
                resp.sendRedirect("admin/view_doctor.jsp");
            }
            else {
                session.setAttribute("errorMsg", "Failed to Delete Doctor");
                resp.sendRedirect("admin/view_doctor.jsp");
            }


            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    

}
