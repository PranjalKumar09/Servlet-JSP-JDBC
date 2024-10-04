package com.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        /* Student st = new Student();
        st.setName("First");
        st.setAddress("India");
        st.setCollageName("ABC");
        st.setEmail("abc@gmail.com");
        
        Student st2 = new Student();
        st2.setName("Second");
        st2.setAddress("USA");
        st2.setCollageName("DFG");
        st2.setEmail("dfg@gmail.com");

        Transaction tx = session.beginTransaction();
        
        session.persist(st);
        session.persist(st2);

        tx.commit();

        System.out.println("Student saved successfully");

         */


       /*  // read data
        List<Student> list = (List<Student>) session.createQuery("from Student", Student.class).list();

        list.forEach(e -> System.out.println(e));
         */

         /* // get by Id

         Student st = session.get(Student.class, 1);
         System.out.println(st);
         */

       /*  //  update data 
        Student st = session.get(Student.class, 1);
        st.setName("Update Name");
        st.setAddress("India UPdated");
        st.setEmail("pranjalkumar@gmail.com");
        st.setCollageName("UK university");

        Transaction tx = (Transaction) session.beginTransaction();
        session.merge(st);
        tx.commit(); */
Student st = session.get(Student.class, 1);
Transaction tx = session.beginTransaction();
session.remove(st);



System.out.println("Data Delete successfull");
        session.close();
        sf.close();
        
    }


}

