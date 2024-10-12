package org.example;

public class Ram  implements Action{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;
    public Ram() {
        System.out.println("Ram object is created");
    }

    @Override
    public void eat() {
        System.out.println("Ram eat");
    }

    @Override
    public void sleep() {
        System.out.println("Ram sleep");
    }
}
