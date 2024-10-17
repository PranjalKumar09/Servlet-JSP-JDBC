package org1.hibernate2;

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

        // Create a session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Create CriteriaBuilder and CriteriaQuery
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Student2> criteriaQuery = criteriaBuilder.createQuery(Student2.class);

            // Define the root of the query
            Root<Student2> root = criteriaQuery.from(Student2.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), 1));

            // Execute the query
            List<Student2> results = session.createQuery(criteriaQuery).getResultList();

            // Check if results are found and print
            if (!results.isEmpty()) {
                Student2 student = results.get(0);
                System.out.println(student.toString());
            } else {
                System.out.println("No student found with ID 1.");
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the session
            session.close();
        }
    }
}
