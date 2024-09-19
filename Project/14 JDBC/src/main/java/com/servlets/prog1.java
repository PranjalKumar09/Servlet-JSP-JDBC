    package com.servlets;
    import java.sql.*;



    public class prog1 {
        public static void main(String[] args) {
            try {
                /*
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "09072005");
                // PreparedStatement ps    = con.prepareStatement("Insert into studentInfo values (?,?,?)");
                PreparedStatement ps    = con.prepareStatement("Update  studentInfo set name=?, address=? where id=? ");
                ps.setString(1,"Pranjal Kumar");
                ps.setString(2, "In front of Entrace Gate, Sidhi");
                ps.setInt(3, 121);

                
                int i = ps.executeUpdate();  // not necessary to store it in num
                System.out.println("Data inserted Successfully");
                System.out.println(i);


                con.close();
                */

                Connection con = null;
                PreparedStatement psInsert = null;
                ResultSet rs = null;
        

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "09072005");
    
                // Insert multiple records
                String insertQuery = "INSERT INTO studentInfo (id, name, address) VALUES (?, ?, ?)";
                psInsert = con.prepareStatement(insertQuery);
    

                psInsert.setInt(1, 122);
                psInsert.setString(2, "Aarti Verma");
                psInsert.setString(3, "Near Main Market, Rewa");
                psInsert.addBatch();
    
                // Insert record 3
                psInsert.setInt(1, 123);
                psInsert.setString(2, "Ravi Sharma");
                psInsert.setString(3, "Behind City Mall, Satna");
                psInsert.addBatch();
    
                // Execute batch
                int[] result = psInsert.executeBatch();
                System.out.println("Data inserted Successfully. Records affected: " + result.length);
    
                // Query to print all data
                Statement stmt = con.createStatement();
                String selectQuery = "SELECT * FROM studentInfo";
                rs = stmt.executeQuery(selectQuery);
    
                // Print all data
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    System.out.println("ID: " + id + ", Name: " + name + ", Address: " + address);
                }
    
                

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
