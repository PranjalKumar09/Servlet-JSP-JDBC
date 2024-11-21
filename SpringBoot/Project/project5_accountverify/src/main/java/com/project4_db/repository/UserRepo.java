package com.project4_db.repository;

import com.project4_db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByVerficationCode(String verificationCode);

    @Query("update User u set u.failedAttempt=?1 where u.email=?2")
    @Modifying
    void updateFailedAttempt(int attempt, String email);
}
