package jdbc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "jdbc")
public class JavaConfig {
    @Bean
    public DriverManagerDataSource  jds(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/my_db");
        dataSource.setUsername("root");
        dataSource.setPassword("09072005");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate( ){
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(jds());
      JdbcTemplate jdbcTemplate = new JdbcTemplate();
      jdbcTemplate.setDataSource(jds());
      return jdbcTemplate;


    }
}
