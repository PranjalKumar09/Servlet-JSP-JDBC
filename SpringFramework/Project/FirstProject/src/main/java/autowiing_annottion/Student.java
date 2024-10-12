package autowiing_annottion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Student {

    @Autowired()
    @Qualifier("add1")
    private Address address;
    public Address getAddress() {
        return address;
    }



    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "address=" + address +
                '}';
    }


/*
    protected Student(Address address) {
        System.out.println("Student constructor called");
        this.address = advvdress;
    }*/
}
