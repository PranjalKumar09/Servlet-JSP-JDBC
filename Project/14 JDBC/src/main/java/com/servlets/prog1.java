    package com.servlets;
    import java.sql.*;



    public class prog1 {
        public static void main(String[] args) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "09072005");
                PreparedStatement ps    = con.prepareStatement("Insert into studentInfo values (?,?,?)");
                ps.setInt(1,121);
                ps.setString(2, "John Doe");
                ps.setString(3, "Shastri Nagar, Sidhi");

                
                int i = ps.executeUpdate();
                System.out.println("Data inserted Successfully");
                System.out.println(i);


                con.close();
                

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
