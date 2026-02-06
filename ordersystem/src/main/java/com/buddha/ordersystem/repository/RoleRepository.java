package com.buddha.ordersystem.repository;

import com.buddha.ordersystem.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRoleName(String roleName);

}
