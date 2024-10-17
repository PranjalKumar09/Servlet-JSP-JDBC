package com.main;

import java.util.List;

import com.Dao.EmpDao;
import com.conn.DBConnect;
import com.entity.Emp;

public class retriveData {
    public static void main(String[] args){
        EmpDao dao= new EmpDao(DBConnect.getConn());
        /* Emp em = dao.getInfo(99);

        if (em == null){
            System.out.println("Data not found");
            return;  // exit the program if data not found
        }

        System.out.println("ID: "+em.getId());
        System.out.println("Name: "+em.getName());
        System.out.println("Address: "+em.getAddress());
        System.out.println("Amount: "+em.getAmount()); */

        List<Emp> list = dao.getAllData();
        
        for (Emp e : list){
            System.out.println("ID: "+e.getId());
            System.out.println("Name: "+e.getName());
            System.out.println("Address: "+e.getAddress());
            System.out.println("Amount: "+e.getAmount());
            System.out.println("--------------------");
        }
    }
}
