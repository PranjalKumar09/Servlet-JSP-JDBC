package com.icwd.config.server.project;

import com.icwd.config.server.project.beans.Network;
import com.icwd.config.server.project.beans.Phone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args)
    {
//        SpringApplication.run(Application.class, args);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        Network network = (Network) ctx.getBean("network");
        System.out.println(network);
        Phone phone = ctx.getBean("phone", Phone.class);
        System.out.println(phone);
        System.out.println(phone.network);
    }

}
