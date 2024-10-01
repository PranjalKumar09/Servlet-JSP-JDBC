package com.admin.servlet;

import java.io.IOException;

import com.dao.SpecialistDao;
import com.db.DBConnect;
import com.entity.Specialist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String specName = req.getParameter("specName");
        Specialist specialist = new Specialist();
        specialist.setSpecialistName(specName);

        SpecialistDao specialistDao = new SpecialistDao(DBConnect.getConn());
        boolean result = specialistDao.addSpeaciallist(specName);

        HttpSession session = req.getSession();

        if (result) {
            session.setAttribute("succObj", "Specaililst Added");
            resp.sendRedirect("./admin/index.jsp");
        } else {
            session.setAttribute("errorMsg", "Could'nt able to set Specialist, Something wrong on server");

            resp.sendRedirect("admin_login.jsp");
        }

    }

}
