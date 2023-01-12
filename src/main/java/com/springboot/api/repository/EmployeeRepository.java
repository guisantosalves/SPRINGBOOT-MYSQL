package com.springboot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
