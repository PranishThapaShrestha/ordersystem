package com.buddha.ordersystem.repository;

import com.buddha.ordersystem.auth.entity.RefreshToken;
import com.buddha.ordersystem.entity.Users;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByName(String userName);

    Optional<Users> findBdByEmail(String email);


}
