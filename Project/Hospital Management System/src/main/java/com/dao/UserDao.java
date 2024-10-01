package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        super();
        this.conn = conn;
    }
    public boolean Register(User u){
        boolean f = false;
        try {
            String sql = "INSERT INTO user (full_name, email, password) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getFullname());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());

            int i = ps.executeUpdate();
            
            if (i == 1)
                f = true;
            
            


        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public User login(String email, String password){
        User u = null;
        try {
            String sql = "SELECT * FROM user WHERE email =? AND password =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                u = new User(rs.getString("full_name"), rs.getString("email"), rs.getString("password"));
                u.setId(rs.getInt("id"));
            }
            
            


        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean  checkOldPassword(int userId, String password){
        boolean f = false;

        try {
            String sql = "select * from user where id=? and password=?";
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

    public boolean changePassword(int userId, String password){
        boolean f = false;

        try {
            String sql = "update user set password=? where id=?";
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


