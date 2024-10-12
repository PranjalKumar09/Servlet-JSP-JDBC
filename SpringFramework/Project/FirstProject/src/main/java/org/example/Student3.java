package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Student3 {

    private int id;
    private String name;


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return "Student3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("init called");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy called");
    }
}
