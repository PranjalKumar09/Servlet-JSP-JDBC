package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnect {
private static Connection conn = null;

    public static Connection getConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "09072005");
            

            // System.out.println("Connected to the database");

        } catch (Exception e) {
        }
        return conn;
    }

}
