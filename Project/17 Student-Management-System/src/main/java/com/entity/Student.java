package com.entity;

public class Student {
    @Override
    public String toString() {
        return "Student [id=" + id + ", fullname=" + fullname + ", dob=" + dob + ", address=" + address
                + ", qualification=" + qualification + ", email=" + email + "]";
    }
    public Student() {
        super();
    }
    public Student(String fullname, String dob, String address, String qualification, String email) {
        super();
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.qualification = qualification;
        this.email = email;
    }

    private int id;
    private String fullname;
    private String dob;
    private String address;
    private String qualification;
    private String email;

    public Student(int id, String fullname, String dob, String address, String qualification, String email) {
        this.id = id;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.qualification = qualification;
        this.email = email;
    }
    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return String return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
