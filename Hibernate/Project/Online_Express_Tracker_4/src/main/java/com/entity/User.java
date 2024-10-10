package com.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String FullName;

    private String Email ;

    private String Password ;

    private String About ;

    public User() {
        super();
    }

    public String getFullName() {
        return FullName;
    }
    public String getfullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public User(String fullName, String email, String password, String about) {
        super();
        FullName = fullName;
        Email = email;
        Password = password;
        About = about;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", FullName='" + FullName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", About='" + About + '\'' +
                '}';
    }
}
