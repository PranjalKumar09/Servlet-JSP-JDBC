package com.icwd.userservice.reposistory;

import com.icwd.userservice.enitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReository extends JpaRepository<User, String> {

}
