package org.example;

public class Shyam implements Action{
    @Override
    public void eat() {
        System.out.println("Shyam is eating");
    }
    public Shyam() {
        System.out.println("Ram object is created");
    }


    @Override
    public void sleep() {
        System.out.println("Shyam is sleeping");

    }
}
