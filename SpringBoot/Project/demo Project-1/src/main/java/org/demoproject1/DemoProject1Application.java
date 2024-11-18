package org.demoproject1;

import org.demoproject1.config.MyMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackageClasses = MyMessage.class)
public class DemoProject1Application implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World");
    }
    public static void main(String[] args) {


        ConfigurableApplicationContext context= (ConfigurableApplicationContext) SpringApplication.run(DemoProject1Application.class, args);
        MyMessage msg = context.getBean(MyMessage.class);
        System.out.println(msg.getMessage());

    }

}
