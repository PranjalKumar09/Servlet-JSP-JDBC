package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static Connection conn;
    public static Connection getConn() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "09072005");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}



