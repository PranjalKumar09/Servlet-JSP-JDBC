package com.java_config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.java_config")
public class config {

//    @Bean
//    public Address address() {
//        return new Address();
//    }
    @Primary
    @Bean
    public Address addrs() {
        return new Address();
    }

    @Bean
    public Address getAdd() {
        return new Address();
    }

    @Bean
    public Emp getEmp(){
        return new Emp();
    }
    @Bean(name={"another_name"})
    public Emp getEmpByAnotherName(){
        System.out.println("getEmpByAnotherName called");
        return new Emp();
    }
}
