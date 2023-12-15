package com.project06_employee_management_crud.service;

import com.project06_employee_management_crud.enitity.Employee;

import java.util.List;

public interface EmpService {
    public Employee getEmpById(int id);
    public Employee saveEmp(Employee emp);
    public List<Employee> getAllEmp(

    );
    public boolean deleteEmp(int id);
}
