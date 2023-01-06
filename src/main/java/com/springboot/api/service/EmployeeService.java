package com.springboot.api.service;

import java.util.List;

import com.springboot.api.model.Employee;

import jakarta.transaction.Transactional;

public interface EmployeeService {
    // return Employee / nameFunc / parameter
    Employee saveEmployee(Employee employee);

    //get all employee
    List<Employee> getAllEmployees();

    //get by employee id
    Employee getEmployeebyid(Long id);

    //delete employees
    void deleteEmployeesById(Long id);

    //update method
    @Transactional
    Employee updateEmployeeById(Long id, Employee employee);
}
