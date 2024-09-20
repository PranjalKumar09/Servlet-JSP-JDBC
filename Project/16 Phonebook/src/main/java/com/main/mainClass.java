package com.main;

import java.sql.Connection;

import com.conn.DBConnect;

public class mainClass {
    public static void main(String[] args){
        Connection conn = DBConnect.getConn();
        System.out.println(conn);
    }
}
