package com.buddha.ordersystem.service;

import com.buddha.ordersystem.dto.DepartmentDto;
import com.buddha.ordersystem.dto.UserDto;
import com.buddha.ordersystem.dto.UserRequest;
import com.buddha.ordersystem.entity.Department;
import com.buddha.ordersystem.entity.Roles;
import com.buddha.ordersystem.entity.Users;
import com.buddha.ordersystem.repository.DepartmentRepository;
import com.buddha.ordersystem.repository.RoleRepository;
import com.buddha.ordersystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;

    public UserService(UserRepository userRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder, DepartmentRepository departmentRepository) {
        this.userRepository = userRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepository = departmentRepository;
    }

    public UserDto createUser(UserRequest userRequest) {

        if (userRepository.findByName(userRequest.getUserName()).isPresent()) {
            throw new RuntimeException("User Already Exist");
        }
        Roles role = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        Department dept2 = departmentRepository.findById(userRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found of id entered!!"));

        Users user = new Users();
        user.setUserName(userRequest.getUserName());
        user.setDepartment(dept2);
        user.setAddress(userRequest.getAddress());
        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userRepository.save(user);


        return mapToUserDto(user);
    }

    private UserDto mapToUserDto(Users user) {
        Department dept = user.getDepartment();
        DepartmentDto departmentDto = new DepartmentDto(dept.getDepartmentName());
        UserDto userDto = new UserDto(user.getUserName(), user.getAddress(), departmentDto);
        return userDto;

    }


}
