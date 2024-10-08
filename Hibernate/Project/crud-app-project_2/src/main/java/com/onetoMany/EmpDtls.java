package com.onetoMany;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class EmpDtls {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy="empDtls")
    private  List<Address> address;
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
    public List<Address> getAddress() {
        return address;
    }
    public void setAddress(List<Address> address) {
        this.address = address;
    }
    
}
