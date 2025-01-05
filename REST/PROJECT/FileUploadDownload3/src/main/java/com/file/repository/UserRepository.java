package com.file.repository;

import com.file.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserDtls, Integer> {
    UserDtls findByUsername(String username);
}
