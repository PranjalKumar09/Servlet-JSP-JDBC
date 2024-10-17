package org1.hibernate3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Student3DaoImpl implements Student3Dao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public int saveStudent(Student3 student) {
        Transaction transaction = null;
        int studentId = 0;

        try (Session session = sessionFactory.openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();

            // Persist the student object
            session.persist(student);

            // The ID is automatically generated after persist, use student.getId()
            studentId = student.getId();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return studentId;
    }


    @Override
    public Student3 getStudent(int id) {
        Student3 student = null;

        try (Session session = sessionFactory.openSession()) {
            // Fetch the student by ID
            student = session.get(Student3.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public List<Student3> getAllStudents() {
        List<Student3> students = null;

        try (Session session = sessionFactory.openSession()) {
            // Create a CriteriaBuilder and CriteriaQuery for fetching all students
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Student3> criteriaQuery = criteriaBuilder.createQuery(Student3.class);

            // Define the root of the query
            Root<Student3> root = criteriaQuery.from(Student3.class);
            criteriaQuery.select(root);

            // Execute the query
            students = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }


    @Override
    public int updateStudent(Student3 student) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();

            // Use merge() to update the student object
            session.merge(student);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;  // Return 0 if the update fails
        }

        return 1;  // Return 1 if the update is successful
    }

    @Override
    public int deleteStudent(Student3 student) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();

            // Use remove() to delete the student object
            session.remove(student);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;  // Return 0 if the delete fails
        }

        return 1;  // Return 1 if the delete is successful
    }


}
