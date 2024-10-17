package com.java_config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean6.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(config.class);
//        Emp emp = (Emp) context.getBean("getEmp");
//        Emp emp2 = (Emp) context.getBean("emp");
//        System.out.println(emp);
//        System.out.println(emp2);
    }
}

// ioc container first check with type name , then check it with  variable name , in multiple beans then give error if none found
// to avoid it we can use @Primary in config file,