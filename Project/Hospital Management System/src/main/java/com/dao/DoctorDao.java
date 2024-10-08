package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Doctor;


public class DoctorDao {
    private Connection conn;

    public DoctorDao(Connection conn) {
        super();
        this.conn = conn;
    }
    public boolean registerDoctor(Doctor d) {
        boolean f = false;
        try {
            String sql = "Insert into doctor(fullName,dob,  qualification,specialist , email, mobNo,password) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getFullName());
            ps.setString(2, d.getDob());
            ps.setString(3, d.getQualification());
            ps.setString(4, d.getSpecailist());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getMobNo());
            ps.setString(7, d.getPassword());

            int i = ps.executeUpdate();

            if (i == 1)
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
     }

    public List<Doctor> getAllDoctor(){
        List<Doctor> list = new ArrayList<Doctor>();
        Doctor d = null;
        String sql = "Select * from doctor";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                d = new Doctor(rs.getString("fullName"), rs.getString("dob"), rs.getString("qualification"), rs.getString("specialist"), rs.getString("email"), rs.getString("mobNo"), rs.getString("password"));
                d.setId(rs.getInt("id"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Doctor getDoctorById(int id){
        Doctor d = null;
        String sql = "Select * from doctor where id = " + id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = new Doctor(rs.getString("fullName"), rs.getString("dob"), rs.getString("qualification"),
                        rs.getString("specialist"), rs.getString("email"), rs.getString("mobNo"),
                        rs.getString("password"));
                d.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public boolean updateDoctor(Doctor d) {
        boolean f = false;
        try {
            String sql = "Update doctor set fullName=?, dob=?, qualification=?, specialist=?, email=?, mobNo=?, password=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getFullName());
            ps.setString(2, d.getDob());
            ps.setString(3, d.getQualification());
            ps.setString(4, d.getSpecailist());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getMobNo());
            ps.setString(7, d.getPassword());
            ps.setInt(8, d.getId());
            

            int i = ps.executeUpdate();

            if (i == 1)
                f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    public boolean editDoctor(Doctor d) {
        boolean f = false;
        try {
            String sql = "Update doctor set fullName=?, dob=?, qualification=?, specialist=?, email=?, mobNo=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getFullName());
            ps.setString(2, d.getDob());
            ps.setString(3, d.getQualification());
            ps.setString(4, d.getSpecailist());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getMobNo());
            ps.setInt(7, d.getId());
            

            int i = ps.executeUpdate();

            if (i == 1)
                f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public boolean deleteDoctor(int id){
        boolean f = false;
        String sql = "Delete from doctor where id =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();

            if (i == 1)
                f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public Doctor login(String email, String password){
        Doctor d = null;
        String sql = "Select * from doctor where email =? and password =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
                d = new Doctor(rs.getInt("id"), rs.getString("fullName"), rs.getString("dob"), rs.getString("qualification"),
                        rs.getString("specialist"), rs.getString("email"), rs.getString("mobNo"),
                        rs.getString("password"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public int countDoctor(){
        int i = 0;
        try {
            String sql = "Select * from doctor";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                i++;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public int countAppointment() {
        int i = 0;
        try {
            String sql = "Select * from appointment";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                i++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public int countAppointmentByDoctorId(int doctor_id) {
        int i = 0;
        try {
            String sql = "Select * from appointment where doctor_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, doctor_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                i++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public int countUser() {
        int i = 0;
        try {
            String sql = "Select * from user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                i++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    public int countSpecialist() {
        int i = 0;
        try {
            String sql = "Select * from specialist";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                i++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public boolean checkOldPassword(int userId, String password) {
        boolean f = false;

        try {
            String sql = "select * from doctor where id=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public boolean changePassword(int userId, String password) {
        boolean f = false;

        try {
            String sql = "update doctor set password=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, userId);

            int i = ps.executeUpdate();

            if (i == 1)
                f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

}


//  fullName, dob, qualification, specailist, email, mobNo, password