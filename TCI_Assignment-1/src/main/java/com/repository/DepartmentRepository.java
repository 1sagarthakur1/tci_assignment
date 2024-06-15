package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
	
}
