package org1.hibernate3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Load the Spring context from the XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate_config.xml");

        // Get the Student3DaoImpl bean from the Spring context
        Student3DaoImpl studentDao = (Student3DaoImpl) context.getBean("student3DaoImpl");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. View All Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a student
                    addStudent(studentDao, scanner);
                    break;
                case 2:
                    // View student by ID
                    viewStudentById(studentDao, scanner);
                    break;
                case 3:
                    // Update a student
                    updateStudent(studentDao, scanner);
                    break;
                case 4:
                    // Delete a student
                    deleteStudent(studentDao, scanner);
                    break;
                case 5:
                    // View all students
                    viewAllStudents(studentDao);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        } while (choice != 0);

        // Close the application context and scanner
        ((ClassPathXmlApplicationContext) context).close();
        scanner.close();
    }

    private static void addStudent(Student3DaoImpl studentDao, Scanner scanner) {
        System.out.println("\n--- Add Student ---");

        // Create a new student object
        Student3 student = new Student3();

        // Get student details from user
        System.out.print("Enter Student ID: ");
        student.setId(scanner.nextInt());
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Student Name: ");
        student.setName(scanner.nextLine());
        System.out.print("Enter Student Address: ");
        student.setAddress(scanner.nextLine());

        // Save the student
        int studentId = studentDao.saveStudent(student);
        if (studentId != 0) {
            System.out.println("Student added successfully with ID: " + studentId);
        } else {
            System.out.println("Error adding student.");
        }
    }

    private static void viewStudentById(Student3DaoImpl studentDao, Scanner scanner) {
        System.out.println("\n--- View Student by ID ---");
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();

        // Get the student by ID
        Student3 student = studentDao.getStudent(id);
        if (student != null) {
            System.out.println("Student Details: ID: " + student.getId() + ", Name: " + student.getName() + ", Address: " + student.getAddress());
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    private static void updateStudent(Student3DaoImpl studentDao, Scanner scanner) {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Fetch the student by ID
        Student3 student = studentDao.getStudent(id);
        if (student == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        // Update student details
        System.out.print("Enter new Name: ");
        student.setName(scanner.nextLine());
        System.out.print("Enter new Address: ");
        student.setAddress(scanner.nextLine());

        // Update the student in the database
        int updateStatus = studentDao.updateStudent(student);
        if (updateStatus == 1) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Error updating student.");
        }
    }

    private static void deleteStudent(Student3DaoImpl studentDao, Scanner scanner) {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        // Fetch the student by ID
        Student3 student = studentDao.getStudent(id);
        if (student == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        // Delete the student
        int deleteStatus = studentDao.deleteStudent(student);
        if (deleteStatus == 1) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Error deleting student.");
        }
    }

    private static void viewAllStudents(Student3DaoImpl studentDao) {
        System.out.println("\n--- View All Students ---");

        // Fetch all students from the database
        studentDao.getAllStudents().forEach(student -> {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Address: " + student.getAddress());
        });
    }
}
