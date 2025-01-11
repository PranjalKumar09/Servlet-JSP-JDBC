package com.role.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_privillege", joinColumns = @JoinColumn(name = "role_id"))
    private List<Privillege> privilleges;
}
