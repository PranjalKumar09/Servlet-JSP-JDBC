package com.pranjal;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App
{
    public static void main( String[] args )
    {
        

        SessionFactory facotry = HibernateUtil.getSessionFactory();
        Session session = facotry.openSession();

        /* Student st = new Student();
        st.setId(1);
        st.setName("John Doe");
        st.setEmail("johndoe@example.com");
        st.setAddress("123 Main St"); */

        Employee emp = new Employee();
        emp.setEmpName("Ranjni Gandha");
        emp.setSalary(50000.0);
        emp.setJoiningDate(new Date());
        emp.setEmail("ranjig@example.com");
        emp.setStatus(true);
        emp.setToken("234erwr313bgh");
        
        Transaction tx = session.beginTransaction();
        session.persist(emp);
        // session.persist(st);

        tx.commit();
        session.close();
    }   
}
