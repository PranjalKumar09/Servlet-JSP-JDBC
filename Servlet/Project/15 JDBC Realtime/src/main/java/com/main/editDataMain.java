package com.main;

import java.util.Scanner;

import org.eclipse.tags.shaded.java_cup.runtime.lr_parser;

import com.Dao.EmpDao;
import com.conn.DBConnect;
import com.entity.Emp;

public class editDataMain {
    public static void main(String[] args) {
        Emp em= new Emp();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Id: ");
        int id = sc.nextInt();
        System.out.println("Enter the new Name: ");
        String name = sc.next();
        System.out.println("Enter the new Address: ");
        String address = sc.next();



        em.setId(id);
        em.setAddress(address);
        em.setName(name);
        
        EmpDao dao = new EmpDao(DBConnect.getConn());
        Boolean f = dao.editData(em);

        if (f)
            System.out.println("Data edit successful");
        else
            System.out.println("Data edit failed");
            
    }

}
