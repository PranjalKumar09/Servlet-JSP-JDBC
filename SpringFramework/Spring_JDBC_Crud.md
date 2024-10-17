## Dao Example
#### **com.pranjal.dao.StudentDao (Interface)**
  - **Methods:**
    - `int insert(Student student)`: Inserts a student record.
    - `int update(Student student)`: Updates an existing student record.
    - `int delete(int id)`: Deletes a student by ID.
    - `Student getStudent(int id)`: Fetches a student by ID.
    - `List<Student> getAllStudents()`: Retrieves all students.
 

#### **com.pranjal.dao.StudentDaoImpl (Implementation Class)**

  - Implements `StudentDao` using **Spring's JdbcTemplate** for database operations.
  - **Key Methods:**
    - `insert(Student student)`: Executes SQL `INSERT` to add a new student.
    - `update(Student student)`: Executes SQL `UPDATE` to modify student details.
    - `delete(int id)`: Executes SQL `DELETE` to remove a student by ID.
    - `getStudent(int id)`: Executes SQL `SELECT` to fetch a student by ID. Uses `RowMapper` to map the `ResultSet` to a `Student` object.
    - `getAllStudents()`: Executes SQL `SELECT` to fetch all students. Uses `RowMapper` for result mapping.
  - **Dependency Injection:**
    - Uses **Spring Bean** (`jdbcTemplate`) injected through setter method.
 

``` java
package com.pranjal.dao;

import com.pranjal.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM STUDENT WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int insert(Student student) {
        String sql = "INSERT INTO STUDENT (id, name, address) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAddress());
    }

    @Override
    public int update(Student student) {
        String sql = "UPDATE STUDENT SET name = ?, address = ? WHERE id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getAddress(), student.getId());
    }

    @Override
    public Student getStudent(int id) {
        String sql = "SELECT * FROM STUDENT WHERE id = ?";

        RowMapper<Student> rowMapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setAddress(rs.getString("address"));
                return st;
            }
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM STUDENT";

        RowMapper<Student> rowMapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setAddress(rs.getString("address"));
                return st;
            }
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
```


#### **config.xml (Spring Bean Configuration)**

  - **Bean for StudentDaoImpl:**
    - `<bean name="studentDao" class="com.pranjal.dao.StudentDaoImpl" p:jdbcTemplate-ref="jdbcTemplate">`
    - Configures `StudentDaoImpl` and injects `JdbcTemplate`.
 

#### **com.pranjal.App (Main Application Class)**

  - **Main Logic:**
    - Loads Spring context from `config.xml` and retrieves `StudentDaoImpl` bean.
    - Implements a command-line interface (CLI) for student management operations using `Scanner`.
  - **Features:**
    - **Menu-Driven Application**:
      i. **Add Student**: Captures user input for student details and inserts into DB.
      ii. **Update Student**: Allows updating student details by ID.
      iii. **Delete Student**: Deletes a student by ID.
      iv. **Get Student by ID**: Retrieves and displays a student’s details by ID.
      v. **Get All Students**: Displays all student records.
      vi. **Exit**: Terminates the application.

    
``` java
package com.pranjal;
import com.pranjal.dao.StudentDao;
import com.pranjal.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao dao = context.getBean("studentDaoImpl", StudentDao.class); // Make sure this matches your bean id

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
                    for (Student s : students) {
                        System.out.println("ID=" + s.getId() + ", Name=" + s.getName() + ", Address=" + s.getAddress());
                    }
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
```