package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.entity.contact;

public class ContactDAO {
    private Connection conn;
    
    public ContactDAO(Connection conn) {
        super();
        this.conn = conn;
        
    }

    public Boolean saveContact(contact c){
        Boolean result = false;

        try{
            PreparedStatement ps = conn.prepareStatement("Insert into contact(name, phno) values (?, ?)");

            ps.setString(1, c.getName());
            ps.setString(2, c.getPhno());

            int i = ps.executeUpdate();
            if (i == 1) result = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Boolean updateContact(contact c){
        Boolean result = false;

        try{
            PreparedStatement ps = conn.prepareStatement("Update contact set phone=? where name=?");
            ps.setString(1, c.getPhno());
            ps.setString(2, c.getName());

            int i = ps.executeUpdate();
            if (i == 1) result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        
    }

    public Boolean deleteContact(String name){
        Boolean result = false;

        try{
            PreparedStatement ps = conn.prepareStatement("delete from contact where name=?");
            ps.setString(1, name);
            
            int i = ps.executeUpdate();
            
        }
    }
}

