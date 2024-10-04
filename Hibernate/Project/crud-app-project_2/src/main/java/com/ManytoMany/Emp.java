package com.ManytoMany;

import java.util.List;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Emp {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Name;

    @ManyToMany(mappedBy="emp")
    private List<Address> addresses;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
}
