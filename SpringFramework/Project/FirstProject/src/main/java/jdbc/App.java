package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);

        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        System.out.println(jdbcTemplate);
        System.out.println(jdbcTemplate.getDataSource());
        System.out.println(jdbcTemplate.getDataSource().getConnection());

        System.out.println("--------------------------------------------------------------------");
        String sql = "SELECT * FROM   STUDENT";

        RowMapper rowMapper = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                String name = rs.getString(2);
                return name;
            }
        };

        List<String> list = jdbcTemplate.query(sql, rowMapper);

        System.out.println(list);


    }
}



