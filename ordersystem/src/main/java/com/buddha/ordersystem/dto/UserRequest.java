package com.buddha.ordersystem.dto;

import com.buddha.ordersystem.entity.Department;
import com.buddha.ordersystem.entity.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserRequest {

    private String userName;

    private String password;

    private String address;

    private Long departmentId;

    private Set<Roles> roles = new HashSet<>();

}
