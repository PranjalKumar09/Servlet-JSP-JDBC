package com.pranjal.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
// import org.hibernate.Transaction;
// import org.hibernate.query.MutationQuery;
// import org.hibernate.query.Query;

import com.pranjal.conn.HibernateUtil;
import com.pranjal.entity.Student;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        /* Transaction tx = session.beginTransaction();

        // Persisting a couple of students
        Student s1 = new Student();
        s1.setName("Na1");
        s1.setAddress("Ad1");
        Student s2 = new Student();
        s2.setName("Na2");
        s2.setAddress("Ad2");

        session.persist(s1);
        session.persist(s2);
        tx.commit();

        // Starting a new transaction for querying
        tx = session.beginTransaction();

        // Fetch all students
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        for (Student student : students) {
            System.out.println(student);
        }

        // Fetch student by id
        Student studentById = session.get(Student.class, 1);
        System.out.println(studentById);

        // Fetch a single result by address
        Query<Student> queryByAddress = session.createQuery("from Student where address = 'Ad1'", Student.class);
        Student studentByAddress = queryByAddress.getSingleResult();
        System.out.println(studentByAddress);

        // Fetch using parameters
        Query<Student> paramQuery = session.createQuery("from Student where address = :ad and id = :id", Student.class);
        paramQuery.setParameter("id", 1);
        paramQuery.setParameter("ad", "Ad1");
        System.out.println(paramQuery.getSingleResult());

        // Persisting a new student
        Student s3 = new Student();
        s3.setName("Na3");
        s3.setAddress("Ad3");
        session.persist(s3);

        // Update student with id=2 using HQL query
        MutationQuery updateQuery = session.createMutationQuery("update Student set address = :newAddress where id = :id");
        updateQuery.setParameter("newAddress", "Updated Ad2");
        updateQuery.setParameter("id", 2);
        int updateResult = updateQuery.executeUpdate();
        System.out.println(updateResult + " student(s) updated via query.");

        // Update student by object
        Student studentToUpdate = session.get(Student.class, 3);
        if (studentToUpdate != null) {
            studentToUpdate.setAddress("Updated Ad3 by object");
            session.merge(studentToUpdate);
            System.out.println("Student updated by object: " + studentToUpdate.getName());
        } else {
            System.out.println("No student found with id=3.");
        }

        // Delete student with id=1 using HQL query
        MutationQuery deleteQuery = session.createMutationQuery("delete from Student where id = :id");
        deleteQuery.setParameter("id", 1);
        int deleteResult = deleteQuery.executeUpdate();
        System.out.println(deleteResult + " student(s) deleted by query.");

        // Delete student with id=3 by object
        Student studentToDelete = session.get(Student.class, 3);
        if (studentToDelete != null) {
            session.remove(studentToDelete);
            System.out.println("Student deleted by object: " + studentToDelete.getName());
        } else {
            System.out.println("No student found with id=3.");
        }
        tx.commit();
 */
        
        Student st = session.get(Student.class, 3);
        System.out.println(st);
        Student st2 = session.get(Student.class,4);
        System.out.println(st2);

        
        session.close();
    }
}


/* 
  be set into auto-commit mode.
Hibernate: select s1_0.id,s1_0.Salary,s1_0.address,s1_0.name from Student s1_0 where s1_0.id=?
Student [id=3, name=Charlie Brown, address=789 Oak Dr, Salary=29000]
Hibernate: select s1_0.id,s1_0.Salary,s1_0.address,s1_0.name from Student s1_0 where s1_0.id=?
Student [id=4, name=Dana White, address=135 Pine Ln, Salary=31000]

 */