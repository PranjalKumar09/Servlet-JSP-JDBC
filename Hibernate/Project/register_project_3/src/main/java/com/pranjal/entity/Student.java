package com.pranjal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Student {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private int Salary; // to make it not null do private Integer Salary;

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", address=" + address + ", Salary=" + Salary + "]";
    }

    public Student(int Salary, String address, int id, String name) {
        this.Salary = Salary;
        this.address = address;
        this.id = id;
        this.name = name;
    }


    public Student() {
    }

    public int getSalary() {
        return Salary;
    }
    public void setSalary(int salary) {
        Salary = salary;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    
}
