package car.example.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

            // Loading the Spring context from the XML configuration file
            ApplicationContext context = new ClassPathXmlApplicationContext();

            // Retrieving the 'myBean' from the Spring context
// MyBean myBean = (MyBean) context.getBean("myBean");

            // Using the ShowMessage method




    }
}
