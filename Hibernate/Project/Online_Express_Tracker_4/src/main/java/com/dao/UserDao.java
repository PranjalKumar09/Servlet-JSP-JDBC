package com.dao;

import com.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public UserDao(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }
    public boolean saveUser(User user) {
        boolean flag = false;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return flag;
    }

    public User login(String Email, String Password) {
        User user = null;

        session = sessionFactory.openSession();
        Query<User> query = session.createQuery("from User where  Email = :Email and Password = :Password", User.class);

        query.setParameter("Email", Email);
        query.setParameter("Password", Password);

        user = (User) query.uniqueResult();

        return user;


    }
}

