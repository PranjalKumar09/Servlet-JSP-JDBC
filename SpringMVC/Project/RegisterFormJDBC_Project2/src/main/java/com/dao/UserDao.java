package com.dao;

import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(User user) {
        String sql = "insert into User(fullName,age, password, email) values (? ,? ,? ,?)";
        return jdbcTemplate.update(sql, user.getFullName(), user.getAge(), user.getPassword(), user.getEmail());
    }

}
