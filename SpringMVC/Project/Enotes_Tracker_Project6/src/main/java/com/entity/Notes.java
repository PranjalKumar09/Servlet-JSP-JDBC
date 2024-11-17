package com.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 60)
    private String title;
    @Column(length = 900)
    private String description;

    @Temporal(TemporalType.DATE)  // Use TemporalType.DATE for storing only the date
    private Date publishDate;

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    private User user;
}
