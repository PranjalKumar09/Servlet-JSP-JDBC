

nextLine() -> It consumes the entire line of input including the newline character.

Given that Student is a class, how many reference variables and objects are created by the following code?
    Student studentName, studentId;
    studentName = new Student();
    Student stud_class = new Student();
    Three reference variables and two objects are created.


to create an instance of an anonymous class -> By new

Which of the following exceptions is not an unchecked exception-> B)FileNotFoundException


abstract class have a constructor -> Yes
they can called in base class constructor


Anonymous classes cannot be declared static.




Anonymous inner classes always extend directly from the Object class. FALSE
When you create an anonymous class for an interface, it extends from Object. For example,

But if you make an anonymous class from another class then it extends from that class. For example, consider the following class:









package test;

public class TopClass
{
   public Inner inner1 = new Inner()
   {
       private static int value;
       public void doA(){  System.out.println("doing A"); }
    };

    public void doA() { inner1.doA(); }
}

class Inner
{
   int value;
}


CE due to Illegal static declaration: The private static int value; within the anonymous inner class is not allowed.
Method not found: The call inner1.doA() is invalid because the Inner class (the declared type of inner1) does not define a doA() method. The doA() method exists only in the anonymous subclass, which is not the declared type of inner1. 