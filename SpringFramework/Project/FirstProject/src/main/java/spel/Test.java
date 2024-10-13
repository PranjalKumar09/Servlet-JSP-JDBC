package spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean6.xml");

        context.getBean("student");

        System.out.println(context.getBean("student"));


        SpelExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Hello World'");
        System.out.println(expression.getValue(context));

    }
}
