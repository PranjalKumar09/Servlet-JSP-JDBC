package com.pranjal.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.pranjal.entity.Emp;


public class EmpDao {
    private SessionFactory factory;
    
    public EmpDao(SessionFactory factory) {
        this.factory = factory;
    }
public boolean saveEmp(Emp emp) {
    boolean f = false;

    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    try {
        session.persist(emp); // Persist the employee object
        
        tx.commit(); // Commit the transaction
        f = true;
    } catch (Exception e) {
        if (tx != null) 
            tx.rollback(); // Rollback in case of an error
        
    } finally {
        session.close(); // Close the session in the finally block
    }

    return f; // Return the success status
}

}
