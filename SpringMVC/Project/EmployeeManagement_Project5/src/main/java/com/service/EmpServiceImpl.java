package com.service;

import com.dao.EmpDao;
import com.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Override
    public List<Emp> getEmpList() {
        return empDao.getAllEmp();
    }

    @Override
    public Emp getEmpById(int id) {
        return empDao.getEmpById(id);
    }

    @Override
    public int updateEmp(Emp emp) {
        return empDao.updateEmp(emp);
    }

    @Override
    public int deleteEmp(Emp emp) {
        return empDao.deleteEmp(emp);
    }

    @Autowired
    private EmpDao empDao;
    @Override
    public int addEmp(Emp emp) {
        return empDao.insertEmp(emp);
    }
}
