package com.ManytoMany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.project.HibernateUtil;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Emp e1 = new Emp();
        e1.setId(101);
        e1.setName("Rakesh");
    
        Emp e2 = new Emp();
        e2.setId(102);
        e2.setName("Suresh");

        Address ad1 = new Address();
        ad1.setId(1);
        ad1.setAddressName("Delhi");

        Address ad2 = new Address();
        ad2.setId(2);
        ad2.setAddressName("Mumbai");

        List<Address> list1 = new ArrayList<>();
        list1.add(ad1);
        list1.add(ad2);

        List<Emp> list2 = new ArrayList<>();
        list2.add(e1);
        list2.add(e2);

        e1.setAddresses(list1);
        ad1.setEmp(list2);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(e1);
            session.persist(e2);
            session.persist(ad1);
            session.persist(ad2);
            System.out.println("Register Success");
            tx.commit();
            // System.out.println("Employee saved successfully with id: " + emp.getId());
        }
    }
}