package com.onetoMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
    public Address(int id, String addressType, String address) {
        super();
        this.id = id;
        this.addressType = addressType;
        this.address = address;
    }
    @Id
    private int id;
    private String addressType;
    private String address;
    @ManyToOne
    private EmpDtls empDtls;
    
    public Address() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddressType() {
        return addressType;
    }
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public EmpDtls getEmpDtls() {
        return empDtls;
    }

    public void setEmpDtls(EmpDtls empDtls) {
        this.empDtls = empDtls;
    }
}
