package com.buddha.ordersystem.repository;

import com.buddha.ordersystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
