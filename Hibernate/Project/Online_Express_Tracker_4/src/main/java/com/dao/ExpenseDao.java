package com.dao;

import com.entity.Expense;
import com.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDao {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    public ExpenseDao(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    public boolean saveExpense(Expense expense) {
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(expense);
            transaction.commit();
            flag = true;
        }
        catch (Exception e) {
            transaction.rollback();
        }
        return flag;
    }
    public List<Expense> getAllExpenseByUser(User user) {
        List<Expense> expenses = new ArrayList<>();
        try{
            session = sessionFactory.openSession();
            Query<Expense> query = session.createQuery("from Expense where user = :user");
            query.setParameter("user", user);
            expenses = query.getResultList();
            session.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  expenses;
    }
    public Expense getExpenseById(int id) {
        Expense expense = null;
        session = sessionFactory.openSession();
        expense = session.get(Expense.class, id);
        session.close();
        return expense;
    }
    public boolean updateExpense(Expense expense) {
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(expense);
            transaction.commit();
            session.close();
            flag = true;
        }
        catch (Exception e) {
            transaction.rollback();
        }
        return flag;
    }
    public boolean deleteExpense(int id) {
        boolean flag = false;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Expense expense = session.get(Expense.class, id);
            session.remove(expense);
            transaction.commit();
            session.close();
            flag = true;
        }
        catch (Exception e) {
            transaction.rollback();
        }
        return flag;
    }



}
