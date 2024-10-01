package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Specialist;

public class SpecialistDao {
    private Connection conn;
    public SpecialistDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public boolean addSpeaciallist(String spec){
        boolean f = false;
        try {
            String sql = "INSERT INTO specialist(spec_name) values(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, spec);

            int i = ps.executeUpdate();
            if (i==1) f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
    public List<Specialist> getAllSpecialist(){
        List<Specialist> list = new ArrayList<Specialist>();
        Specialist s = null;

        try {
            String sql = "select * from specialist";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                s = new Specialist(rs.getInt("id"), rs.getString("spec_name"));
                list.add(s);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }    
}
