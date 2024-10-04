package com.pranjal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FetchApp {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session =  factory.openSession();
       
        Employee emp = session.get(Employee.class,1);
        Employee emp2 = session.get(Employee.class,1);
        System.out.println(emp);
        System.out.println(emp2);
    
    }
}
