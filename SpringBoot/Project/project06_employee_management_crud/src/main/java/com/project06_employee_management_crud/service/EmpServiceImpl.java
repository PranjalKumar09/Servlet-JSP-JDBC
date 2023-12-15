package com.project06_employee_management_crud.service;

import com.project06_employee_management_crud.enitity.Employee;
import com.project06_employee_management_crud.repository.EmpRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpRepository empRepository;

    @Override
    public boolean deleteEmp(int id) {
        Employee emp = this.getEmpById(id);
        if (emp != null) {
            empRepository.delete(emp);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmp( ) {
        return empRepository.findAll();
    }

    @Override
    public Employee getEmpById(int id) {
        return empRepository.findById(id).get();
    }

    @Override
    public Employee saveEmp(Employee employee) {
        Employee new_emp = empRepository.save(employee);

        return new_emp;
    }

    public void removeSessionMessage(){
        HttpSession session = ((ServletRequestAttributes)(Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest().getSession();
        session.removeAttribute("msg");
    }
}
