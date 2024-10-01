package com.entity;

public class Appointment {
    private int id, userId, doctorId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String fullName, gender, age, appoint_date, phno, disease, address, status, email ;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Appointment() {
        super();
    }
    public Appointment(String email, int userId, int doctorId, String fullName, String gender, String age,
            String appoint_date, String phno, String disease, String address, String status) {
        super();
        this.email = email;
        this.userId = userId;
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.appoint_date = appoint_date;
        this.phno = phno;
        this.disease = disease;
        this.address = address;
        this.status = status;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAppoint_date() {
        return appoint_date;
    }
    public void setAppoint_date(String appoint_date) {
        this.appoint_date = appoint_date;
    }
    public String getPhno() {
        return phno;
    }
    public void setPhno(String phno) {
        this.phno = phno;
    }
    public String getDisease() {
        return disease;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
