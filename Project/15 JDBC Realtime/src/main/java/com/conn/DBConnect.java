package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public static Connection getConn() {
        try {
            if (conn == null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "09072005");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void setConn(Connection conn) {
        DBConnect.conn = conn;
    }

    private static Connection conn;

    


}
