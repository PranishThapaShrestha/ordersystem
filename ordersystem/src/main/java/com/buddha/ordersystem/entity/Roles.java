package com.buddha.ordersystem.entity;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;



}
