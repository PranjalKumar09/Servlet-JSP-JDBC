package com.service;

import com.entity.Emp;

import java.util.List;

public interface EmpService {
    public int addEmp(Emp emp);
    public List<Emp> getEmpList();
    public Emp getEmpById(int id);
    public int updateEmp(Emp emp);
    public int deleteEmp(Emp emp);
}
