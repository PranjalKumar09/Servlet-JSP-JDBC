package com.pranjal.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.pranjal.conn.HibernateUtil;
import com.pranjal.entity.Student;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CriteriaObject {

    private static void print(List<Student> list) {
        for (Student student : list) 
            System.out.println(student);
        System.out.println("==".repeat(20));
    }

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        
        // Using HQL to fetch all students
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        print(students);

        // Using Criteria API to fetch all students
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(root);
        
        List<Student> student_list = session.createQuery(criteriaQuery).getResultList();
        print(student_list);
        

         criteriaQuery = criteriaBuilder.createQuery(Student.class); // Reset the criteria query
    root = criteriaQuery.from(Student.class); 

        // Fetching students with salary greater than 31,000
        Predicate salaryPredicate = criteriaBuilder.gt(root.get("Salary"), 31000); 
        criteriaQuery.select(root).where(salaryPredicate); // Apply the predicate to the query

        List<Student> student_having_salary_more_than_31000 = session.createQuery(criteriaQuery).getResultList();
        print(student_having_salary_more_than_31000);


        // Fetching students with salary less than 28,000
        criteriaQuery = criteriaBuilder.createQuery(Student.class); // Reset the criteria query
        root = criteriaQuery.from(Student.class); // Reset the root
        Predicate salPredicate2 = criteriaBuilder.lt(root.get("Salary"), 28000);
        criteriaQuery.select(root).where(salPredicate2); // Apply the new predicate
        List<Student> student_having_salary_less_than_28000 = session.createQuery(criteriaQuery).getResultList();
        print(student_having_salary_less_than_28000);



       // Fetching students whose names start with "E"
        criteriaQuery = criteriaBuilder.createQuery(Student.class); // Reset the criteria query
        root = criteriaQuery.from(Student.class);
        Predicate nameStartsWithE = criteriaBuilder.like(root.get("name"), "E%"); // Adjust "name" to your actual field name
        criteriaQuery.select(root).where(nameStartsWithE);
        List<Student> studentsWithNameStartingWithE = session.createQuery(criteriaQuery).getResultList();
        print(studentsWithNameStartingWithE);


        // Fetching students with salaries between 25,000 and 30,000
        criteriaQuery = criteriaBuilder.createQuery(Student.class); // Reset the criteria query
        root = criteriaQuery.from(Student.class);
        Predicate salaryBetween = criteriaBuilder.between(root.get("Salary"), 25000, 30000);
        criteriaQuery.select(root).where(salaryBetween);
        List<Student> studentsWithSalaryBetween = session.createQuery(criteriaQuery).getResultList();
        print(studentsWithSalaryBetween);

        // Fetching students with null salaries
        criteriaQuery = criteriaBuilder.createQuery(Student.class); // Reset the criteria query
        root = criteriaQuery.from(Student.class);
        Predicate salaryIsNull = criteriaBuilder.isNull(root.get("Salary"));
        criteriaQuery.select(root).where(salaryIsNull);
        List<Student> studentsWithNullSalary = session.createQuery(criteriaQuery).getResultList();
        print(studentsWithNullSalary);

        // Fetching students with non-null salaries
        criteriaQuery = criteriaBuilder.createQuery(Student.class); // Reset the criteria query
        root = criteriaQuery.from(Student.class);
        Predicate salaryIsNotNull = criteriaBuilder.isNotNull(root.get("Salary"));
        criteriaQuery.select(root).where(salaryIsNotNull);
        List<Student> studentsWithNonNullSalary = session.createQuery(criteriaQuery).getResultList();
        print(studentsWithNonNullSalary);

        // Fetching students whose address is "135 Pine Ln"
        criteriaQuery = criteriaBuilder.createQuery(Student.class); // Reset the criteria query
        root = criteriaQuery.from(Student.class);
        Predicate addressEquals = criteriaBuilder.equal(root.get("address"), "135 Pine Ln"); // Adjust "address" to your actual field name
        criteriaQuery.select(root).where(addressEquals);
        List<Student> studentsWithSpecificAddress = session.createQuery(criteriaQuery).getResultList();
        print(studentsWithSpecificAddress);

        session.close(); // Close the session
        factory.close(); // Close the factory
    }
}
