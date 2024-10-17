package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Emp;



public class EmpDao {
    private Connection   conn;

    public EmpDao(Connection conn) {
        super();

        this.conn = conn;
    }

    public boolean dataInsert(Emp emp){
        boolean f = false;
        try{
            PreparedStatement ps = conn.prepareStatement("insert into Employee values(?,?,?,?)");
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getAddress());
            ps.setDouble(4, emp.getAmount());
            int a = ps.executeUpdate();
            
            if (a == 1) f = true; // if correct inserting in database
        }catch(Exception e){
            e.printStackTrace();
        }
        return f ;

    }

    public boolean editData(Emp em){
        boolean f = false;
        try{
            PreparedStatement ps = conn.prepareStatement("update Employee set name = ?, address = ? where id =?");
            ps.setString(1, em.getName());
            ps.setString(2, em.getAddress());
            ps.setInt(3, em.getId());
            int a = ps.executeUpdate();
            
            if (a == 1) f = true; // if correct updating in database
        }catch(Exception e){
            e.printStackTrace();
        }
        return f ;
    }

    public boolean deleteData(int id){
        boolean f = false;
        try{
            PreparedStatement ps = conn.prepareStatement("delete from Employee where id =?");
            ps.setInt(1, id);
            int a = ps.executeUpdate();
            
            if (a == 1) f = true; // if correct deleting in database
        }catch(Exception e){
            e.printStackTrace();
        }
        return f ;
    }
    public Emp getInfo(int id){
        Emp em = null;
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Employee where id =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while  (rs.next()){
                em = new Emp();  // create new object for employee entity
                em.setId(rs.getInt("id"));
                em.setName(rs.getString("name"));
                em.setAddress(rs.getString("address"));
                em.setAmount(rs.getDouble("amount"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return em;
    }

    public List<Emp> getAllData(){
        List<Emp> list = new ArrayList<Emp>();
        Emp em = null;
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Employee");
            ResultSet rs = ps.executeQuery();
            
            while  (rs.next()){
                em = new Emp();  // create new object for employee entity
                em.setId(rs.getInt("id"));
                em.setName(rs.getString("name"));
                em.setAddress(rs.getString("address"));
                em.setAmount(rs.getDouble("amount"));
                list.add(em);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
