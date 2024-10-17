package com.pranjal.main;
import com.pranjal.dao.StudentDao;
import com.pranjal.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(java_config.class);
        StudentDao dao = context.getBean("studentDao", StudentDao.class); // Make sure this matches your bean id

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Get Student by ID");
            System.out.println("5. Get All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Student
                    Student newStudent = new Student();
                    System.out.print("Enter ID: ");
                    newStudent.setId(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    newStudent.setName(scanner.nextLine());
                    System.out.print("Enter Address: ");
                    newStudent.setAddress(scanner.nextLine());
                    int insertResult = dao.insert(newStudent);
                    System.out.println("Inserted Student: " + insertResult);
                    break;

                case 2:
                    // Update Student
                    Student updateStudent = new Student();
                    System.out.print("Enter ID of the student to update: ");
                    updateStudent.setId(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    updateStudent.setName(scanner.nextLine());
                    System.out.print("Enter New Address: ");
                    updateStudent.setAddress(scanner.nextLine());
                    int updateResult = dao.update(updateStudent);
                    System.out.println("Updated Student: " + updateResult);
                    break;

                case 3:
                    // Delete Student
                    System.out.print("Enter ID of the student to delete: ");
                    int deleteId = scanner.nextInt();
                    int deleteResult = dao.delete(deleteId);
                    System.out.println("Deleted Student: " + deleteResult);
                    break;

                case 4:
                    // Get Student by ID
                    System.out.print("Enter ID of the student to retrieve: ");
                    int retrieveId = scanner.nextInt();
                    Student student = dao.getStudent(retrieveId);
                    if (student != null) {
                        System.out.println("Retrieved Student: ID=" + student.getId() + ", Name=" + student.getName() + ", Address=" + student.getAddress());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    // Get All Students
                    List<Student> students = dao.getAllStudents();
                    System.out.println("All Students:");
                    for (Student s : students)
                        System.out.println("ID=" + s.getId() + ", Name=" + s.getName() + ", Address=" + s.getAddress());
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 6);

        scanner.close(); // Close the scanner resource
    }
}
