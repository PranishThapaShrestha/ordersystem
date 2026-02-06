package com.buddha.ordersystem.config;

import com.buddha.ordersystem.entity.Roles;
import com.buddha.ordersystem.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        if (roleRepository.findByRoleName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Roles("ROLE_USER"));
        }


    }
}



























































