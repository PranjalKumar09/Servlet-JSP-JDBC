package sterotype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");

        Emp student = (Emp) context.getBean("emp");

        System.out.println(student.hashCode());

        Emp student2 = (Emp) context.getBean("emp");
        System.out.println(student2.hashCode());

//        System.out.println(st2);
    }
}
