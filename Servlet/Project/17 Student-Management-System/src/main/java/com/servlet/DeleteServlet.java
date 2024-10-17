package com.servlet;

import java.io.IOException;

import com.conn.DBConnect;
import com.dao.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        StudentDAO dao = new StudentDAO(DBConnect.getConn());
        boolean isDeleted = dao.deleteStudent(id);
        HttpSession session = req.getSession();

        if (isDeleted) {
            session.setAttribute("succMsg", "Student Details Deleted Succesfully");
            resp.sendRedirect("index.jsp");
            // System.out.println("Data inserted");
    }
        else{
            // System.out.println("Something wrong on server");
            session.setAttribute("errorMsg", "Failed to Delete student details");
            resp.sendRedirect("index.jsp");
        }
    }


}
