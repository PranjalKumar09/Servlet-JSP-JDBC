package com.main;

import com.Dao.EmpDao;
import com.conn.DBConnect;

public class deleteMain {
    public static void main(String[] args){
        EmpDao dao= new EmpDao(DBConnect.getConn());
        Boolean f = dao.deleteData(2);
        if (f) System.out.println("Data deleted successfully");
        else System.out.println("Data deleted failed");
    }
}
