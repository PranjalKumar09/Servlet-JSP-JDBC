package org.project02_jpa.entities;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne
    private Mobile mobile;
}