package com.pranjal.dao;

import com.pranjal.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM STUDENT WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int insert(Student student) {
        String sql = "INSERT INTO STUDENT (id, name, address) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAddress());
    }

    @Override
    public int update(Student student) {
        String sql = "UPDATE STUDENT SET name = ?, address = ? WHERE id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getAddress(), student.getId());
    }

    @Override
    public Student getStudent(int id) {
        String sql = "SELECT * FROM STUDENT WHERE id = ?";

        RowMapper<Student> rowMapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setAddress(rs.getString("address"));
                return st;
            }
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM STUDENT";

        RowMapper<Student> rowMapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setAddress(rs.getString("address"));
                return st;
            }
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
