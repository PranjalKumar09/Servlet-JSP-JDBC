package spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
    @Value("#{2+3}")
    public int sum;

    @Value("#{2-3}")
    public int sub;

    @Value("#{2*3}")
    public int mul;

    @Value("#{2/3}")
    public int div;

    @Value("#{1==1}")
    public  boolean equalvalid;

    @Value("#{1>2|| 3==5}")
    public  boolean checkStatus;

    @Value("#{1 lt 5}")
    public  boolean checkStatus2;

    @Value("#{1<5 ? 'Yes' : 'No'}")
    public  String str;



    @Value("#{T(java.lang.Math).sqrt(144)}")
    public double num4;


    @Value("#{T(java.lang.Math).sqrt(144).intValue()}")
    public int num4inInt;



    @Value("#{T(spel.Student).call()}")
    public String result;


    @Value("#{T(java.lang.Math).PI } ")
    public  double num5;

    public static String call(){
        return "Method Called";
    }

    @Override
    public String toString() {
        return "Student{" +
                "sum=" + sum +
                ", sub=" + sub +
                ", mul=" + mul +
                ", div=" + div +
                ", equalvalid=" + equalvalid +
                ", checkStatus=" + checkStatus +
                ", checkStatus2=" + checkStatus2 +
                ", str='" + str + '\'' +
                ", num4=" + num4 +
                ", num4inInt=" + num4inInt +
                ", result='" + result + '\'' +
                ", num5=" + num5 +
                '}';
    }
}