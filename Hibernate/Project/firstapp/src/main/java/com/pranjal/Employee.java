package com.pranjal;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;


@Entity
@Table(name ="emp_dtls")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "name", nullable = false, length = 50)
    private String empName;
    private Double salary;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joiningDate;
    private String email;
    private boolean status;

    @Transient
    private String token;
    public Employee() {
super();
    }
    public Employee(int id, String empName, Double salary, Date joiningDate, String email, boolean status,
            String token) {
        super();
        this.id = id;
        this.empName = empName;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.email = email;
        this.status = status;
        this.token = token;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public Date getJoiningDate() {
        return joiningDate;
    }
    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", empName=" + empName + ", salary=" + salary + ", joiningDate=" + joiningDate
                + ", email=" + email + ", status=" + status + ", token=" + token + "]";
    }

    

}
