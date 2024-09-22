package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.entity.Student;

public class StudentDAO {
    private Connection conn;

    public StudentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean addStudent(Student student) {
        boolean isAdded = false;
        String sql = "INSERT INTO student(name, dob, address, qualification, email) VALUES(?,?,?,?,?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFullname());
            ps.setString(2, student.getDob());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getQualification());
            ps.setString(5, student.getEmail());

            int i = ps.executeUpdate();
            if (i == 1) isAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        try (PreparedStatement ps = conn.prepareStatement(sql); 
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String fullname = rs.getString("name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String qualification = rs.getString("qualification");
                String email = rs.getString("email");

                Student s = new Student(fullname, dob, address, qualification, email);
                s.setId(id);
                students.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int id){
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String fullname = rs.getString("name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String qualification = rs.getString("qualification");
                String email = rs.getString("email");

                student = new Student(fullname, dob, address, qualification, email);
                student.setId(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean updateStudent(Student s){
        boolean f = false;
        String sql = "UPDATE student SET name=?, dob=?, address=?, qualification=?, email=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getFullname());
            ps.setString(2, s.getDob());
            ps.setString(3, s.getAddress());
            ps.setString(4, s.getQualification());
            ps.setString(5, s.getEmail());
            ps.setInt(6, s.getId());

            int i = ps.executeUpdate();
            if (i == 1) f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean deleteStudent(int id){
        boolean f = false;
        String sql = "Delete from student  WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            if (i == 1) f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
