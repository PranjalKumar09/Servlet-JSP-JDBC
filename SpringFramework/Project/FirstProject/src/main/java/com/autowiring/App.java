package com.autowiring;

import org.example.Student3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");

        Student student = (Student) context.getBean("st");


        System.out.println(student);

//        System.out.println(st2);
    }
}

//
//Exception in thread "main" org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException: Line 1 in XML document from class path resource [beans2.xml] is invalid
//Caused by: org.xml.sax.SAXParseException; lineNumber: 1; columnNumber: 39; Content is not allowed in prolog.