package com.servlet;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.Expense;
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/updateExpense")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        LocalTime time = LocalTime.parse(req.getParameter("time"));
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        User user = (User) req.getSession().getAttribute("user");

        Expense expense = new Expense(title, date, time, description, price, user);
        expense.setId(id);
        ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
        boolean flag  = dao.updateExpense(expense);

        HttpSession session = req.getSession();

        if (flag)
            session.setAttribute("msg", "Expense Updated Success");
//            System.out.println("Register Success");

        else
            session.setAttribute("msg", "Expense Unable to Update");

        resp.sendRedirect("user/view_expense.jsp");
    }
}
