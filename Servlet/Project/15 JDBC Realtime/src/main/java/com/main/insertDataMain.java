package com.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.conn.DBConnect;
import com.entity.Emp;
import com.Dao.EmpDao;

public class insertDataMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;

        try {
            // Collecting input with validation
            System.out.println("Enter Employee ID: ");
            int id = sc.nextInt();

            System.out.println("Enter Employee Name: ");
            String name = sc.next();

            System.out.println("Enter Employee Address: ");
            String address = sc.next();

            System.out.println("Enter Employee Amount: ");
            double amount = sc.nextDouble();

            // Creating an Employee object
            Emp em = new Emp();
            em.setId(id);
            em.setAddress(address);
            em.setName(name);
            em.setAmount(amount);

            // Database interaction
            conn = DBConnect.getConn();
            EmpDao dao = new EmpDao(conn);

            // Inserting data and handling the result
            boolean f = dao.dataInsert(em);
            if (f) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
        } finally {
            // Closing scanner and connection
            sc.close();
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }
}
