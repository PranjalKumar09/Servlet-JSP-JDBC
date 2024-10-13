package com.java_config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class Emp {
    @Autowired
    private Address address;

    @Override
    public String toString() {
        return "Emp{" +
                "address=" + address +
                '}';
    }

    public Address address() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
