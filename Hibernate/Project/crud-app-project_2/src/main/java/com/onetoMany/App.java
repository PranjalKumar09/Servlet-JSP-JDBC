package com.onetoMany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.project.HibernateUtil;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        
        Address add1 = new Address(103, "Permanent", "Delhi2");
        Address add2 = new Address(101, "Permanent", "Delhi2");


        List<Address> list  = new ArrayList<>();
        list.add(add1);
        list.add(add2);



        EmpDtls emp = new EmpDtls();
        emp.setId(1);
        emp.setName("John Doe");
        emp.setAddress(list);

        add1.setEmpDtls(emp);
        add2.setEmpDtls(emp);

        Session session =  factory.openSession();
        Transaction tx = session.beginTransaction();

        /* session.persist(add1);
        session.persist(add2);
        session.persist(emp); */
/* 
        EmpDtls empDtls = (EmpDtls) session.get(EmpDtls.class, 1);
        System.out.println("Emp Name= " + empDtls.getName());
        for (Address ad: empDtls.getAddress())
            System.out.println("Address type: "+ad.getAddressType() + " " + "  Address :"+ ad.getAddress());
         */

        Address ad = (Address) session.get(Address.class, 101 );
        System.out.println("Emp Name= " + ad.getEmpDtls().getName());
        System.out.println("Address type: "+ad.getAddressType() + " " + "  Address :"+ ad.getAddress());
        

        System.out.println("Done");
        tx.commit();

        factory.close();
    }
}
