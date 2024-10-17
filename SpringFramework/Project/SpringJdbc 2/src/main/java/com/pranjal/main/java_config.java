package com.pranjal.main;


import com.pranjal.dao.StudentDao;
import com.pranjal.dao.StudentDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "com.pranjal.main")
public class java_config {
    /*

    <bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate" p:dataSource-ref="jds" > </bean>

    <bean name="studentDao" class="com.pranjal.dao.StudentDaoImpl" p:jdbcTemplate-ref="jdbcTemplate">
    */
    @Bean
    public DriverManagerDataSource jds(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/my_db");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("09072005");
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(jds());
        jdbcTemplate.setDataSource(jds());
        return jdbcTemplate;
    }

    @Bean
    public StudentDaoImpl studentDao(){
        StudentDaoImpl studentDao = new StudentDaoImpl();
        studentDao.setJdbcTemplate(jdbcTemplate());
        return studentDao;
    }
}
