package com.servlet;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deleteExpense")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
        boolean flag = dao.deleteExpense(id);
        HttpSession session = req.getSession();

        if (flag)
            session.setAttribute("msg", "Expense Deleted Successful");
        else
            session.setAttribute("msg", "Expense Deletion Failed");
        resp.sendRedirect("user/view_expense.jsp");

    }
}