package org.project02_jpa.repository;

import org.project02_jpa.entities.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Emp,Integer> {

}
