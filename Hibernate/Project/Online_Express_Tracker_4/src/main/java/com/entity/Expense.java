package com.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Expense {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private Double amount;

    @ManyToOne
    private User user;

    public Expense(String title, LocalDate date, LocalTime time, String description, double amount) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Expense(String title, LocalDate date, LocalTime time, String description, double amount, User user) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.description = description;
        this.amount = amount;
        this.user = user;
    }

    public Expense() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
