package org.hibernate2;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Load the Spring application context from XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate_config.xml");

        // Get the SessionFactory bean from the Spring context
        SessionFactory sessionFactory = context.getBean("sessionFactory", SessionFactory.class);

        // Open a new session
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            Transaction transaction = session.beginTransaction();

            // Using CriteriaBuilder to create a CriteriaQuery
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Student2> criteriaQuery = criteriaBuilder.createQuery(Student2.class);
            Root<Student2> root = criteriaQuery.from(Student2.class);

            // Set the selection criteria (replace "name" with your actual field name)
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("John"), "Kumar"));

            // Execute the query and get results
            List<Student2> students = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();

            // Print results
            students.forEach(student -> System.out.println(student.getName())); // Adjust according to your Student2 class
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}