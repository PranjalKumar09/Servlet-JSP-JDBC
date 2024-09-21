package com.main;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.Dao.ContactDAO;
import com.conn.DBConnect;
import com.entity.contact;

public class mainClass {
    public static void main(String[] args){
        boolean f = true;
        Connection conn = DBConnect.getConn();  
        System.out.println(conn);
        while (f) {
            System.out.println("--------------------------------");
            System.out.println("1. Add Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. View Contact");
            System.out.println("5. Exit");

            Scanner sc = new Scanner(System.in);
            int no = sc.nextInt();
            ContactDAO dao = new ContactDAO(conn);
            
            
            switch (no) {
                case 1:
                System.out.println("Enter Name");
                String name = sc.next();
                
                System.out.println("Enter Phone Number");
                String phno = sc.next();
                
                contact c = new contact();
                c.setId(no);
                c.setName(name);
                c.setPhno(phno);
    
                
                boolean s1 = dao.saveContact(c);;
                
                if (s1)
                    System.out.println("Ph No Saved...");
                else System.out.println("Somethiing wrong on server");
            
                    break;
                case 2:
                    System.out.println("Enter id: ");
                    int id = sc.nextInt();
                    
                    System.out.println("Enter Name: ");
                    String Newname = sc.next();

                    System.out.println("Enter New Phone Number: ");
                    String newPhno = sc.next();

                    contact c2 = new contact();
                    c2.setId(id);
                    c2.setName(Newname);
                    c2.setPhno(newPhno);

                    boolean s2 = dao.updateContact(c2);


                    if (s2){
                        System.out.println("Updated");
                    }
                    else System.out.println("Somethiing wrong on server");

                
                    break;
                case 3:
                    System.out.println("Enter Id");
                    int id3 = sc.nextInt();

                    boolean s3 = dao.deleteContact(id3);
                    if (s3) System.out.println("Successfully deleted");
                    else System.out.println("Deletion Failed");


                break;
                case 4:
                List<contact> list = dao.getAllContacts();
                if (list.isEmpty()) System.out.println("Phno is not available");
                else {
                    for (contact con:list){
                        System.out.println("Id: " + con.getId());
                        System.out.println("Name: " + con.getName());
                        System.out.println("newPhnot: " + con.getPhno());
                        System.out.println("----------------------------");
                    }}



                    break;
                case 5:
                    f = false;

                    break;
                default:
                    System.out.println("Please enter correct phjone no");


            }

        }
    
    
    
    }
}
