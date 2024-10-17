package com.servlets;
import java.sql.*;
import java.util.Scanner;


public class prog2_dyanmic {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "09072005");

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter student ID: ");
            int id = sc.nextInt();

            System.out.println("Enter student Name: ");
            String name = sc.next();

            System.out.println("Enter student Address: ");
            String address = sc.next();

            PreparedStatement ps = con.prepareStatement("INSERT INTO studentInfo (id, name, address) VALUES (?,?,?)");
            // PreparedStatement ps = con.prepareStatement("UPDATE studentInfo SET name=?, address=? WHERE id=?"); // for updating

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, address);
            int i = ps.executeUpdate();



            /*  if (i > 0) {
                System.out.println("Data updated successfully");
            } else {
                System.out.println("Student ID not found");
            } */


            System.out.println("Data inserted successfully");


            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
