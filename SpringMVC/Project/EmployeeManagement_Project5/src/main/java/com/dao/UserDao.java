package com.dao;


import com.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    public int insertUser(User user) {
        int userId = 0;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(user);
            userId = user.getId();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
    public User loginUser(String email, String password) {
        Query<User> query = sessionFactory
                .openSession()
                .createQuery("from User where email = :email and password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.uniqueResult();
    }

}
