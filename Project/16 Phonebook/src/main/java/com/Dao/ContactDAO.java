package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.contact;
import java.util.List;
import java.util.ArrayList;

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

    public Boolean updateContact(contact c) {
        Boolean result = false;
    
        try {
            // Prepare the SQL statement to update the contact's phone and name where the id matches
            PreparedStatement ps = conn.prepareStatement("UPDATE contact SET phno=?, name=? WHERE id=?");
    
            // Set the values from the contact object to the prepared statement
            ps.setString(1, c.getPhno()); // Set the new phone number
            ps.setString(2, c.getName()); // Set the new name
            ps.setInt(3, c.getId());      // Set the contact's ID
    
            // Execute the update query and check if it affected one row
            int i = ps.executeUpdate();
            if (i == 1) result = true;
            
        } catch (Exception e) {
            // Print the exception stack trace for debugging
            e.printStackTrace();
        }
    
        // Return whether the update was successful or not
        return result;
    }
    

    public Boolean deleteContact(int id){
        Boolean result = false;

        try{
            PreparedStatement ps = conn.prepareStatement("delete from contact where id=?");
            ps.setInt(1, id);
            
            int i = ps.executeUpdate();
            
            if (i == 1) result = true;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<contact> getAllContacts() {
        List<contact> contactList = new ArrayList<contact>();
        contact c = null;

        try {
            PreparedStatement ps = conn.prepareStatement("select * from contact");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c = new contact();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setPhno(rs.getString("phno"));
                contactList.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }
}

