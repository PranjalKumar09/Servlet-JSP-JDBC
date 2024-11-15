package org.project03_springsecurity.reposistory;

import org.project03_springsecurity.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {

    Employee findByEmail(String email);
}
