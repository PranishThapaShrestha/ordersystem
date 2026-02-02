package com.buddha.ordersystem.repository;

import com.buddha.ordersystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
