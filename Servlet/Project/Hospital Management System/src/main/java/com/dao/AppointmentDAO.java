package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.entity.Appointment;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private Connection conn;

    public AppointmentDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addAppointment(Appointment ap){
        boolean f = false;
        
        try {
            String sql = "INSERT INTO appointment(user_id, doctor_id, full_name, gender, age, appoint_date, phno, disease, address, status, email) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, ap.getUserId());
            ps.setInt(2, ap.getDoctorId());
            ps.setString(3, ap.getFullName());
            ps.setString(4, ap.getGender());
            ps.setString(5, ap.getAge());
            ps.setString(6, ap.getAppoint_date());
            ps.setString(7, ap.getPhno());
            ps.setString(8, ap.getDisease());
            ps.setString(9, ap.getAddress());
            ps.setString(10, ap.getStatus());
            ps.setString(11, ap.getEmail());

            int i = ps.executeUpdate();
            if (i==1)
                f = true;
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    public List<Appointment> getAllAppointmentsByUserId(int id_l) {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment where user_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_l);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int doctorId = rs.getInt("doctor_id");
                String fullName = rs.getString("full_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String appoint_date = rs.getString("appoint_date");
                String phno = rs.getString("phno");
                String disease = rs.getString("disease");
                String address = rs.getString("address");
                String status = rs.getString("status");
                String email = rs.getString("email");

                Appointment ap = new Appointment(email,userId,doctorId, fullName, gender, age, appoint_date, phno, disease ,address, status );
                ap.setId(id);
                list.add(ap);
        }
    }
        catch (Exception e) {
        e.printStackTrace();
        }
    return list;
    }

    public List<Appointment> getAllAppointmentsByDoctor_Id(int id_l) {
        List<Appointment> list = new ArrayList<Appointment>();
        String sql = "SELECT * FROM appointment where doctor_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_l);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                    int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int doctorId = rs.getInt("doctor_id");
                String fullName = rs.getString("full_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String appoint_date = rs.getString("appoint_date");
                String phno = rs.getString("phno");
                String disease = rs.getString("disease");
                String address = rs.getString("address");
                String status = rs.getString("status");
                String email = rs.getString("email");

                Appointment ap = new Appointment(email, userId, doctorId, fullName, gender, age, appoint_date, phno,
                        disease, address, status);
                ap.setId(id);
                list.add(ap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<Appointment>();
        String sql = "SELECT * FROM appointment";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                    int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int doctorId = rs.getInt("doctor_id");
                String fullName = rs.getString("full_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String appoint_date = rs.getString("appoint_date");
                String phno = rs.getString("phno");
                String disease = rs.getString("disease");
                String address = rs.getString("address");
                String status = rs.getString("status");
                String email = rs.getString("email");

                Appointment ap = new Appointment(email, userId, doctorId, fullName, gender, age, appoint_date, phno,
                        disease, address, status);
                ap.setId(id);
                list.add(ap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Appointment getAppointmentById(int id_l){
        Appointment ap = null;
        String sql = "SELECT * FROM appointment where id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_l);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int doctorId = rs.getInt("doctor_id");
                String fullName = rs.getString("full_name");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                String appoint_date = rs.getString("appoint_date");
                String phno = rs.getString("phno");
                String disease = rs.getString("disease");
                String address = rs.getString("address");
                String status = rs.getString("status");
                String email = rs.getString("email");

                ap = new Appointment(email, userId, doctorId, fullName, gender, age, appoint_date, phno,
                        disease, address, status);
                ap.setId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ap;
    }

    public boolean updateCommentStatus(int id, int did, String comm){
        boolean f = false;
        String sql = "UPDATE appointment SET status=? WHERE id=? AND doctor_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, comm);
            ps.setInt(2, id);
            ps.setInt(3, did);

            int i = ps.executeUpdate();
            if (i==1)
                f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

}

