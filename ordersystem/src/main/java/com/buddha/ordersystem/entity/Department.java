package com.buddha.ordersystem.entity;

import jakarta.persistence.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "departname_name")
    private String departmentName;

    @Column(name = "dpt_code")
    private String dptCode;

    @OneToMany(mappedBy = "department",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Users> user = new ArrayList<>();

}
