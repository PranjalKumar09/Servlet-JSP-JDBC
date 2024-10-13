package com.java_config;

public class Address {
    private String street;
    private String city;

    public String city() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String street() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
