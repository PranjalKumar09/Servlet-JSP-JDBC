package com.ManytoMany;

import java.util.List;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Address {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String addressName;
    @ManyToMany
    private List<Emp> emp;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddressName() {
        return addressName;
    }
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    public List<Emp> getEmp() {
        return emp;
    }
    public void setEmp(List<Emp> emp) {
        this.emp = emp;
        
    }

    
}
