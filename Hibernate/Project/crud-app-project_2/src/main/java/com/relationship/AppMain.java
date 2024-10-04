package com.relationship;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.project.HibernateUtil;

public class AppMain {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        

        Address ad = new Address();

        ad.setId(102);
        ad.setAddress("Bihar");


        EmpDtls e = new EmpDtls();
        e.setId(2);
        e.setName("Harshita Mathur");
        e.setAddress(ad);
        


        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        ad.setEmp(e);
        

        session.persist(ad);
        session.persist(e);

        EmpDtls emp = (EmpDtls) session.get(EmpDtls.class,1);
        System.out.println(emp.getName());
        System.out.println(emp.getAddress().getAddress());

        Address address = (Address) session.get(Address.class, 101);
        System.out.println(address.getEmp().getName());
        System.out.println(address.getAddress());

        tx.commit();
        factory.close(); // Close the session factory once all operations are done.
    
        // System.out.println("Insert Success");
    }
}
